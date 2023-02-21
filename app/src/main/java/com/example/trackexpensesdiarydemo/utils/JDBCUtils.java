package com.example.trackexpensesdiarydemo.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JDBCUtils {
    static {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    public static Connection getConn(){
        Connection conn = null;
        try {
            conn = DriverManager.getConnection("jdbc:mysql://10.0.2.2:3307/test","root","MySQLk300231053307");
        }catch (Exception exception){
            exception.printStackTrace();
        }
        return conn;
    }
    public static void close(Connection conn){
        try {
            conn.close();
        }catch (SQLException throwables){
            throwables.printStackTrace();
        }
    }
}
