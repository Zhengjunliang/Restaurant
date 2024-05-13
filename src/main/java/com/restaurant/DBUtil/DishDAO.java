package com.restaurant.DBUtil;

import com.restaurant.model.Dish;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.ResultSet;
import java.sql.SQLException;

public class DishDAO {

    public static void insertDishData(int id, String name, String course, double price, String date) throws SQLException {
        String sql = "insert into dish values("+id+",'"+name+"','"+course+"',"+price+",'"+date+"')";
        try{
            DBUtil.dbExecuteQuery(sql);
        } catch (SQLException e) {
            System.out.println("Exception occurred while inserting dish data");
            throw e;
        }
    }

    public static void updateDishData(int id, String name, String course, double price, String date) throws SQLException {
        String sql = "UPDATE dish SET dish='"+name+"', course='"+course+"', price="+price+", date='"+date+"' WHERE id=" + id;
        try {
            DBUtil.dbExecuteQuery(sql);
        }
        catch (SQLException e) {
            System.out.println("Exception occurred while updating dish data");
            throw e;
        }
    }

    public static void deleteDishData(int id) throws SQLException {
        String sql = "DELETE FROM dish WHERE id=" + id;
        try {
            DBUtil.dbExecuteQuery(sql);
        }
        catch (SQLException e) {
            System.out.println("Exception occurred while deleting dish data");
            throw e;
        }
    }

    public static ObservableList<Dish> getAllRecords() throws SQLException, ClassNotFoundException {
        String sql = "SELECT * FROM dish";
        try{
            ResultSet rs = DBUtil.dbExecute(sql);
            ObservableList<Dish> dishList;
            dishList = getDishObjects(rs);
            return dishList;
        }
        catch (SQLException e){
            System.out.println("Exception occurred while reading dish data");
            throw e;
        }
    }


    public static ObservableList<Dish> getDishObjects(ResultSet rs) throws SQLException {
        try {
            ObservableList<Dish> dishList = FXCollections.observableArrayList();
            while (rs.next()) {
                Dish dish = new Dish();
                dish.setId(rs.getInt("id"));
                dish.setDish(rs.getString("dish"));
                dish.setCourse(rs.getString("course"));
                dish.setPrice(rs.getDouble("price"));
                dish.setDate(rs.getString("date"));
                dishList.add(dish);
            }
            return dishList;
        }
        catch (SQLException e) {
            System.out.println("Exception occurred while reading dish data");
            throw e;
        }
    }

    public static ObservableList<Dish> searchDishData(String id) throws SQLException, ClassNotFoundException {
        String sql = "SELECT * FROM dish WHERE id=" + id;
        try {
            ResultSet rs = DBUtil.dbExecute(sql);
            ObservableList<Dish> dishList;
            dishList = getDishObjects(rs);
            return dishList;
        }
        catch (SQLException e) {
            System.out.println("Exception occurred while searching dish data");
            throw e;
        }
    }

}
