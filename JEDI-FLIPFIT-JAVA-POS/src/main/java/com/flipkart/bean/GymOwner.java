package com.flipkart.bean;

import java.util.List;

public class GymOwner extends User {

    private int gymOwnerId;
    private String accountNumber;


    public GymOwner(String username, int userId, String password, String email, String name, int roleId, String status) {
        super(username, userId, password, email, name, roleId, status);
    }

    public GymOwner() {
        super();
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
}