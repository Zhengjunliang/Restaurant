package com.restaurant.administrator;

import com.restaurant.DBUtil.OrderListDAO;
import com.restaurant.Main;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;


import java.sql.SQLException;
import java.util.Objects;

public class AdministratorController {

    @FXML private Label txtBudget;
    @FXML private Button logoutButton;
    @FXML private static Label txtUsername;
    @FXML private ImageView gifStaff;
    @FXML private ImageView imgStaff;
    @FXML private ImageView gifDishes;
    @FXML private ImageView imgDishes;
    @FXML private ImageView gifOrders;
    @FXML private ImageView imgOrders;
    private static String username = "";
    private double budget;

    public void initialize() throws ClassNotFoundException, SQLException {
        budget = StaffDAO.sumSalaryData() - OrderListDAO.sumPriceData();
        txtBudget.setText(String.valueOf(budget));
    }

    // Metodo di set per txtUsername
    @FXML
    public static void setUsername(String username) {
        if (username != null) {
            AdministratorController.username = username;
        }
    }

    public static String getUsername() {
        return username;
    }

    public void gifHoverDishes() {
        gifDishes.setVisible(true);
        imgDishes.setVisible(false);
    }

    public void gifExitDishes() {
        gifDishes.setVisible(false);
        imgDishes.setVisible(true);
    }

    public void admDish(MouseEvent event) throws Exception {
        try {
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("AdministratorDish.fxml")));
            Scene admDish = new Scene(root);
            //get stage information
            Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
            window.setScene(admDish);
            window.show();
        }
        catch (Exception e) {
            System.out.println("Error occurred while opening Dish page");
            throw e;
        }
    }

    public void admDishButton(ActionEvent event) throws Exception {
        try {
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("AdministratorDish.fxml")));
            Scene admDish = new Scene(root);
            //get stage information
            Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
            window.setScene(admDish);
            window.show();
        }
        catch (Exception e) {
            System.out.println("Error occurred while opening Dish page");
            throw e;
        }
    }

    public void gifHoverStaff() {
        gifStaff.setVisible(true);
        imgStaff.setVisible(false);
    }

    public void gifExitStaff() {
        gifStaff.setVisible(false);
        imgStaff.setVisible(true);
    }

    public void admStaff(MouseEvent event) throws Exception {
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

    }public void admStaffButton(ActionEvent event) throws Exception {
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

    public void gifHoverOrders() {
        gifOrders.setVisible(true);
        imgOrders.setVisible(false);
    }

    public void gifExitOrders() {
        gifOrders.setVisible(false);
        imgOrders.setVisible(true);
    }

    public void admOrders(MouseEvent event) throws Exception {
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

    public void admOrdersButton(ActionEvent event) throws Exception {
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

    public void admBudget() throws ClassNotFoundException, SQLException {
        budget = StaffDAO.sumSalaryData() /*- StaffDAO.sumOrders*/;
    }

    public void admLogout(ActionEvent event) throws Exception {
        try {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Logout");
            alert.setHeaderText("You are about to log out");
            alert.setContentText("Do you really want to log out?");

            if (alert.showAndWait().get() == ButtonType.OK) {

                Stage stage = (Stage) logoutButton.getScene().getWindow();
                stage.close();

                Main main = new Main();
                main.start(new Stage());
            }
        }
        catch (Exception e) {
            System.out.println("Error occurred while logging out");
            throw e;
        }
    }
}
