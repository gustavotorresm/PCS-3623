package br.usp.poli.magnodb.model;

/**
 * Created by mateus on 06/12/16.
 */
public class Item {
    private Produto produto;
    private Pedido pedido;

    private float quantidade;
    private String lote;

    public Item(Produto produto, Pedido pedido, float quantidade, String lote) {
        this.produto = produto;
        this.pedido = pedido;
        this.quantidade = quantidade;
        this.lote = lote;
    }

    public Produto getProduto() {
        return produto;
    }

    public Pedido getPedido() {
        return pedido;
    }

    public float getQuantidade() {
        return quantidade;
    }

    public String getLote() {
        return lote;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }
}
