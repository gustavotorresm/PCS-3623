package br.usp.poli.magnodb.controller;

import br.usp.poli.magnodb.model.Context;
import br.usp.poli.magnodb.model.Fisico;
import br.usp.poli.magnodb.model.dao.ClienteDAO;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

/**
 * Created by User on 08/12/2016.
 */
public class ClienteCadastrarFisicoController {
    @FXML
    TextField nomeField, emailField, enderecoField, telefoneField,cpfField, rgField, rendaField;

    @FXML
    TextArea descricaoArea;

    public void salvar() {
        String nome = nomeField.getText();
        String email= emailField.getText();
        String endereco = enderecoField.getText();
        String telefone = telefoneField.getText();
        String cpf = cpfField.getText();
        String rg = rgField.getText();
        String renda = telefoneField.getText();

        Fisico cliente = new Fisico(nome, email,endereco,telefone,cpf,rg,renda);

        ClienteDAO dao = ClienteDAO.getInstance();
        dao.cadastrarCliente(cliente);

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
