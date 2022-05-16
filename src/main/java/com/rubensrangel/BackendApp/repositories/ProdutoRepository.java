package com.rubensrangel.BackendApp.repositories;

import com.rubensrangel.BackendApp.domain.Categoria;
import com.rubensrangel.BackendApp.domain.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Integer> {
}
