package com.flipkart.bean;

public class FlipFitAdmin extends User {
    private int adminId;

    public FlipFitAdmin(String username, int userId, String password, String email, String name, int roleId, String status) {
        super(username, userId, password, email, name, roleId, status);
    }

    public int getAdminId() {
        return adminId;
    }

    public void setAdminId(int adminId) {
        this.adminId = adminId;
    }
}