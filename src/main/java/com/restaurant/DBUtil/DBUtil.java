package com.restaurant.DBUtil;

import java.sql.*;

public class DBUtil {

    //database connection method
    public static Connection dbConnect() throws SQLException, ClassNotFoundException  {
        Connection con = null;
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
            throw new SQLException(e.getMessage());
        }
        return con;
    }

    //disconnecting database method
    public static void dbDisconnect(Connection con) throws SQLException {
        if(con != null && !con.isClosed()){
            con.close();
        }

    }

}
