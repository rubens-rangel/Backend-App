package com.rubensrangel.BackendApp.repositories;

import com.rubensrangel.BackendApp.domain.Cidade;
import com.rubensrangel.BackendApp.domain.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Integer> {
}
