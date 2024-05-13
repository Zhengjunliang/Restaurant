package com.restaurant.customer;

import com.restaurant.DBUtil.OrderListDAO;
import com.restaurant.Main;
import com.restaurant.DBUtil.DishDAO;
import com.restaurant.model.ConfigurationManager;
import com.restaurant.model.Dish;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Objects;
import java.util.Optional;


public class CustomerController {

    @FXML private TableView<Dish> orderTable;
    @FXML private TableColumn<Dish, Integer> colOrderId;
    @FXML private TableColumn<Dish, String> colOrderName;
    @FXML private TableColumn<Dish, String> colOrderCourse;
    @FXML private TableColumn<Dish, Double> colOrderPrice;
    @FXML private TableColumn<Dish, String> colOrderDate;

    @FXML private TableView<Dish> order;
    @FXML private TableColumn<Dish, String> colName;
    @FXML private TableColumn<Dish, Double> colPrice;
    @FXML private Label priceTxt;
    private double price;

    @FXML private Button logoutButton;
    @FXML private Text txtCheckOutSuccess;
    @FXML private Text txtCheckOutFail;
    @FXML private Label txtUsername;




    @FXML
    public void initialize() throws Exception {
        price = 0;
        colOrderId.setCellValueFactory(cellData -> cellData.getValue().getDishId().asObject());
        colOrderName.setCellValueFactory(cellData -> cellData.getValue().getDishName());
        colOrderCourse.setCellValueFactory(cellData -> cellData.getValue().getDishCourse());
        colOrderPrice.setCellValueFactory(cellData -> cellData.getValue().getDishPrice().asObject());
        colOrderDate.setCellValueFactory(cellData -> cellData.getValue().getDishDate());
        colName.setCellValueFactory(cellData -> cellData.getValue().getDishName());
        colPrice.setCellValueFactory(cellData -> cellData.getValue().getDishPrice().asObject());
        ObservableList<Dish> dishList = DishDAO.getAllRecords();
        populateTable(dishList);
        priceTxt.setText(String.valueOf(price));
        ConfigurationManager configManager = ConfigurationManager.getInstance();
        txtUsername.setText(configManager.getUsername());

    }

    private void populateTable(ObservableList<Dish> dishList) {
        orderTable.setItems(dishList);
    }

    @FXML
    public void AddOrder() { //cambia che aggiunga item non al click ma dopo con un tasto o doppio click
        try
        {
            Dish dish = orderTable.getSelectionModel().getSelectedItem();
            if (dish != null) {
                order.getItems().add(dish);
                price += dish.getPrice();
                priceTxt.setText(String.valueOf(price));
            }
        }
        catch (Exception e) {
            System.out.println("Error occurred while add Order");
            throw e;
        }
    }

    @FXML
    public void CheckOutOrder() throws Exception{
        try{
            if (price != 0){
                ConfigurationManager configurationManager = ConfigurationManager.getInstance();
                LocalDate currentDate = LocalDate.now();
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yy");
                String date = currentDate.format(formatter);
                OrderListDAO.insertOrderData(configurationManager.getUsername(), price, date);
                txtCheckOutFail.setVisible(false);
                txtCheckOutSuccess.setVisible(true);
                configurationManager.setPrice(price);
                Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("PaymentView.fxml")));
                Scene payment = new Scene(root);

                Stage window = new Stage();
                window.setScene(payment);
                window.show();
            }
            else
            {
                txtCheckOutSuccess.setVisible(false);
                txtCheckOutFail.setVisible(true);
            }
        }
        catch (Exception e) {
            txtCheckOutSuccess.setVisible(false);
            txtCheckOutFail.setVisible(true);
            System.out.println("Error occurred while check out Order");
            throw e;
        }
    }

    public void deleteOrder() {
        try
        {
            Dish dish = order.getSelectionModel().getSelectedItem();
            if (order != null) {
                order.getItems().remove(dish);
                price -= dish.getPrice();
                priceTxt.setText(String.valueOf(price));
            }
        }
        catch (Exception e) {
            System.out.println("Error occurred while add Order");
            throw e;
        }
    }

    public void logout() throws Exception {
        try {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Logout");
            alert.setHeaderText("You are about to log out");
            alert.setContentText("Do you really want to log out?");

            Optional<ButtonType> result = alert.showAndWait();
            if (result.isPresent() && result.get() == ButtonType.OK) {

                Stage stage = (Stage) logoutButton.getScene().getWindow();
                stage.close();

                Main main = new Main();
                main.start(new Stage());
            }
        }
        catch (Exception e) {
            System.out.println("Error occurred while opening Login page");
            throw e;
        }
    }
}
