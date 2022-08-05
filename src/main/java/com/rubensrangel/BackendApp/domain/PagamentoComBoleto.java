package com.rubensrangel.BackendApp.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonTypeName;
import com.rubensrangel.BackendApp.domain.enums.EstadoPagamento;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import java.util.Date;

@Data
@NoArgsConstructor
@Entity
@JsonTypeName("pagamentoComBoleto")
public class PagamentoComBoleto extends Pagamento {
    private static final long serialVersionUID = 1L;
    @JsonFormat(pattern = "dd/MM/yyyy")
   private Date dataVencimento;
    @JsonFormat(pattern = "dd/MM/yyyy")
   private Date dataPagamento;

    public PagamentoComBoleto(Integer id, EstadoPagamento estado, Pedido pedio, Date dataVencimento, Date dataPagamento) {
        super(id, estado, pedio);
        this.dataVencimento = dataVencimento;
        this.dataPagamento = dataPagamento;
    }
}
