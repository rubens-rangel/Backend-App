package com.rubensrangel.BackendApp.dto;

import com.rubensrangel.BackendApp.domain.Estado;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@NoArgsConstructor
@Data
public class EstadoDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer id;
    private String nome;

    public EstadoDTO(Estado obj) {
        id = obj.getId();
        nome = obj.getNome();
    }
}
