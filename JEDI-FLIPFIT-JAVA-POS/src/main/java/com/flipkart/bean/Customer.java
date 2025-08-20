package com.flipkart.bean;

public class Customer extends User {
    private String customerCity;


    public Customer (int userId, String userName, String email, String password, String phoneNO, String customerCity) {
        super(userId, userName, email, password , phoneNO);
        this.customerCity = customerCity;

    }

    public String getCustomerAddress() {
        return customerCity;
    }

    public void setCustomerAddress(String customerAddress) {
        this.customerCity = customerAddress;
    }
}