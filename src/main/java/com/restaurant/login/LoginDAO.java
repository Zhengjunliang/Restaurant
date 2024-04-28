package com.restaurant.login;

import com.restaurant.DBUtil.DBUtil;

import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginDAO {

    public static boolean isLogin(String username, String password) throws SQLException {
        String sql = "select * from utente where username='"+username+"' and password='"+password+"'";
        try{
            ResultSet rs = DBUtil.dbExecute(sql);

            return rs.next();
        }
        catch (Exception e){
            return false;
        }
    }
}
