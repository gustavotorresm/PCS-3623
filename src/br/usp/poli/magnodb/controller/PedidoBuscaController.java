package br.usp.poli.magnodb.controller;

import java.io.IOException;

import br.usp.poli.magnodb.model.Context;
import br.usp.poli.magnodb.model.Pedido;
import br.usp.poli.magnodb.model.Usuario;
import br.usp.poli.magnodb.model.dao.PedidoDAO;
import br.usp.poli.magnodb.model.dao.UsuarioDAO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class PedidoBuscaController {

    @FXML
    TextField idField;

    @FXML
    public void initialize() {
    }

    public PedidoBuscaController() {
    }
    
    public void buscar(ActionEvent e) {
        int id = Integer.valueOf(idField.getText().trim());

        PedidoDAO pdao = PedidoDAO.getInstance();
        Pedido pedido = null;
        pedido = pdao.buscarPedido(id);

        if (pedido == null) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Busca por pedido");
            alert.setHeaderText(null);
            alert.setContentText("Não possível encontrar o pedido especificado. Por favor, verifique se as informações estão corretas");
            alert.show();
            return;
        }

        Node node = (Node) e.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        Context.getInstance().setPedido(pedido);
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/view/pedido/main.fxml"));
            Scene scene = new Scene(root);

            stage.setScene(scene);
            stage.show();
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }
}
