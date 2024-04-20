package com.restaurant.controller;

import com.restaurant.DBUtil.DBUtil;
import com.restaurant.util.StringUtil;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

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
    public void Login() throws SQLException {
        String login = accountLogin.getText();
        String password = passwordLogin.getText();
        if (StringUtil.isEmpty(login) || StringUtil.isEmpty(password)) {
            loginErrorMessage.setVisible(true);
            return;
        }
        Connection con = null;
        ResultSet rs = null;
        try{
            con = DBUtil.dbConnect();
            String sql = "select * from utente";
            Statement stm = con.createStatement();
            rs = stm.executeQuery(sql);
            System.out.println("查询结果:");
            while (rs.next()) {
                String name = rs.getString("username");
                String qwpassword = rs.getString("password");
                System.out.println(name);
                System.out.println(qwpassword);
            }

        }
        catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            DBUtil.dbDisconnect(con);
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
