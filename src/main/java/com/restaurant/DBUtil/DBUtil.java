package com.restaurant.DBUtil;

import javafx.stage.Stage;

import java.sql.*;

public class DBUtil {

    private static Stage stage;
    private static Connection con;

    //database connection method
    public static void dbConnect() throws SQLException, ClassNotFoundException  {

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        }
        catch (ClassNotFoundException e) {
            throw new ClassNotFoundException(e.getMessage());
        }
        try{
            con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/db.restaurant", "root", "root");
        }
        catch (SQLException e) {
            System.out.println("Connection Failed");
            throw e;
        }

    }

    //disconnecting database method
    public static void dbDisconnect() throws SQLException {
        try{
            if(con != null && !con.isClosed()){
                con.close();
            }
       }
       catch (SQLException e) {
            throw new SQLException(e.getMessage());
       }
    }
    //for insert update delete from db
    public static void dbExecuteQuery(String sqlStm) throws SQLException {
        Statement stm = null;
        try{
            dbConnect();
            stm = con.createStatement();
            stm.executeUpdate(sqlStm);
        }
        catch (SQLException | ClassNotFoundException e){
            System.out.println("Problem occurred at dbExecuteQuery");
            throw new SQLException(e.getMessage());
        }
        finally {
            if(stm != null){
                stm.close();
            }
            System.out.println("Connect Disconnected");
            dbDisconnect();
        }
    }
    //for getting data from database(selects)
    public static ResultSet dbExecute(String sqlQuery) throws ClassNotFoundException, SQLException {
        Statement stmt;
        ResultSet rs;
        try {

            dbConnect();
            stmt = con.createStatement();
            rs = stmt.executeQuery(sqlQuery);

        } catch (SQLException e) {
            System.out.println("Problem occurred in dbExecute " + e);
            throw e;
        }
        /*
        ResultSet return the value but the connection must be open
        finally {
            if (stmt != null) {
                stmt.close();
            }
            System.out.println("Connect Disconnected");
            dbDisconnect();
        }*/
        return rs;
    }

    public static void setStage(Stage stage) {
        DBUtil.stage = stage;
    }

    public static Stage getStage() {
        return stage;
    }

}
