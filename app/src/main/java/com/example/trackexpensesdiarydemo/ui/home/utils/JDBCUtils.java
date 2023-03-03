package com.example.trackexpensesdiarydemo.ui.home.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JDBCUtils {
    static {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        }catch (ClassNotFoundException e){
            e.printStackTrace();
        }
    }

    public static Connection getConn(){
        Connection connection = null;
        try {
            connection = DriverManager.getConnection("jdbc:mysql://192.168.152.110:3307/test","root","MySQLk300231053307");
        }catch (Exception e){
            e.printStackTrace();
        }
        return connection;
    }

    public static void close(Connection connection){
        try {
            connection.close();
        }catch (SQLException throwables){
            throwables.printStackTrace();
        }
    }
}
