package br.usp.poli.magnodb.model;

import javafx.scene.layout.AnchorPane;

public class Context {

    private Usuario usuario;
    private AnchorPane contentPane;

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
}
