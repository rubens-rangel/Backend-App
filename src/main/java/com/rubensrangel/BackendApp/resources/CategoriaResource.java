package com.rubensrangel.BackendApp.resources;

import com.rubensrangel.BackendApp.domain.Categoria;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/categories")
public class CategoriaResource {


    @RequestMapping(method = RequestMethod.GET)
    public List<Categoria>  listar(){

        Categoria cat1 =new Categoria(null,"Informatica");
        Categoria cat2 =new Categoria(null,"Escritorio");

       List<Categoria> lista = new ArrayList<>();
       lista.add(cat1);
       lista.add(cat2);
        return lista;
    }

}
