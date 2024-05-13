package com.restaurant.customer;

import com.restaurant.Main;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.Objects;

public class CustomerApplication extends Application {

    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("CustomerView.fxml")));
        Scene scene = new Scene(root);
        primaryStage.setTitle("Customer");
        primaryStage.setScene(scene);
        primaryStage.show();

        primaryStage.setOnCloseRequest(event -> {
            event.consume();
            Main.logout(primaryStage);
        });
    }
}
