package br.usp.poli.magnodb.controller;

import br.usp.poli.magnodb.model.Context;
import br.usp.poli.magnodb.model.Usuario;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class MainController {

    Usuario usuario;

    @FXML
    Label welcomeText;

    public StringProperty usernameProperty() {
        return  new SimpleStringProperty(usuario.getUsername());
    }

    public MainController() {
        usuario = Context.getInstance().getUsuario();
    }

    @FXML
    public void initialize() {
        welcomeText.setText("Bem-vindo, " + usuario.getUsername());
    }
}
