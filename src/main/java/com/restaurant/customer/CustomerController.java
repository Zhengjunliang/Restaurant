package com.restaurant.customer;

import com.restaurant.DBUtil.OrderDAO;
import com.restaurant.administrator.DishDAO;
import com.restaurant.administrator.DishUpdateController;
import com.restaurant.model.Dish;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.sql.SQLException;
import java.util.Objects;


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
    }

    private void populateTable(ObservableList<Dish> dishList) {
        orderTable.setItems(dishList);
    }

    @FXML
    public void AddOrder() throws Exception{
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
    public void CheckOutOrder() throws SQLException{
        try{
           OrderDAO.insertOrderData(2,"marco", price, "11/11/11");

        }
        catch (SQLException e) {
            System.out.println("Error occurred while check out Order");
            throw e;
        }
    }

    public void logout(ActionEvent event) throws Exception {
        try {
            System.exit(1);
        }
        catch (Exception e) {
            System.out.println("Error occurred while logging out");
            throw e;
        }
    }

    public void deleteOrder(MouseEvent mouseEvent) {
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
}
