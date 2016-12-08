package br.usp.poli.magnodb.controller;

import br.usp.poli.magnodb.model.Cliente;
import br.usp.poli.magnodb.model.Context;
import br.usp.poli.magnodb.model.Venda;
import br.usp.poli.magnodb.model.dao.ClienteDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;

/**
 * Created by mateus on 08/12/16.
 */



public class cadastrarVendaClienteController {
    @FXML
    ListView lv_clientes;

    @FXML
    public void initialize() {
        ObservableList data = FXCollections.observableArrayList();
        ClienteDAO dao = ClienteDAO.getInstance();
        List<Cliente> clientes = dao.listarClientes();
        for (Cliente cliente : clientes) {
           data.add(cliente.getId()+"-"+cliente.getNome());
        }
        lv_clientes.setItems(data);
    }

    public void selecionarCliente(ActionEvent e) {
        int idCliente = Integer.parseInt(lv_clientes.getSelectionModel().getSelectedItem().toString().split("-")[0]);
        Cliente cliente = ClienteDAO.getInstance().buscarCliente(idCliente);

        Venda venda = new Venda();
        venda.setCliente(cliente);
        Context.getInstance().setVenda(venda);
        Context.getInstance().setCliente(cliente);

        try {
            GridPane root = FXMLLoader.load(getClass().getResource("/view/cadastrarVenda/pedido.fxml"));
            AnchorPane content = Context.getInstance().getContentPane();

            content.getChildren().clear();
            content.getChildren().add(root);
        } catch (IOException e1) {
            e1.printStackTrace();
        }

    }
}
