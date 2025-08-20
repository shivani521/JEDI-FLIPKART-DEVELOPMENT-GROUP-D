package com.flipkart.bean;

public class User {
    private String userName;
    private String userID;
    private String emailID;
    private String password;
    private String phoneNO;

    public User (String userId, String userName, String emailID, String password, String phoneNO) {
        this.userID = userId;
        this.userName = userName;
        this.emailID = emailID;
        this.password = password;
        this.phoneNO = phoneNO;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID= userID;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmailID() {
        return emailID;
    }

    public void setEmail(String emailID) {
        this.emailID = emailID;
    }
    public String getPhone() {
        return phoneNO;
    }

    public void setphone(String phoneNO) {
        this.phoneNO = phoneNO;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}