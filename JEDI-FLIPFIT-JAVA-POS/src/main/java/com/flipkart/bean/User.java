package com.flipkart.bean;

/**
 * The `User` class is a base class representing a generic user in the FlipFit system.
 * It contains common attributes for all user types, such as login credentials, personal details,
 * and a role identifier.
 */
public class User {

    /**
     * A unique identifier for the user.
     */
    private int userId;

    /**
     * The username used for login.
     */
    private String username;

    /**
     * The user's password.
     */
    private String password;

    /**
     * The user's email address.
     */
    private String email;

    /**
     * The full name of the user.
     */
    private String name;

    /**
     * The unique ID of the role assigned to the user.
     */
    private int roleId;

    /**
     * The current status of the user's account (e.g., "Active", "Pending", "Blocked").
     */
    private String status;

    /**
     * Constructs a new `User` object with all its properties.
     *
     * @param username The username of the user.
     * @param userId The unique ID of the user.
     * @param password The user's password.
     * @param email The user's email address.
     * @param name The user's full name.
     * @param roleId The ID of the user's role.
     * @param status The account status of the user.
     */
    public User(String username, int userId, String password, String email, String name, int roleId, String status) {
        this.username = username;
        this.userId = userId;
        this.password = password;
        this.email = email;
        this.name = name;
        this.roleId = roleId;
        this.status = status;
    }

    /**
     * Retrieves the username of the user.
     *
     * @return The username.
     */
    public String getUsername() {
        return username;
    }

    /**
     * Sets the username for the user.
     *
     * @param username The new username.
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Retrieves the unique ID of the user.
     *
     * @return The user ID.
     */
    public int getUserId() {
        return userId;
    }

    /**
     * Sets the unique ID for the user.
     *
     * @param userId The new user ID.
     */
    public void setUserId(int userId) {
        this.userId = userId;
    }

    /**
     * Retrieves the password of the user.
     *
     * @return The password.
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets the password for the user.
     *
     * @param password The new password.
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Retrieves the email address of the user.
     *
     * @return The email address.
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets the email address for the user.
     *
     * @param email The new email address.
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Retrieves the full name of the user.
     *
     * @return The full name.
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the full name for the user.
     *
     * @param name The new full name.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Retrieves the role ID of the user.
     *
     * @return The role ID.
     */
    public int getRoleId() {
        return roleId;
    }

    /**
     * Sets the role ID for the user.
     *
     * @param roleId The new role ID.
     */
    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    /**
     * Retrieves the current status of the user's account.
     *
     * @return The account status.
     */
    public String getStatus() {
        return status;
    }

    /**
     * Sets the account status for the user.
     *
     * @param status The new status.
     */
    public void setStatus(String status) {
        this.status = status;
    }
}