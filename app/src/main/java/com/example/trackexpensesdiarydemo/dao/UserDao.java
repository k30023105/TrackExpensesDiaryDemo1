package com.example.trackexpensesdiarydemo.dao;

import android.telecom.Connection;

import com.example.trackexpensesdiarydemo.entity.User;
import com.example.trackexpensesdiarydemo.ui.home.utils.JDBCUtils;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDao {

    public boolean login(String useremail,String password){
        String sql = "select * from users where useremail = ? and password = ?";
        Connection connection = (Connection) JDBCUtils.getConn();
        try {
            PreparedStatement preparedStatement = ((java.sql.Connection) connection).prepareStatement(sql);
            preparedStatement.setString(1,useremail);
            preparedStatement.setString(2,password);
            if(preparedStatement.executeQuery().next()){
                return true;
            }
        }catch (SQLException throwables){
            throwables.printStackTrace();
        }finally {
            JDBCUtils.close((java.sql.Connection) connection);
        }
        return false;
    }
    public boolean register(User user){
        String sql = "insert into users(nickname,useremail,password) values (?,?,?)";
        Connection connection = (Connection) JDBCUtils.getConn();
        try {
            PreparedStatement preparedStatement = ((java.sql.Connection) connection).prepareStatement(sql);
            preparedStatement.setString(1, user.getNickname());
            preparedStatement.setString(2, user.getUseremail());
            preparedStatement.setString(3, user.getPassword());
            int value = preparedStatement.executeUpdate();
            if(value > 0){
                return true;
            }
        }catch (SQLException throwables){
            throwables.printStackTrace();
        }finally {
            JDBCUtils.close((java.sql.Connection) connection);
        }
        return false;
    }
    public User findUser(String useremail){
        String sql = "select * from users where useremail = ?";
        Connection connection = (Connection) JDBCUtils.getConn();
        User user = null;
        try {
            PreparedStatement preparedStatement = ((java.sql.Connection) connection).prepareStatement(sql);
            preparedStatement.setString(1,useremail);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                int id = resultSet.getInt(0);
                String nickname = resultSet.getString(1);
                String useremaildb = resultSet.getString(2);
                String password = resultSet.getString(3);
                user = new User(id,nickname,useremaildb,password);
            }
        }catch (SQLException throwables){
            throwables.printStackTrace();
        }finally {
            JDBCUtils.close((java.sql.Connection) connection);
        }
        return user;
    }
}
