package com.rubensrangel.BackendApp.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.rubensrangel.BackendApp.domain.enums.EstadoPagamento;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
public class PagamentoComBoleto extends Pagamento {
    private static final long serialVersionUID = 1L;

   private Date dataVencimento;
   private Date dataPagamento;


    public PagamentoComBoleto(Integer id, EstadoPagamento estado, Pedido pedio, Date dataVencimento, Date dataPagamento) {
        super(id, estado, pedio);
        this.dataVencimento = dataVencimento;
        this.dataPagamento = dataPagamento;
    }
}
