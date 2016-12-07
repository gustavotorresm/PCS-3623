package br.usp.poli.magnodb;

import br.usp.poli.magnodb.model.dao.config.DAOSetUp;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application{

    public static void main(String[] args) {
        setUp();
        launch(args);
    }

    private static void setUp() {
        DAOSetUp.productionSetUp();
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/view/login.fxml"));

        primaryStage.setTitle("MagnoDB");
        primaryStage.setScene(new Scene(root, 800, 600));

        primaryStage.show();
    }
}
