package com.rubensrangel.BackendApp.resources;

import com.rubensrangel.BackendApp.domain.Categoria;
import com.rubensrangel.BackendApp.domain.Produto;
import com.rubensrangel.BackendApp.dto.CategoriaDTO;
import com.rubensrangel.BackendApp.dto.ProdutoDTO;
import com.rubensrangel.BackendApp.resources.utils.URL;
import com.rubensrangel.BackendApp.services.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/produtos")
public class ProdutoResource {


    @Autowired
    private ProdutoService produtoService;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<Produto>  find(@PathVariable Integer id){
        Produto obj = produtoService.find(id);

        return ResponseEntity.ok().body(obj);
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<Page<ProdutoDTO>> findPage(@RequestParam(value = "nome", defaultValue = "0") String nome,
                                                     @RequestParam(value = "categorias", defaultValue = "0") String categorias,
                                                     @RequestParam(value = "page", defaultValue = "0") Integer page,
                                                     @RequestParam(value = "linesPerPage", defaultValue = "24") Integer linesPerPage,
                                                     @RequestParam(value = "orderBy", defaultValue = "nome") String orderBy,
                                                     @RequestParam(value = "direction", defaultValue = "ASC") String direction) {
        String nomeDecoded = URL.decodeParam(nome);
        List<Integer> ids = URL.decodeIntList(categorias);
        Page<Produto> produtoList = produtoService.search(nome ,ids,page, linesPerPage, orderBy, direction);
        Page<ProdutoDTO> produtoDTOListt = produtoList.map(obj -> new ProdutoDTO(obj));
        return ResponseEntity.ok().body(produtoDTOListt);
    }
}
