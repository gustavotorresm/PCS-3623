package br.usp.poli.magnodb.controller;

import br.usp.poli.magnodb.model.Context;
import br.usp.poli.magnodb.model.Usuario;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;

import java.io.IOException;

public class MainController {

    Usuario usuario;

    @FXML
    AnchorPane content;

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
        Context.getInstance().setContentPane(content);
        welcomeText.setText("Bem-vindo, " + usuario.getUsername());
    }

    public void buscarProduto(ActionEvent e) {
        try {
            Parent root = (GridPane) FXMLLoader.load(getClass().getResource("/view/produto/buscar.fxml"));
            content.getChildren().clear();

            content.getChildren().add(root);
            root.prefHeight(content.getHeight());

        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }

    public void buscarCliente(ActionEvent e) {
        try {
            Parent root = (GridPane) FXMLLoader.load(getClass().getResource("/view/cliente/pesquisar.fxml"));
            content.getChildren().clear();

            content.getChildren().add(root);
            root.prefHeight(content.getHeight());

        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }

    public void cadastrarProduto(ActionEvent e) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/view/produto/cadastrar.fxml"));
            content.getChildren().clear();

            content.getChildren().add(root);
            root.prefHeight(content.getHeight());

        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }

    public void cadastrarClienteFisico(ActionEvent e) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/view/cliente/cadastrarClienteFisico.fxml"));
            content.getChildren().clear();

            content.getChildren().add(root);
            root.prefHeight(content.getHeight());

        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }


    public void cadastrarClienteJuridico(ActionEvent e) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/view/cliente/cadastrarClienteJuridico.fxml"));
            content.getChildren().clear();

            content.getChildren().add(root);
            root.prefHeight(content.getHeight());

        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }

    public void estatisticaEntrega(ActionEvent e) {
        try {
            Parent root = (GridPane) FXMLLoader.load(getClass().getResource("/view/entrega/estatistica.fxml"));
            content.getChildren().clear();

            content.getChildren().add(root);
            root.prefHeight(content.getHeight());

        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }

    public void pesquisarEntrega(ActionEvent e) {
        try {
            Parent root = (GridPane) FXMLLoader.load(getClass().getResource("/view/entrega/pesquisar.fxml"));
            content.getChildren().clear();

            content.getChildren().add(root);
            root.prefHeight(content.getHeight());

        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }

    public void pesquisarPedido(ActionEvent e) {
        try {
            Parent root = (GridPane) FXMLLoader.load(getClass().getResource("/view/pedido/busca.fxml"));
            content.getChildren().clear();

            content.getChildren().add(root);
            root.prefHeight(content.getHeight());

        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }


    public void cadastrarVenda(ActionEvent e) {
        try {
            Parent root = (GridPane) FXMLLoader.load(getClass().getResource("/view/cadastrarVenda/cliente.fxml"));
            content.getChildren().clear();
            content.getChildren().add(root);
            root.prefHeight(content.getHeight());

        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }
}
