package br.usp.poli.magnodb.controller;

import br.usp.poli.magnodb.model.Context;
import br.usp.poli.magnodb.model.dao.EntregaDAO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;

import java.io.IOException;

/**
 * Created by mateus on 07/12/16.
 */
public class PesquisarEntregaController {
    @FXML
    TextField tf_cliente;

    @FXML
    AnchorPane content;

    @FXML
    public void initialize() {
    }

    public void buscarEntrega(ActionEvent ae) {
        EntregaDAO dao = EntregaDAO.getInstance();
        Context.getInstance().setEntregas(dao.buscarPorCliente(tf_cliente.getText()));
        try {
            ScrollPane root = FXMLLoader.load(getClass().getResource("/view/entrega/lista.fxml"));
            AnchorPane content = Context.getInstance().getContentPane();

            content.getChildren().clear();
            content.getChildren().add(root);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
