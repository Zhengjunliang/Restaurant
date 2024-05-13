package com.restaurant.command;

import javafx.event.Event;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.Objects;

public class AdmToOrderCommand implements Command{
    @Override
    public void execute(Event event) throws Exception{
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
}
