package com.restaurant.administrator;

import com.restaurant.model.Dish;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.fxml.FXML;
import javafx.stage.Stage;

import java.sql.SQLException;
import java.util.Objects;

public class DishController {

    @FXML private TextField searchId;

    @FXML TableView<Dish> dishTable;
    @FXML private TableColumn<Dish, Integer> colDishId;
    @FXML private TableColumn<Dish, String> colDishName;
    @FXML private TableColumn<Dish, String> colDishCourse;
    @FXML private TableColumn<Dish, Double> colDishPrice;
    @FXML private TableColumn<Dish, String> colDishDate;

    @FXML private Button returnButton;

    @FXML
    public void initialize() throws Exception{
        colDishId.setCellValueFactory(cellData -> cellData.getValue().getDishId().asObject());
        colDishName.setCellValueFactory(cellData -> cellData.getValue().getDishName());
        colDishCourse.setCellValueFactory(cellData -> cellData.getValue().getDishCourse());
        colDishPrice.setCellValueFactory(cellData -> cellData.getValue().getDishPrice().asObject());
        colDishDate.setCellValueFactory(cellData -> cellData.getValue().getDishDate());
        ObservableList<Dish> dishList = DishDAO.getAllRecords();
        populateTable(dishList);
    }

    private void populateTable(ObservableList<Dish> dishList) {
        dishTable.setItems(dishList);
    }

    @FXML
    public void refreshDish() throws SQLException, ClassNotFoundException {
        ObservableList<Dish> dishList = DishDAO.getAllRecords();
        populateTable(dishList);
    }

    @FXML
    public void addDish() throws Exception{
        try {
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("DishAdd.fxml")));
            Scene admDish = new Scene(root);

            Stage window = new Stage();
            window.setScene(admDish);
            window.show();
        }
        catch (Exception e) {
            System.out.println("Error occurred while opening addDish page");
            throw e;
        }
    }

    public void updateDish() throws Exception{
        try{
            Dish dish = dishTable.getSelectionModel().getSelectedItem();
            if (dish != null) {
                DishUpdateController.setCurrent(dish);
                Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("DishUpdate.fxml")));

                Stage window = new Stage();
                window.setScene(new Scene(root));
                window.show();
            }
        }
        catch (Exception e) {
            System.out.println("Error occurred while opening updateDish page");
            throw e;
        }
    }

    @FXML
    public void deleteDish() throws SQLException, ClassNotFoundException {
        try {
            Dish dish = dishTable.getSelectionModel().getSelectedItem();
            if (dish != null) {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Confirm Deletion");
                alert.setHeaderText("Do you really want to delete this dish?");
                alert.setContentText("Press OK to delete this dish");

                if (alert.showAndWait().get() == ButtonType.OK) {
                    DishDAO.deleteDishData(dish.getId());
                    ObservableList<Dish> dishList = DishDAO.getAllRecords();
                    populateTable(dishList);
                }
            }
        }
        catch (SQLException e) {
            System.out.println("Error occurred while deleting dish");
            throw e;
        }
    }

    @FXML
    public void searchDish() throws SQLException, ClassNotFoundException {
        if (searchId.getText().isEmpty()){
            ObservableList<Dish> dishList = DishDAO.getAllRecords();
            populateTable(dishList);
        }
        else {
            ObservableList<Dish> dishList = DishDAO.searchDishData(searchId.getText());

            if (!dishList.isEmpty()) {
                populateTable(dishList);
            }
            else {
                System.out.println("Dish not found");
            }
        }
    }

    public void return_back(ActionEvent event) throws Exception {
        try {
            //si possono togliere queste due righe?
            Stage stage = (Stage) returnButton.getScene().getWindow(); // Ottieni il riferimento alla finestra di AdministratorDish
            stage.close(); // Chiudi la finestra di AdministratorDish

            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("AdministratorView.fxml")));
            Scene admDish = new Scene(root);

            Label usernameLabel = (Label) admDish.lookup("#txtUsername");
            usernameLabel.setText(AdministratorController.getUsername());

            Stage window = new Stage();
            window.setScene(admDish);
            window.show();
        }
        catch (Exception e) {
            System.out.println("Error occurred while opening Administrator page");
            throw e;
        }
    }
}
