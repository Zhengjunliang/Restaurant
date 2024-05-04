package com.restaurant.DBUtil;

import com.restaurant.model.Order;

import java.sql.SQLException;

public class OrderDAO {

    public static void insertOrderData(int id, String name, Double price, String date) throws SQLException {
        String sql = "insert into orders values("+id+",'"+name+"',"+price+",'"+date+"')";
        try{
            DBUtil.dbExecuteQuery(sql);
        }
        catch(SQLException e){
            System.out.println("Error inserting order data");
            throw e;
        }
    }
}
