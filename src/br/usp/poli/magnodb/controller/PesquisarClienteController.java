package br.usp.poli.magnodb.controller;

import br.usp.poli.magnodb.model.Context;
import br.usp.poli.magnodb.model.dao.ClienteDAO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

/**
 * Created by mateus on 07/12/16.
 */
public class PesquisarClienteController {
    @FXML
    TextField tf_cliente;

    @FXML
    AnchorPane content;

    @FXML
    public void initialize() {
    }

    public void buscarCliente(ActionEvent actionEvent) {
        ClienteDAO dao = ClienteDAO.getInstance();
        Context.getInstance().setCliente(dao.buscarCliente(Integer.parseInt(tf_cliente.getText())));
        try {
            ScrollPane root = FXMLLoader.load(getClass().getResource("/view/cliente/lista.fxml"));
            AnchorPane content = Context.getInstance().getContentPane();

            content.getChildren().clear();
            content.getChildren().add(root);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void buscarVendasCliente(ActionEvent actionEvent) {
        ClienteDAO dao = ClienteDAO.getInstance();

    }
}