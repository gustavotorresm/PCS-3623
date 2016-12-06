package br.usp.poli.magnodb.controller;

import br.usp.poli.magnodb.Main;
import br.usp.poli.magnodb.model.Usuario;
import br.usp.poli.magnodb.model.dao.UsuarioDAO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;

import java.util.Map;

public class Controller {

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

        System.out.println(authorized);
    }
}
