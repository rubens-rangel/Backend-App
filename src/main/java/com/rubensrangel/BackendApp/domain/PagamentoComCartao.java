package com.rubensrangel.BackendApp.domain;

import com.fasterxml.jackson.annotation.JsonTypeName;
import com.rubensrangel.BackendApp.domain.enums.EstadoPagamento;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;

@Data
@NoArgsConstructor
@Entity
@JsonTypeName("pagamentoComCartao")
public class PagamentoComCartao extends Pagamento {
    private static final long serialVersionUID = 1L;

   private Integer numeroDeParcelas;

    public PagamentoComCartao(Integer id, EstadoPagamento estado, Pedido pedio, Integer numeroDeParcelas) {
        super(id, estado, pedio);
        this.numeroDeParcelas = numeroDeParcelas;
    }
}
