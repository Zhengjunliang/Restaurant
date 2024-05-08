package com.restaurant.DBUtil;

import com.restaurant.model.Order;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.ResultSet;
import java.sql.SQLException;

public class OrderListDAO {

    public static ObservableList<Order> getAllRecords() throws SQLException, ClassNotFoundException {
        String sql = "SELECT * FROM orders";
        try{
            ResultSet rs = DBUtil.dbExecute(sql);
            ObservableList<Order> orderList;
            orderList = getOrderObjects(rs);
            return orderList;
        }
        catch (SQLException e){
            System.out.println("Exception occurred while reading order data");
            throw e;
        }
    }

    public static ObservableList<Order> getOrderObjects(ResultSet rs) throws SQLException, ClassNotFoundException {
        try {
            ObservableList<Order> orderList = FXCollections.observableArrayList();
            while (rs.next()) {
                Order order = new Order();
                order.setIdProperty(rs.getInt("id"));
                order.setCustomerProperty(rs.getString("name"));
                order.setTotalPriceProperty(rs.getDouble("price"));
                order.setDataProperty(rs.getString("date"));
                orderList.add(order);
            }
            return orderList;
        }
        catch (SQLException e) {
            System.out.println("Exception occurred while reading order data");
            throw e;
        }
    }

    public static ObservableList<Order> searchOrderData(String id) throws SQLException, ClassNotFoundException {
        String sql = "SELECT * FROM orders WHERE id=" + id;
        try {
            ResultSet rs = DBUtil.dbExecute(sql);
            ObservableList<Order> orderList;
            orderList = getOrderObjects(rs);
            return orderList;
        }
        catch (SQLException e) {
            System.out.println("Exception occurred while searching order data");
            throw e;
        }
    }

    public static double sumPriceData() throws SQLException, ClassNotFoundException {
        String sql = "SELECT SUM(price) as totalPrice FROM orders";
        double totalPrice = 0;
        try{
            ResultSet rs = DBUtil.dbExecute(sql);
            if (rs.next()) {
                totalPrice = rs.getDouble("totalPrice");
            }
            return totalPrice;
        }
        catch (SQLException e){
            System.out.println("Exception occurred while calculating total price");
            throw e;
        }
    }
}


