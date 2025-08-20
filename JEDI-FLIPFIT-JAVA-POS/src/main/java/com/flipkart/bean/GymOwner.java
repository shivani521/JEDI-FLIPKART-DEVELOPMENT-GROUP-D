package com.flipkart.bean;

import java.util.List;

public class GymOwner extends User {

    private String aadharCardNumber;
    private String panNumber;
    private List<Integer> gymCenterId;
    private boolean status;

    public GymOwner(int id, String name, String email, String password, String phoneNO, String aadharCardNumber, String panNumber, List<Integer> gymCenterId, boolean status) {
        super(id, name, email, password,phoneNO);
        this.aadharCardNumber = aadharCardNumber;
        this.panNumber = panNumber;
        this.gymCenterId = gymCenterId;
        this.status = status;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public List<Integer> getGymCenterId() {
        return gymCenterId;
    }

    public void setGymCenterId(List<Integer> gymCenterId) {
        this.gymCenterId = gymCenterId;
    }

    public String getAadharCardNumber() {
        return aadharCardNumber;
    }

    public void setAadharCardNumber(String aadharCardNumber) {
        this.aadharCardNumber = aadharCardNumber;
    }
    public String getPanNumber() {
        return panNumber;
    }
    public void setPanNumber(String panNumber) {
        this.panNumber = panNumber;
    }
}