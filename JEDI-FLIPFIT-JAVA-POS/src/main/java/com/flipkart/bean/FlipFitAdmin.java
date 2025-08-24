package com.flipkart.bean;


public class FlipFitAdmin extends User {

    /**
     * A unique identifier for the administrator.
     */
    private int adminId;

    /**
     * Constructs a new FlipFitAdmin object by inheriting and setting properties from the User base class.
     *
     * @param username The administrator's username.
     * @param userId The unique user ID.
     * @param password The administrator's password.
     * @param email The administrator's email address.
     * @param name The administrator's full name.
     * @param roleId The role ID assigned to the administrator (e.g., role ID for admin).
     * @param status The current account status of the administrator.
     */
    public FlipFitAdmin(String username, int userId, String password, String email, String name, int roleId, String status) {
        super(username, userId, password, email, name, roleId, status);
    }

    /**
     * Retrieves the unique ID of the administrator.
     *
     * @return The administrator's ID.
     */
    public int getAdminId() {
        return adminId;
    }

    /**
     * Sets the unique ID for the administrator.
     *
     * @param adminId The new administrator ID to set.
     */
    public void setAdminId(int adminId) {
        this.adminId = adminId;
    }
}