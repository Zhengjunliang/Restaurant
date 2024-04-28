package com.restaurant.administrator;

import com.restaurant.DBUtil.DBUtil;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.ResultSet;
import java.sql.SQLException;

public class StaffDAO {

    public static void insertStaffData(int id, String name, String gender, int age, String role, String salary, String date) throws SQLException {
        String sql = "insert into staff values("+id+",'"+name+"','"+gender+"',"+age+",'"+role+"','"+salary+"','"+date+"')";
        try {
            DBUtil.dbExecuteQuery(sql);
        }
        catch (SQLException e) {
            System.out.println("Exception occurred while inserting the staff");
            throw e;
        }
    }

    public static void updateStaffData(int id, String name, String gender, int age, String role, String salary, String date) throws SQLException {
        String sql = "UPDATE staff SET name='" + name + "', gender='" + gender + "', age=" + age + ", role='" + role + "', salary='" + salary + "', date='" + date + "' WHERE id=" + id;
        try{
            DBUtil.dbExecuteQuery(sql);
        }
        catch (SQLException e) {
            System.out.println("Exception occurred while updating the staff");
            throw e;
        }
    }

    public static void deleteStaffByID(int id) throws SQLException {
        String sql = "delete from staff where id="+id;
        try{
            DBUtil.dbExecuteQuery(sql);
        }
        catch (SQLException e) {
            System.out.println("Exception occurred while deleting the Staff Record");
            throw e;
        }
    }
    public static ObservableList<Staff> getAllRecords() throws SQLException, ClassNotFoundException {
        String sql = "select * from staff";

        try {
            ResultSet rs = DBUtil.dbExecute(sql);
            ObservableList<Staff> staffList;
            staffList = getStaffObjects(rs);
            return staffList;
        }
        catch (SQLException e) {
            System.out.println("Error occurred while fetching staff records");
            throw e;
        }
    }

    public static ObservableList<Staff> getStaffObjects(ResultSet rs) throws SQLException, ClassNotFoundException {
        try
        {
            ObservableList<Staff> staffList = FXCollections.observableArrayList();
            while (rs.next())
            {
                Staff staff = new Staff();
                staff.setId(rs.getInt("id"));
                staff.setName(rs.getString("name"));
                staff.setGender(rs.getString("gender"));
                staff.setAge(rs.getInt("age"));
                staff.setRole(rs.getString("role"));
                staff.setSalary(rs.getString("salary"));
                staff.setDate(rs.getString("date"));
                staffList.add(staff);
            }
            return staffList;
        }
        catch (SQLException e){
            System.out.println("Error occurred while fetching staff records");
            throw e;
        }
    }

    public static ObservableList<Staff> searchStaffData(String id) throws SQLException, ClassNotFoundException {
        String sql = "select * from staff where id="+id;
        try{
            ResultSet rs = DBUtil.dbExecute(sql);
            ObservableList<Staff> staffList;
            staffList = getStaffObjects(rs);
            return staffList;
        }
        catch (SQLException e){
            System.out.println("Error occurred while searching staff records");
            throw e;
        }
    }
}
