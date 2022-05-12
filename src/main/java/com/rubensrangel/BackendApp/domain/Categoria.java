package com.rubensrangel.BackendApp.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Categoria implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    private String nome;

}
