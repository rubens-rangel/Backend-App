package com.rubensrangel.BackendApp.services;

import com.rubensrangel.BackendApp.domain.ItemPedido;
import com.rubensrangel.BackendApp.domain.PagamentoComBoleto;
import com.rubensrangel.BackendApp.domain.Pedido;
import com.rubensrangel.BackendApp.domain.enums.EstadoPagamento;
import com.rubensrangel.BackendApp.repositories.ItemPedidoRepository;
import com.rubensrangel.BackendApp.repositories.PagamentoRepository;
import com.rubensrangel.BackendApp.repositories.PedidoRepository;
import com.rubensrangel.BackendApp.repositories.ProdutoRepository;
import com.rubensrangel.BackendApp.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
public class PedidoService {
    @Autowired
    private PedidoRepository repo;

    @Autowired
    private  BoletoService boletoService;

    @Autowired
    private PagamentoRepository pagamentoRepository;

    @Autowired
    private ProdutoService produtoService;

    @Autowired
    private ItemPedidoRepository itemPedidoRepository;

    public Pedido find(Integer id) {
        Optional<Pedido> obj = repo.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! Id: " + id + ", Tipo: " + Pedido.class.getName()));
    }

    public Pedido insert(Pedido obj) {
        obj.setId(null);
        obj.setInstante(new Date());
        obj.getPagamento().setEstado(EstadoPagamento.PENDENTE.getCod());
        obj.getPagamento().setPedido(obj);
        if( obj.getPagamento() instanceof PagamentoComBoleto){
            PagamentoComBoleto pagto = (PagamentoComBoleto) obj.getPagamento();
            boletoService.preencherPagamentoComBoleto(pagto,obj.getInstante());
        }
        obj = repo.save(obj);
        pagamentoRepository.save(obj.getPagamento());
        for(ItemPedido ip : obj.getItens()){
            ip.setDesconto(0.0);
            ip.setProduto(produtoService.find(ip.getProduto().getId()));
            ip.setPreco(ip.getProduto().getPreco());
            ip.setPedido(obj);
        }
        itemPedidoRepository.saveAll(obj.getItens());
        return obj;
    }
}
