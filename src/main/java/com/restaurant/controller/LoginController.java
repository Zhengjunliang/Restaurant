package com.restaurant.controller;

import com.restaurant.util.StringUtil;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

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
    public void Login() {
        String login = accountLogin.getText();
        String password = passwordLogin.getText();
        if (StringUtil.isEmpty(login) || StringUtil.isEmpty(password)) {
            loginErrorMessage.setVisible(true);
            return;
        }

        if (login.equals("admin") && password.equals("123")) {
            //Go to next controller
            loginErrorMessage.setVisible(false);
        }
        else {
            loginErrorMessage.setVisible(true);
        }
    }
}
