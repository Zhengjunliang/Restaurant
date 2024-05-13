package com.restaurant.administrator;

import com.restaurant.DBUtil.OrderListDAO;
import com.restaurant.DBUtil.StaffDAO;
import com.restaurant.Main;
import com.restaurant.command.AdmToDishesCommand;
import com.restaurant.command.AdmToOrderCommand;
import com.restaurant.command.AdmToStaffCommand;
import com.restaurant.command.CommandInvoker;
import com.restaurant.model.ConfigurationManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import java.sql.SQLException;


public class AdministratorController {

    @FXML private Label txtBudget;
    @FXML private Button logoutButton;
    @FXML private Label txtUsername;
    @FXML private ImageView gifStaff;
    @FXML private ImageView imgStaff;
    @FXML private ImageView gifDishes;
    @FXML private ImageView imgDishes;
    @FXML private ImageView gifOrders;
    @FXML private ImageView imgOrders;

    private final CommandInvoker commandInvoker = new CommandInvoker();

    public void initialize() throws ClassNotFoundException, SQLException {
        double budget = StaffDAO.sumSalaryData() - OrderListDAO.sumPriceData();
        txtBudget.setText(String.valueOf(budget));
        ConfigurationManager configurationManager = ConfigurationManager.getInstance();
        txtUsername.setText(configurationManager.getUsername());
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
        commandInvoker.setCommand(new AdmToDishesCommand());
        commandInvoker.executeCommand(event);
    }

    public void admDishButton(ActionEvent event) throws Exception {
        commandInvoker.setCommand(new AdmToDishesCommand());
        commandInvoker.executeCommand(event);
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
        commandInvoker.setCommand(new AdmToStaffCommand());
        commandInvoker.executeCommand(event);

    }
    public void admStaffButton(ActionEvent event) throws Exception {
        commandInvoker.setCommand(new AdmToStaffCommand());
        commandInvoker.executeCommand(event);
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
        commandInvoker.setCommand(new AdmToOrderCommand());
        commandInvoker.executeCommand(event);
    }

    public void admOrdersButton(ActionEvent event) throws Exception {
        commandInvoker.setCommand(new AdmToOrderCommand());
        commandInvoker.executeCommand(event);
    }


    public void admLogout(ActionEvent event) throws Exception {
        try {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Logout");
            alert.setHeaderText("You are about to log out");
            alert.setContentText("Do you really want to log out?");

            if (alert.showAndWait().get() == ButtonType.OK) {

                Stage stage = (Stage) logoutButton.getScene().getWindow();
                //stage.close();

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
