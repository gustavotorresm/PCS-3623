package br.usp.poli.magnodb.controller;

import br.usp.poli.magnodb.model.Context;
import br.usp.poli.magnodb.model.Produto;
import br.usp.poli.magnodb.model.dao.ProdutoDAO;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class ProdutoCadastrarController {

    @FXML
    TextField nomeField, precoField;

    @FXML
    TextArea descricaoArea;

    public void salvar() {
        String nome = nomeField.getText();
        Float preco = Float.valueOf(precoField.getText());
        String descricao = descricaoArea.getText();

        Produto produto = new Produto(nome, descricao, preco);

        ProdutoDAO dao = ProdutoDAO.getInstance();
        dao.cadastrarProduto(produto);

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Cadastro");
        alert.setContentText("Cadastro realizado com sucesso");

        alert.showAndWait();

        try {
            Parent root = FXMLLoader.load(getClass().getResource("/view/produto/buscar.fxml"));
            AnchorPane content = Context.getInstance().getContentPane();
            content.getChildren().clear();

            content.getChildren().add(root);
            root.prefHeight(content.getHeight());

        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }

}
