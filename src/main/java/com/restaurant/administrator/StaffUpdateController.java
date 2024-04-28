package com.restaurant.administrator;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

import java.sql.SQLException;

public class StaffUpdateController {
    @FXML private TextField txtId;
    @FXML private TextField txtName;
    @FXML private TextField txtSalary;
    @FXML private TextField txtAge;
    @FXML private TextField txtRole;
    @FXML private ChoiceBox<String> ckbGender;
    @FXML private DatePicker dpDate;
    @FXML private static Staff current;

    public static void setCurrent(Staff staff) {
        StaffUpdateController.current=staff;
    }
    @FXML
    public void initialize() {
        ckbGender.getItems().clear();
        ckbGender.getItems().addAll("Male", "Female");

        txtId.setText(Integer.toString(current.getId()));
        txtId.setEditable(false);
        txtName.setText(current.getName());
        ckbGender.setValue(current.getGender());
        txtSalary.setText(current.getSalary());
        txtAge.setText(Integer.toString(current.getAge()));
        txtRole.setText(current.getRole());
        dpDate.getEditor().setText(current.getDate());
    }


    public void modifyStaff(ActionEvent event) throws ClassNotFoundException, SQLException {
        try {
            StaffDAO.updateStaffData(Integer.parseInt(txtId.getText()), txtName.getText(), ckbGender.getValue(), Integer.parseInt(txtAge.getText()), txtRole.getText(),txtSalary.getText(), dpDate.getEditor().getText());

        }
        catch (SQLException e) {
            System.out.println("Exception occurs in Insertion " + e);
            throw e;
        }
    }

}
