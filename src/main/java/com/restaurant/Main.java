package com.restaurant;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.util.Objects;

public class Main extends Application {

    private static Stage stage;

    @Override
    public void start(Stage stage) throws Exception {
        Main.stage = stage;
        stage.setTitle("Restaurant");
        changeView("loginView.fxml");
        stage.show();
    }

    public static void changeView(String fxml) throws Exception {

        Parent root = FXMLLoader.load(Objects.requireNonNull(Main.class.getResource(fxml)));
        stage.setScene(new Scene(root));
    }


    public static void main(String[] args) {
        launch(args);
    }
}