package com.restaurant.administrator;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.util.Objects;

public class OrderListController {

    @FXML
    private Button returnButton;

    public void initialize() throws Exception{

    }

    public void refreshOrders(ActionEvent actionEvent) {
    }

    public void searchOrder(ActionEvent actionEvent) {
    }

    public void return_back(ActionEvent event) throws Exception {
        try {
            Stage stage = (Stage) returnButton.getScene().getWindow(); // Ottieni il riferimento alla finestra di AdministratorDish
            stage.close(); // Chiudi la finestra di AdministratorDish

            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("AdministratorView.fxml")));
            Scene admOrders = new Scene(root);

            Label usernameLabel = (Label) admOrders.lookup("#txtUsername");
            usernameLabel.setText(AdministratorController.getUsername());

            Stage window = new Stage();
            window.setScene(admOrders);
            window.show();
        }
        catch (Exception e) {
            System.out.println("Error occurred while opening Administrator page");
            throw e;
        }
    }
}
