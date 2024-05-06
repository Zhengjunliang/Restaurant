package com.restaurant.DBUtil;

import com.restaurant.model.Order;

import java.sql.SQLException;

public class OrderDAO {

    public static void insertOrderData(String name, Double price, String date) throws SQLException {
        String sql = "insert into orders (name, price, data) values('"+name+"',"+price+",'"+date+"')";
        try{
            DBUtil.dbExecuteQuery(sql);
        }
        catch(SQLException e){
            System.out.println("Error inserting order data");
            throw e;
        }
    }
}
