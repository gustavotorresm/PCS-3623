package br.usp.poli.magnodb.controller;

import br.usp.poli.magnodb.model.*;
import br.usp.poli.magnodb.model.dao.ProdutoDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;

import java.io.IOException;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by mateus on 08/12/16.
 */
public class cadastrarVendaPedidoController {

    @FXML
    ListView lv_produtos;

    @FXML
    TextField tf_quantidade;

    List<Item> itens = new LinkedList<>();

    Pedido pedido;

    @FXML
    public void initialize() {
        ObservableList data = FXCollections.observableArrayList();
        ProdutoDAO dao = ProdutoDAO.getInstance();
        List<Produto> produtos = dao.listarTodosProdutos();
        for (Produto produto : produtos) {
            data.add(produto.getId()+"-"+produto.getNome());
        }
        lv_produtos.setItems(data);

        pedido = new Pedido(new Date());
    }

    public void adicionarItem(ActionEvent e) {
        int idProduto = Integer.parseInt(lv_produtos.getSelectionModel().getSelectedItem().toString().split("-")[0]);
        Produto produto = ProdutoDAO.getInstance().buscarProduto(idProduto);
        int quantidade = Integer.parseInt(tf_quantidade.getText());

        Item item = new Item(produto, pedido, quantidade, "1º lote");
        itens.add(item);

        lv_produtos.getSelectionModel().clearSelection();
        tf_quantidade.setText("");
    }

    public void finalizar(ActionEvent e) {
        Context.getInstance().setItens(itens);
        Context.getInstance().setPedido(pedido);

        try {
            GridPane root = FXMLLoader.load(getClass().getResource("/view/cadastrarVenda/entrega.fxml"));
            AnchorPane content = Context.getInstance().getContentPane();

            content.getChildren().clear();
            content.getChildren().add(root);
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }
}
