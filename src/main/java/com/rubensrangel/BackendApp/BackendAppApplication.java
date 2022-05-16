package com.rubensrangel.BackendApp;

import com.rubensrangel.BackendApp.domain.Categoria;
import com.rubensrangel.BackendApp.domain.Cidade;
import com.rubensrangel.BackendApp.domain.Cliente;
import com.rubensrangel.BackendApp.domain.Endereco;
import com.rubensrangel.BackendApp.domain.Estado;
import com.rubensrangel.BackendApp.domain.Produto;
import com.rubensrangel.BackendApp.domain.enums.TipoCliente;
import com.rubensrangel.BackendApp.repositories.CategoriaRepository;
import com.rubensrangel.BackendApp.repositories.CidadeRepository;
import com.rubensrangel.BackendApp.repositories.ClienteRepository;
import com.rubensrangel.BackendApp.repositories.EnderecoRepository;
import com.rubensrangel.BackendApp.repositories.EstadoRepository;
import com.rubensrangel.BackendApp.repositories.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Arrays;

@SpringBootApplication
public class BackendAppApplication implements CommandLineRunner {

	@Autowired
	private CategoriaRepository categoriaRepository;

	@Autowired
	private ProdutoRepository produtoRepository;

	@Autowired
	private CidadeRepository cidadeRepository;

	@Autowired
	private EstadoRepository estadoRepository;

	@Autowired
	private ClienteRepository clienteRepository;

	@Autowired
	private EnderecoRepository enderecoRepository;

	public static void main(String[] args) {
		SpringApplication.run(BackendAppApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		Categoria cat1 = new Categoria(null, "Informatica");
		Categoria cat2 = new Categoria(null, "Escritorio");

		Produto p1 = new Produto(null, "Computador", 2000.00);
		Produto p2 = new Produto(null, "impressora", 300.00);
		Produto p3 = new Produto(null, "mouse", 500.00);


		cat1.getProdutos().addAll(Arrays.asList(p1,p2,p3));
		cat2.getProdutos().addAll(Arrays.asList(p2));

		p1.getCategorias().addAll(Arrays.asList(cat1));
		p2.getCategorias().addAll(Arrays.asList(cat1,cat2));
		p3.getCategorias().addAll(Arrays.asList(cat1));

		categoriaRepository.saveAll(Arrays.asList(cat1,cat2));
		produtoRepository.saveAll(Arrays.asList(p1,p2,p3));

		Estado est1 = new Estado(null, "Minas Gerais");
		Estado est2 = new Estado(null, "São paulo");

		Cidade c1 = new Cidade(null, "Uberlandia", est1);
		Cidade c2 = new Cidade(null, "São paulo", est2);
		Cidade c3 = new Cidade(null, "Campinas", est2);

		est1.getCidades().addAll(Arrays.asList(c1));
		est2.getCidades().addAll(Arrays.asList(c2,c3));

		estadoRepository.saveAll(Arrays.asList(est1,est2));
		cidadeRepository.saveAll(Arrays.asList(c1,c2,c3));

		Cliente cli1 = new Cliente(null, "Maria Orvalho", "maria@gmail.com", "1294801295", TipoCliente.PESSOA_FISICA);
		cli1.getTelefones().addAll(Arrays.asList("21123241", "21315161"));

		Endereco e1 = new Endereco(null, "Rua FLores", "11", "apto 303","Praia da Jostas", "290923-333",c1, cli1);
		Endereco e2 = new Endereco(null, "Avenida Matos ", "333", "apto 1122","Jardem da Penha", "12342-333",c2, cli1);

		clienteRepository.saveAll(Arrays.asList(cli1));
		enderecoRepository.saveAll(Arrays.asList(e1,e2));
	}


}
