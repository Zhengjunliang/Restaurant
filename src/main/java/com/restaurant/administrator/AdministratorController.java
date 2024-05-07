package com.restaurant.administrator;

import com.restaurant.login.LoginController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.Objects;

public class AdministratorController {

    @FXML private static Label txtUsername;
    private static String username = "";


    // Metodo di set per txtUsername
    @FXML
    public static void setUsername(String username) {
        if (username != null) {
            AdministratorController.username = username;
        }
    }

    public static String getUsername() {
        return username;
    }

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

    public void admStaff(ActionEvent event) throws Exception {
        try {
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("AdministratorStaff.fxml")));
            Scene admStaff = new Scene(root);
            //get stage information
            Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
            window.setScene(admStaff);
            window.show();
        }
        catch (Exception e) {
            System.out.println("Error occurred while opening Staff page");
            throw e;
        }
    }

    public void admOrders(ActionEvent event) throws Exception {
        try{
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("AdministratorOrders.fxml")));
            Scene admOrders = new Scene(root);

            Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
            window.setScene(admOrders);
            window.show();
        }
        catch (Exception e) {
            System.out.println("Error occurred while opening Orders page");
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
