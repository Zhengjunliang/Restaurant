package com.restaurant.administrator;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.Objects;

public class AdministratorController {

    public void admDish(ActionEvent event) throws Exception {
        try {
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("AdministratorDish.fxml")));
            Scene admDish = new Scene(root);
            //get stage information
            Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
            window.setScene(admDish);
            window.show();
        }
        catch (Exception e) {
            System.out.println("Error occurred while opening Dish page");
            throw e;
        }
    }

    public void admLogout(ActionEvent event) throws Exception {
        try {
            System.exit(1);
        }
        catch (Exception e) {
            System.out.println("Error occurred while logging out");
            throw e;
        }
    }
}
