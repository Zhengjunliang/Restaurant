package com.restaurant.administrator;

import com.restaurant.DBUtil.DishDAO;
import com.restaurant.model.Dish;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.sql.SQLException;

public class DishUpdateController {
    @FXML private TextField txtId;
    @FXML private TextField txtDish;
    @FXML private ChoiceBox<String> ckbCourse;
    @FXML private TextField txtPrice;
    @FXML private DatePicker dpDate;
    @FXML private static Dish current;

    @FXML private Button updateButton;

    public static void setCurrent(Dish dish) {DishUpdateController.current=dish;}

    @FXML
    public void initialize() {
        ckbCourse.getItems().clear();
        ckbCourse.getItems().addAll("Appetizer", "First Course", "Second Course", "Dessert", "Water", "Wine");

        txtId.setText(Integer.toString(current.getId()));
        txtId.setEditable(false);
        txtDish.setText(current.getDish());
        ckbCourse.setValue(current.getCourse());
        txtPrice.setText(Double.toString(current.getPrice()));
        dpDate.getEditor().setText(current.getDate());
    }

    @FXML
    public void modifyDish(ActionEvent event) throws SQLException, ClassNotFoundException {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirm Changes");
        alert.setHeaderText("Do you really want to apply changes?");
        alert.setContentText("Press OK to confirm");

        if (alert.showAndWait().get() == ButtonType.OK) {
            try {
                DishDAO.updateDishData(Integer.parseInt(txtId.getText()), txtDish.getText(), ckbCourse.getValue(), Double.parseDouble(txtPrice.getText()), dpDate.getEditor().getText());

                // Chiudi la finestra di aggiornamento
                Stage stage = (Stage) updateButton.getScene().getWindow(); // Ottieni il riferimento alla finestra di aggiornamento
                stage.close(); // Chiudi la finestra di aggiornamento
            } catch (SQLException e) {
                System.out.println("Exception occurs in Insertion" + e);
                throw e;
            }

        }
    }
}
