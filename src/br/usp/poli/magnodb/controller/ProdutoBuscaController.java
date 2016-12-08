package br.usp.poli.magnodb.controller;

import br.usp.poli.magnodb.model.Context;
import br.usp.poli.magnodb.model.Produto;
import br.usp.poli.magnodb.model.dao.ProdutoDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public class ProdutoBuscaController {

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
        if (idField.getText().trim().isEmpty() && nomeField.getText().trim().isEmpty()) return;

        int id = 0;
        String nome = "";

        if (!idField.getText().trim().isEmpty()) {
            id = Integer.valueOf(idField.getText().trim());
        }

        if (!nomeField.getText().trim().isEmpty()) {
            nome = nomeField.getText();

        }

        Produto produto = null;
        ProdutoDAO dao = ProdutoDAO.getInstance();
        if (id != 0) {
            produto = dao.buscarProduto(id);
        } else {
            Optional<Produto> optional = dao.buscarProduto(nome).stream().findAny();
            if (optional.isPresent()) {
                produto = optional.get();
            }
        }

        if (produto == null) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Busca por produto");
            alert.setHeaderText(null);
            alert.setContentText("Não possível encontrar o produto especificado. Por favor, verifique se as informações estão corretas");

            alert.show();
            return;
        }

        Context.getInstance().setProduto(produto);
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/view/produto/produto.fxml"));
            AnchorPane content = Context.getInstance().getContentPane();

            content.getChildren().clear();
            content.getChildren().add(root);
        } catch (IOException e1) {
            e1.printStackTrace();
        }

    }
}
