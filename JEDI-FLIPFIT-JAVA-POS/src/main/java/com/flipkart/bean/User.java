package com.flipkart.bean;

public class User {
    private int userId;
    private String username;
    private String password;
    private String email;
    private String name;
    private int roleId;
    private String status;

    public User(String username, int userId, String password, String email, String name, int roleId, String status) {
        this.username = username;
        this.userId = userId;
        this.password = password;
        this.email = email;
        this.name = name;
        this.roleId = roleId;
        this.status = status;
    }


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}