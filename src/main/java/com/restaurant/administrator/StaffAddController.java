package com.restaurant.administrator;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

import java.sql.SQLException;

public class StaffAddController {

    @FXML private TextField txtId;
    @FXML private TextField txtName;
    @FXML private TextField txtSalary;
    @FXML private TextField txtAge;
    @FXML private TextField txtRole;
    @FXML private ChoiceBox<String> ckbGender;
    @FXML private DatePicker dpDate;

    @FXML
    public void initialize() {
        ckbGender.getItems().clear();
        ckbGender.getItems().addAll("Male", "Female");
    }


    public void insertStaff(ActionEvent event) throws ClassNotFoundException, SQLException {
        try {
            StaffDAO.insertStaffData(Integer.parseInt(txtId.getText()), txtName.getText(), ckbGender.getValue(), Integer.parseInt(txtAge.getText()), txtRole.getText(),txtSalary.getText(), dpDate.getValue().toString());

        }
        catch (SQLException e) {
            System.out.println("Exception occurs in Insertion " + e);
            throw e;
        }
    }
}
