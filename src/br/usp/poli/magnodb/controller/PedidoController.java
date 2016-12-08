package br.usp.poli.magnodb.controller;

import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.Optional;

import br.usp.poli.magnodb.model.Context;
import br.usp.poli.magnodb.model.Pedido;
import br.usp.poli.magnodb.model.Produto;
import br.usp.poli.magnodb.model.dao.PedidoDAO;
import br.usp.poli.magnodb.model.dao.ProdutoDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextInputDialog;

public class PedidoController {

	@FXML
    Label idField;
	
    @FXML
    Label dataField;
    
    @FXML
    Label clienteField;
    
    @FXML
    Label entregaField;

    private Pedido pedido;

    public PedidoController() {
        pedido = Context.getInstance().getPedido();
    }

    @FXML
    public void initialize() {
        dataField.setText("Data do pedido: " + pedido.getData().toString());
        
        PedidoDAO dao = PedidoDAO.getInstance();
        clienteField.setText("Cliente: " + dao.buscarCliente(pedido.getId()));
        
        idField.setText("ID do pedido" + String.valueOf(pedido.getId()));
        entregaField.setText("ID da entrega: " + dao.buscarEntrega(pedido.getId()));
    }
}
