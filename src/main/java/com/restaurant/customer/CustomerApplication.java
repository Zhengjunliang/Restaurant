package com.restaurant.customer;

import com.restaurant.Main;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.util.Objects;

public class CustomerApplication extends Application {

    private final String username;

    public CustomerApplication(String username) {
        this.username = username;
    }

    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("CustomerView.fxml")));
        Scene scene = new Scene(root);
        primaryStage.setTitle("Customer");
        Label usernameLabel = (Label) scene.lookup("#txtUsername");
        usernameLabel.setText(username);
        primaryStage.setScene(scene);
        primaryStage.show();

        primaryStage.setOnCloseRequest(event -> {
            event.consume();
            Main.logout(primaryStage);
        });
    }
}
