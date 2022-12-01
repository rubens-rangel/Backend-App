package com.rubensrangel.BackendApp.domain.enums;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
public enum Perfil {

    PENDENTE(1, "PENDENTE"),
    QUITADO(2, "QUITADO")
    ,CANCELADO(3, "CANCELADO");

    private int cod;
    private String desc;

    public int getCod() {
        return cod;
    }

    public String getDesc() {
        return desc;
    }


    public static Perfil toEnum(Integer cod) {
        if (cod == null) {
            return null;
        }
        for (Perfil x : Perfil.values()) {
            if (cod.equals(x.getCod())) {
                return x;
            }
        }
        throw new IllegalArgumentException("ID invalido: " + cod);
    }
}
