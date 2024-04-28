package com.restaurant.login;

import com.restaurant.administrator.AdministratorApplication;
import com.restaurant.customer.CustomerApplication;
import com.restaurant.DBUtil.DBUtil;
import com.restaurant.util.StringUtil;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.sql.*;

public class LoginController {

    @FXML
    private TextField accountLogin;

    @FXML
    private TextField passwordLogin;

    @FXML
    private Label loginErrorMessage;

    @FXML
    public void initialize() {

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
                    AdministratorApplication administratorApplication = new AdministratorApplication();
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
}
