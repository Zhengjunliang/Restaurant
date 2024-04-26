package com.restaurant.util;

import com.restaurant.DBUtil.DBUtil;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class LoginUtil {

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
