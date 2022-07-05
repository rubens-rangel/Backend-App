package com.rubensrangel.BackendApp.resources;

import com.rubensrangel.BackendApp.domain.Categoria;
import com.rubensrangel.BackendApp.dto.CategoriaDTO;
import com.rubensrangel.BackendApp.services.CategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/categories")
public class CategoriaResource {

    @Autowired
    private CategoriaService categoriaService;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<Categoria> find(@PathVariable Integer id) {
        Categoria obj = categoriaService.find(id);

        return ResponseEntity.ok().body(obj);
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<CategoriaDTO>> findAll() {
        List<Categoria> categoriaList = categoriaService.findAll();
        List<CategoriaDTO> categoriaDTOList = categoriaList.stream().map(obj -> new CategoriaDTO(obj)).collect(Collectors.toList());
        return ResponseEntity.ok().body(categoriaDTOList);
    }

    @RequestMapping(value = "/page", method = RequestMethod.GET)
    public ResponseEntity<Page<CategoriaDTO>> findPage(@RequestParam(value = "page", defaultValue = "0") Integer page,
                                                       @RequestParam(value = "linesPerPage", defaultValue = "24") Integer linesPerPage,
                                                       @RequestParam(value = "orderBy", defaultValue = "nome") String orderBy,
                                                       @RequestParam(value = "direction", defaultValue = "ASC") String direction) {
        Page<Categoria> categoriaList = categoriaService.findPage(page, linesPerPage, orderBy, direction);
        Page<CategoriaDTO> categoriaDTOListt = categoriaList.map(obj -> new CategoriaDTO(obj));
        return ResponseEntity.ok().body(categoriaDTOListt);
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Void> insert(@Valid @RequestBody CategoriaDTO objDTO) {
        Categoria obj = categoriaService.fromDTO(objDTO);
        obj = categoriaService.insert(obj);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Void> update(@Valid @RequestBody CategoriaDTO objDto, @PathVariable Integer id ) {
        Categoria obj = categoriaService.fromDTO(objDto);
        obj.setId(id);
        obj = categoriaService.update(obj);
        return ResponseEntity.noContent().build();
    }


    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        categoriaService.delete(id);
        return ResponseEntity.noContent().build();
    }
}


