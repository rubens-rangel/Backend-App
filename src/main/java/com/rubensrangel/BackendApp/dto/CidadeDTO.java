package com.rubensrangel.BackendApp.dto;

import com.rubensrangel.BackendApp.domain.Categoria;
import com.rubensrangel.BackendApp.domain.Cidade;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

@NoArgsConstructor
@Data
public class CidadeDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer id;
    @NotEmpty(message = "O campo nome não pode ser nulo")
    @Length(min=5, max=80, message="O tamanho deve ser entre 5 e 80 caracteres")
    private String nome;

    public CidadeDTO(Cidade obj) {
        id = obj.getId();
        nome = obj.getNome();
    }
}
