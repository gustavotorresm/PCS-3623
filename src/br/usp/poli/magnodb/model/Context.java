package br.usp.poli.magnodb.model;

import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.util.List;

public class Context {

    private Usuario usuario;
    private AnchorPane contentPane;
    private Stage stage;
    private Produto produto;
    private List<Entrega> entregas;
    private Venda venda;

    private static Context ourInstance = new Context();
    private List<Item> itens;
    private Pedido pedido;
    private Cliente cliente;

    public static Context getInstance() {
        return ourInstance;
    }

    private Context() {
    }

    public Usuario getUsuario() {
        return  usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public void setContentPane(AnchorPane contentPane) {
        this.contentPane = contentPane;
    }

    public AnchorPane getContentPane() {
        return contentPane;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public Stage getStage() {
        return stage;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public List<Entrega> getEntregas() {
        return entregas;
    }

    public void setEntregas(List<Entrega> entregas) {
        this.entregas = entregas;
    }



    public Cliente getCliente() {
        return this.cliente;
    }

    public void setVenda(Venda venda) {
        this.venda = venda;
    }

    public void setItens(List<Item> itens) {
        this.itens = itens;
    }

    public List<Item> getItens() {
        return itens;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }

    public Pedido getPedido() {
        return pedido;
    }

    public Venda getVenda() {
        return venda;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }
}
