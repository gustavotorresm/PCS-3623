package br.usp.poli.magnodb.controller;

import br.usp.poli.magnodb.Main;
import br.usp.poli.magnodb.model.Context;
import br.usp.poli.magnodb.model.Usuario;
import br.usp.poli.magnodb.model.dao.UsuarioDAO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Map;

public class LoginController {

    @FXML
    ImageView logo;

    @FXML
    BorderPane root;

    @FXML
    TextField usernameField;

    @FXML
    PasswordField passwordField;

    @FXML
    public void initialize() {
        logo.fitWidthProperty().bind(root.widthProperty());
    }

    public void logoInfo(MouseEvent mouseEvent) {;
    }

    public void login(ActionEvent e) {
        String username = usernameField.getText();
        String password = passwordField.getText();

        UsuarioDAO dao = UsuarioDAO.getInstance();

        Usuario usuario = dao.buscarUsuario(username);

        if (usuario == null) {
            System.out.println("Usuário não existe");
            return;
        }

        boolean authorized = usuario.login(password);

        if (! authorized) {
            System.out.println("Informações incorretas");
            return;
        }

        Context.getInstance().setUsuario(usuario);

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
