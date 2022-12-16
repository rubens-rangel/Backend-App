package com.rubensrangel.BackendApp.services;

import com.rubensrangel.BackendApp.domain.Cidade;
import com.rubensrangel.BackendApp.domain.Cliente;
import com.rubensrangel.BackendApp.domain.Endereco;
import com.rubensrangel.BackendApp.domain.enums.Perfil;
import com.rubensrangel.BackendApp.domain.enums.TipoCliente;
import com.rubensrangel.BackendApp.dto.ClienteDTO;
import com.rubensrangel.BackendApp.dto.ClienteNewDTO;
import com.rubensrangel.BackendApp.repositories.CidadeRepository;
import com.rubensrangel.BackendApp.repositories.ClienteRepository;
import com.rubensrangel.BackendApp.repositories.EnderecoRepository;
import com.rubensrangel.BackendApp.security.UserSS;
import com.rubensrangel.BackendApp.services.exceptions.AuthorizationException;
import com.rubensrangel.BackendApp.services.exceptions.DataIntegrityException;
import com.rubensrangel.BackendApp.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.net.URI;
import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {

    @Autowired
    private BCryptPasswordEncoder pe;

    @Autowired
    private ClienteRepository repo;

    @Autowired
    private EnderecoRepository enderecoRepository;

    @Autowired
    private CidadeRepository cidadeRepository;

    @Autowired
    private S3Service s3Service;

    public Cliente find(Integer id) {
        UserSS user = UserService.authenticated();
        if (user == null || !user.hasRole(Perfil.ADMIN) && !id.equals(user.getId())) {
            throw new AuthorizationException("Acesso Negado");
        }
        Optional<Cliente> obj = repo.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! Id: " + id + ", Tipo: " + Cliente.class.getName()));
    }

    public Cliente update(Cliente obj) {
        Cliente newObj = find(obj.getId());
        updateData(newObj, obj);
        return repo.save(newObj);
    }

    @Transactional
    public Cliente insert(Cliente obj) {
        obj.setId(null);
        obj = repo.save(obj);
        enderecoRepository.saveAll(obj.getEnderecos());
        return obj;
    }

    public void delete(Integer id) {
        find(id);
        try {
            repo.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityException("Não é possível excluir um cliente que possui há pedidos relacionados");
        }
    }

    public List<Cliente> findAll() {
        return repo.findAll();
    }

    public Page<Cliente> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
        PageRequest pageRequest = PageRequest.of(page, linesPerPage, Sort.Direction.valueOf(direction), orderBy);
        return repo.findAll(pageRequest);
    }

    public Cliente fromDTO(ClienteDTO objDTO) {
        return new Cliente(objDTO.getId(), objDTO.getNome(), objDTO.getEmail(), null, null, null);
    }
    public Cliente fromNewDTO(ClienteNewDTO objDTO) {
        Cliente cli = new Cliente(null, objDTO.getNome(), objDTO.getEmail(), objDTO.getCpfOuCnpj(), TipoCliente.toEnum(objDTO.getTipo()),pe.encode(objDTO.getSenha()));
        Cidade cid = new Cidade(objDTO.getCidadeId(), null, null);
        Endereco end = new Endereco(null, objDTO.getLogradouro(), objDTO.getNumero(), objDTO.getBairro(), objDTO.getComplemento(), objDTO.getCep(), cid, cli );
        cli.getEnderecos().add(end);
        cli.getTelefones().add(objDTO.getTelefone1());
        if(objDTO.getTelefone2()!= null){
            cli.getTelefones().add(objDTO.getTelefone2());
        }if(objDTO.getTelefone3()!= null){
            cli.getTelefones().add(objDTO.getTelefone3());
        }
        return cli;
    }

    private void updateData(Cliente newObj, Cliente obj) {
        newObj.setNome(obj.getNome());
        newObj.setEmail(obj.getEmail());
    }

    public URI uploadProfilePicture(MultipartFile multipartFile){
        return s3Service.uploadFile(multipartFile);
    }

}
