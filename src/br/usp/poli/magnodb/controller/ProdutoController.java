package br.usp.poli.magnodb.controller;

import br.usp.poli.magnodb.model.Context;
import br.usp.poli.magnodb.model.Produto;
import br.usp.poli.magnodb.model.dao.ProdutoDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ScrollBar;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;

import java.io.IOException;
import java.util.List;

public class ProdutoController {

    @FXML
    GridPane root;

    @FXML
    TextField idField, nomeField;

    @FXML
    public void initialize() {
    }

    public void listar() {
        try {
            ScrollPane root = FXMLLoader.load(getClass().getResource("/view/produto/lista.fxml"));
            AnchorPane content = Context.getInstance().getContentPane();

            content.getChildren().clear();
            content.getChildren().add(root);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void buscar(ActionEvent e) {
    }
}
