package br.usp.poli.magnodb.controller;

import br.usp.poli.magnodb.model.Context;
import br.usp.poli.magnodb.model.Juridico;
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
public class ClienteCadastrarJuridicoController {
    @FXML
    TextField nomeField, emailField, enderecoField, telefoneField,cnpjField, porteField, tipoField;

    @FXML
    TextArea descricaoArea;

    public void salvar() {
        String nome = nomeField.getText();
        String email= emailField.getText();
        String endereco = enderecoField.getText();
        String telefone = telefoneField.getText();
        String cnpj = cnpjField.getText();
        String porte = porteField.getText();
        String tipo = tipoField.getText();

        Juridico cliente = new Juridico(nome, email,endereco,telefone,cnpj,porte,tipo);

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
