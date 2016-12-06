package br.usp.poli.magnodb.controller;

import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;

public class Controller {

    @FXML
    ImageView logo;

    @FXML
    BorderPane root;

    @FXML
    public void initialize() {
        logo.fitWidthProperty().bind(root.widthProperty());
        logo.fitHeightProperty().bind(((StackPane) logo.getParent()).heightProperty());
    }

    public void logoInfo(MouseEvent mouseEvent) {;
    }
}
