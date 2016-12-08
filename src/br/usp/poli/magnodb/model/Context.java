package br.usp.poli.magnodb.model;

import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class Context {

    private Usuario usuario;
    private AnchorPane contentPane;
    private Stage stage;
    private Produto produto;

    private static Context ourInstance = new Context();

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
}
