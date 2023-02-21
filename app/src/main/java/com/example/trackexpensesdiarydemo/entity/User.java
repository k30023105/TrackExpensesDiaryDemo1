package com.example.trackexpensesdiarydemo.entity;

public class User {
    private int id;
    private String nickname;
    private String useremail;
    private String password;

    public User(){

    }

    public User(int id,String nickname,String useremail,String password){
        this.id = id;
        this.nickname = nickname;
        this.useremail = useremail;
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getUseremail() {
        return useremail;
    }

    public void setUseremail(String useremail) {
        this.useremail = useremail;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}


