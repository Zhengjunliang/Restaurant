package com.restaurant.command;

import javafx.event.Event;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.Objects;

public class AdmToStaffICommand implements ICommand {

    @Override
    public void execute(Event event) throws Exception{
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
}
