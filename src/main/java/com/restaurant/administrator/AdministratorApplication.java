package com.restaurant.administrator;

import com.restaurant.login.LoginController;
import com.restaurant.Main;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.util.Objects;

public class AdministratorApplication extends Application {

    private final String username;

    public AdministratorApplication(String username) throws Exception {

        this.username = username;

    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("AdministratorView.fxml")));
        Scene scene = new Scene(root);
        primaryStage.setTitle("Administrator");
        Label usernameLabel = (Label) scene.lookup("#txtUsername");
        usernameLabel.setText(username);
        AdministratorController.setUsername(username);
        primaryStage.setScene(scene);
        primaryStage.show();

        primaryStage.setOnCloseRequest(event -> {
            event.consume();
            Main.logout(primaryStage);
        });
    }

}
