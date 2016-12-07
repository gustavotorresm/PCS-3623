package br.usp.poli.magnodb.controller;

import br.usp.poli.magnodb.model.Produto;
import br.usp.poli.magnodb.model.dao.ProdutoDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableView;

import java.util.List;

public class ProdutoListaController {

    @FXML
    TableView tabela;

    @FXML
    public void initialize() {
        ProdutoDAO dao = ProdutoDAO.getInstance();

        ObservableList<Produto> produtosList = FXCollections.observableArrayList();

        List<Produto> produtos = dao.listarProdutos();
        produtos.forEach(produtosList::add);

        tabela.setItems(produtosList);
    }
}
