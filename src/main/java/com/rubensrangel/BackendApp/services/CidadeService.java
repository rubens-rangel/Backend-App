package com.rubensrangel.BackendApp.services;

import com.rubensrangel.BackendApp.domain.Cidade;
import com.rubensrangel.BackendApp.domain.Estado;
import com.rubensrangel.BackendApp.repositories.CidadeRepository;
import com.rubensrangel.BackendApp.repositories.EstadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CidadeService {

    @Autowired
    private CidadeRepository repo;

    public List<Cidade> findAll(Integer estadoId) {

        return  repo.findCidades(estadoId);
    }

}
