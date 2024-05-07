package com.restaurant.login;

import com.restaurant.administrator.AdministratorApplication;
import com.restaurant.administrator.AdministratorController;
import com.restaurant.customer.CustomerApplication;
import com.restaurant.DBUtil.DBUtil;
import com.restaurant.util.StringUtil;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

import java.sql.*;

public class LoginController {

    @FXML
    private AnchorPane loginPane;

    @FXML
    private TextField accountLogin;

    @FXML
    private TextField passwordLogin;

    @FXML
    private Label loginErrorMessage;

    @FXML
    private Button exitButton;

    Stage stage;

    private static String login;

    @FXML
    public void initialize() {

    }

    // Getter per l'username
    public static String getLogin() {
        return login;
    }

    @FXML
    public void Login() throws Exception {
        String login = accountLogin.getText();
        String password = passwordLogin.getText();
        if (StringUtil.isEmpty(login) || StringUtil.isEmpty(password)) {
            loginErrorMessage.setVisible(true);
            return;
        }

        try{
            if(LoginDAO.isLogin(login, password)){
                if(login.equals("admin")){
                    AdministratorApplication administratorApplication = new AdministratorApplication(login);
                    administratorApplication.start(new Stage());
                    Stage stage = DBUtil.getStage();
                    stage.close();
                }
                else {
                    CustomerApplication customerApplication = new CustomerApplication();
                    customerApplication.start(new Stage());
                    Stage stage = DBUtil.getStage();
                    stage.close();
                }
                System.out.println("Connected Successfully");

            }
            else{
                accountLogin.setStyle("-fx-border-color:red; -fx-border-width:2px;");
                passwordLogin.setStyle("-fx-border-color:red; -fx-border-width:2px;");
                loginErrorMessage.setVisible(true);
                System.out.println("Login Failed");
            }
        }
        catch (SQLException e) {
            System.out.println("Error occured while Login");
            throw e;
        }
    }

    public void logout(ActionEvent event) {

        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Logout");
        alert.setHeaderText("The Application is about to get closed.");
        alert.setContentText("Do you really want to log out?");


        if (alert.showAndWait().get() == ButtonType.OK) {
            stage = (Stage) loginPane.getScene().getWindow();
            stage.close();
        }
    }


}
