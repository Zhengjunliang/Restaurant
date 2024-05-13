package com.restaurant.administrator;

import com.restaurant.DBUtil.OrderListDAO;
import com.restaurant.model.Order;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.sql.SQLException;
import java.util.Objects;

public class OrderListController {

    @FXML private TextField searchId;

    @FXML TableView<Order> orderTable;
    @FXML private TableColumn<Order, Integer> colOrderId;
    @FXML private TableColumn<Order, String> colOrderName;
    @FXML private TableColumn<Order, Double> colOrderTotal;
    @FXML private TableColumn<Order, String> colOrderDate;

    @FXML private ImageView returnButton;

    public void initialize() throws Exception{
        colOrderId.setCellValueFactory(cellData -> cellData.getValue().idPropertyProperty().asObject());
        colOrderName.setCellValueFactory(cellData -> cellData.getValue().customerPropertyProperty());
        colOrderTotal.setCellValueFactory(cellData -> cellData.getValue().totalPricePropertyProperty().asObject());
        colOrderDate.setCellValueFactory(cellData -> cellData.getValue().dataPropertyProperty());
        ObservableList<Order> orderList = OrderListDAO.getAllRecords();
        populateTable(orderList);
    }

    private void populateTable(ObservableList<Order> orderList){
        orderTable.setItems(orderList);
    }

    public void refreshOrders(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        ObservableList<Order> orderList = OrderListDAO.getAllRecords();
        populateTable(orderList);
    }

    public void searchOrder(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        if (searchId.getText().isEmpty()) {
            ObservableList<Order> orderList = OrderListDAO.getAllRecords();
            populateTable(orderList);
        }
        else {
            ObservableList<Order> orderList = OrderListDAO.searchOrderData(searchId.getText());

            if (!orderList.isEmpty()) {
                populateTable(orderList);
            }
            else {
                System.out.println("Order Not Found");
            }
        }
    }

    public void return_back(MouseEvent event) throws Exception {
        try {
            Stage stage = (Stage) returnButton.getScene().getWindow(); // Ottieni il riferimento alla finestra di AdministratorDish
            stage.close(); // Chiudi la finestra di AdministratorDish

            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("AdministratorView.fxml")));
            Scene admOrders = new Scene(root);


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
