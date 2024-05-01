package com.restaurant.administrator;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.sql.SQLException;

public class DishAddController {
    @FXML private TextField txtId;
    @FXML private TextField txtDish;
    @FXML private ChoiceBox<String> ckbCourse;
    @FXML private TextField txtPrice;
    @FXML private DatePicker dpDate;

    @FXML private Button addButton;

    @FXML
    public void initialize() {
        ckbCourse.getItems().clear();
        ckbCourse.getItems().addAll("Appetizer", "First Course", "Second Course", "Dessert", "Water", "Wine"); //fare carta dei vini separata?
    }

    public void insertDish(ActionEvent event) throws SQLException, ClassNotFoundException {
        try {
            DishDAO.insertDishData(Integer.parseInt(txtId.getText()), txtDish.getText(), ckbCourse.getValue(), Double.parseDouble(txtPrice.getText()), dpDate.getValue().toString());

            // Chiudi la finestra di inserimento
            Stage stage = (Stage) addButton.getScene().getWindow(); // Ottieni il riferimento alla finestra di inserimento
            stage.close(); // Chiudi la finestra di inserimento
        }
        catch (SQLException e) {
            System.out.println("Exception occurs in Insertion" + e);
            throw e;
        }
    }
}
