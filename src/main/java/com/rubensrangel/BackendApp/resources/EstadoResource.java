package com.rubensrangel.BackendApp.resources;

import com.rubensrangel.BackendApp.domain.Cidade;
import com.rubensrangel.BackendApp.domain.Estado;
import com.rubensrangel.BackendApp.dto.CidadeDTO;
import com.rubensrangel.BackendApp.dto.EstadoDTO;
import com.rubensrangel.BackendApp.services.CidadeService;
import com.rubensrangel.BackendApp.services.EstadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/estados")
public class EstadoResource {


    @Autowired
    private EstadoService estadoService;

    @Autowired
    private CidadeService cidadeService;


    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<EstadoDTO>> findAll() {
        List<Estado> estadoList = estadoService.findAll();
        List<EstadoDTO> estadoDTOList = estadoList.stream().map(obj -> new EstadoDTO(obj)).collect(Collectors.toList());
        return ResponseEntity.ok().body(estadoDTOList);
    }

    @RequestMapping(value = "/{estadoId}/cidades", method = RequestMethod.GET)
    public ResponseEntity<List<CidadeDTO>> findAll(@PathVariable Integer estadoId) {
        List<Cidade> cidadeList = cidadeService.findAll(estadoId);
        List<CidadeDTO> cidadeDTOList = cidadeList.stream().map(obj -> new CidadeDTO(obj)).collect(Collectors.toList());
        return ResponseEntity.ok().body(cidadeDTOList);
    }
}
