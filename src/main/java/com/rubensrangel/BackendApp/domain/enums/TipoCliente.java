package com.rubensrangel.BackendApp.domain.enums;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public enum TipoCliente {

    PESSOA_FISICA(1, "Pessoa Física"),
    PESSOA_JURIDICA(2, "Pessoa Juridica");

    private int cod;
    private String desc;

    private TipoCliente(int cod, String descricao) {
        this.cod = cod;
        this.desc = descricao;
    }

    public int getCod() {
        return cod;
    }

    public String getDesc() {
        return desc;
    }


    public static TipoCliente toEnum(Integer cod) {
        if (cod == null) {
            return null;
        }
        for (TipoCliente x : TipoCliente.values()) {
            if (cod.equals(x.getCod())) {
                return x;
            }
        }
        throw new IllegalArgumentException("ID invalido: " + cod);
    }
}
