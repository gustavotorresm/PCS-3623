package br.usp.poli.magnodb.controller;

import br.usp.poli.magnodb.model.*;
import br.usp.poli.magnodb.model.dao.EntregaDAO;
import br.usp.poli.magnodb.model.dao.ItemDAO;
import br.usp.poli.magnodb.model.dao.PedidoDAO;
import br.usp.poli.magnodb.model.dao.VendaDAO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by mateus on 08/12/16.
 */


public class cadastrarVendaEntregaController {
    @FXML
    TextField tf_endereco;

    @FXML
    TextField tf_frete;

    @FXML
    DatePicker dp_despache;

    @FXML
    DatePicker dp_recepcao;

    public void cadastrar(ActionEvent e) {
        String endereco = tf_endereco.getText();
        float frete = Float.parseFloat(tf_frete.getText());
        LocalDate l = dp_despache.getValue();
        Calendar c = Calendar.getInstance();
        c.set(l.getYear(), l.getMonthValue(), l.getDayOfMonth());
        Date despache = c.getTime();

        l = dp_recepcao.getValue();
        c.set(l.getYear(), l.getMonthValue(), l.getDayOfMonth());
        Date recepcao = c.getTime();

        Entrega entrega = new Entrega(endereco, frete, despache, recepcao);
        int id = EntregaDAO.getInstance().cadastrarEntrega(entrega);
        entrega.setId(id);

        Pedido pedido = Context.getInstance().getPedido();
        id = PedidoDAO.getInstance().cadastrarPedido(pedido);
        pedido.setId(id);

        List<Item> itens = Context.getInstance().getItens();
        for (Item item : itens) {
            item.setPedido(pedido);
            ItemDAO.getInstance().cadastrarItem(item);
        }

        Venda venda = Context.getInstance().getVenda();
        Cliente cliente = Context.getInstance().getCliente();
        venda.setCliente(cliente);
        venda.setEntrega(entrega);

        VendaDAO.getInstance().cadastrarVenda(venda);

        Node node = (Node) e.getSource();
        Stage stage = (Stage) node.getScene().getWindow();

        try {
            Parent root = FXMLLoader.load(getClass().getResource("/view/main.fxml"));
            Scene scene = new Scene(root);

            stage.setScene(scene);
            stage.show();
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }
}
