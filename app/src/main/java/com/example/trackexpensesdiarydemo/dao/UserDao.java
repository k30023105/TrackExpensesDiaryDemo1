package com.example.trackexpensesdiarydemo.dao;

import com.example.trackexpensesdiarydemo.entity.User;
import com.example.trackexpensesdiarydemo.utils.JDBCUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDao {
    public boolean login(String useremail,String password){
        String sql = "select * from users where name = ? and password = ?";
        Connection con = JDBCUtils.getConn();

        try {
            PreparedStatement pst = con.prepareStatement(sql);

            pst.setString(1,useremail);
            pst.setString(2,password);

            if (pst.executeQuery().next()){
                return true;
            }
        }catch (SQLException throwables){
            throwables.printStackTrace();
        }finally {
            JDBCUtils.close(con);
        }
        return false;
    }
    public boolean register(User user){
        String sql = "insert into users(nickname,useremail,password)values(?,?,?)";

        Connection con = JDBCUtils.getConn();

        try {
            PreparedStatement pst = con.prepareStatement(sql);

            pst.setString(1, user.getNickname());
            pst.setString(2, user.getUseremail());
            pst.setString(3, user.getPassword());

            int value = pst.executeUpdate();

            if (value > 0){
                return true;
            }
        }catch (SQLException throwables){
            throwables.printStackTrace();
        }finally {
            JDBCUtils.close(con);
        }
        return false;
    }

    public User findUser(String useremail){
        String sql = "select * from users where name = ?";

        Connection con = JDBCUtils.getConn();
        User user = null;
        try {
            PreparedStatement pst = con.prepareStatement(sql);

            pst.setString(2,useremail);

            ResultSet rs = pst.executeQuery();

            while (rs.next()){
                int id = rs.getInt(0);
                String nicknamedb = rs.getString(1);
                String useremaildb = rs.getString(2);
                String passworddb = rs.getString(3);
                user = new User(id,nicknamedb,useremaildb,passworddb);
            }
        }catch (SQLException throwables){
            throwables.printStackTrace();
        }finally {
            JDBCUtils.close(con);
        }
        return user;
    }
}
