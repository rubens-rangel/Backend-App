package com.rubensrangel.BackendApp.services;

import com.rubensrangel.BackendApp.domain.Estado;
import com.rubensrangel.BackendApp.dto.EstadoDTO;
import com.rubensrangel.BackendApp.repositories.EstadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EstadoService {

    @Autowired
    private EstadoRepository repo;

    public List<Estado> findAll() {
        return  repo.findAllByOrderByNome();
    }

}
