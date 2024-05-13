package com.restaurant;

import com.restaurant.DBUtil.DBUtil;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;
import java.util.Objects;
import java.util.Optional;

public class Main extends Application {

    @Override
    public void start(Stage stage) throws Exception {

        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("LoginView.fxml")));
        Scene scene = new Scene(root);
        DBUtil.setStage(stage);
        stage.setTitle("Restaurant");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();

        stage.setOnCloseRequest(event -> {
            event.consume();
            Main.logout(stage);
        });
    }

    public static void logout(Stage stage) {

        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Logout");
        alert.setHeaderText("The Application is about to get closed.");
        alert.setContentText("Do you really want to log out?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            stage.close();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}