package br.usp.poli.magnodb.model;

import java.util.Date;

/**
 * Created by mateus on 06/12/16.
 */
public class Venda {
    private Cliente cliente;
    private Entrega entrega;
    private Pedido pedido;
    private Date dataPagamento;
    private float desconto;
    private String notaFiscal;


    public Venda(Cliente cliente, Entrega entrega, Pedido pedido, Date dataPagamento, float desconto, String notaFiscal) {
        this.cliente = cliente;
        this.entrega = entrega;
        this.pedido = pedido;
        this.dataPagamento = dataPagamento;
        this.desconto = desconto;
        this.notaFiscal = notaFiscal;
    }

    public Venda(){}

    public void setCliente(Cliente cliente) {this.cliente = cliente;}

    public Cliente getCliente() {
        return cliente;
    }

    public Entrega getEntrega() {
        return entrega;
    }

    public Pedido getPedido() {
        return pedido;
    }

    public Date getDataPagamento() {
        return dataPagamento;
    }

    public float getDesconto() {
        return desconto;
    }

    public String getNotaFiscal() {
        return notaFiscal;
    }

    public void setEntrega(Entrega entrega) {
        this.entrega = entrega;
    }
}
