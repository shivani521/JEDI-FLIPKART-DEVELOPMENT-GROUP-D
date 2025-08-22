package com.flipkart.bean;

public class GymOwner extends User {

    private int gymOwnerId;
    private String accountNumber;
    private String phone;
    private String panNumber;

    public GymOwner(String username, int userId, String password, String email, String name, int roleId, String status) {
        super(username, userId, password, email, name, roleId, status);
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public int getGymOwnerId() {
        return gymOwnerId;
    }

    public void setGymOwnerId(int gymOwnerId) {
        this.gymOwnerId = gymOwnerId;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPanNumber() {
        return panNumber;
    }

    public void setPanNumber(String panNumber) {
        this.panNumber = panNumber;
    }
}