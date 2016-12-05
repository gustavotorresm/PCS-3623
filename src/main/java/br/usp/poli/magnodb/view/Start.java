package br.usp.poli.magnodb.view;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Start extends Application{
    public void start(Stage primaryStage) throws Exception {
        System.out.println(getClass().getResource(""));
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/start.fxml"));

        primaryStage.setTitle("MagnoDB");
        primaryStage.setFullScreen(true);

        primaryStage.setScene(new Scene(root, 300, 300));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
