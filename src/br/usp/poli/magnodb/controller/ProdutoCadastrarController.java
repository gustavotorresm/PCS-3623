package br.usp.poli.magnodb.controller;

import br.usp.poli.magnodb.model.Produto;
import br.usp.poli.magnodb.model.dao.ProdutoDAO;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

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
    }

}
