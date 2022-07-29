package com.rubensrangel.BackendApp.dto;

import com.rubensrangel.BackendApp.domain.Produto;
import lombok.Data;

import java.io.Serializable;

@Data
public class ProdutoDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer id;

    private String nome;
    private Double preco;

    public ProdutoDTO(Produto obj) {
        id = obj.getId();
        nome = obj.getNome();
        preco = obj.getPreco();
    }
}
