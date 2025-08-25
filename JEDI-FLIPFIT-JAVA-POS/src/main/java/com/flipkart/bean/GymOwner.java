package com.flipkart.bean;

/**
 * The `GymOwner` class extends the `User` class to represent a gym owner in the FlipFit system.
 * It holds specific details relevant to a gym owner's profile, such as financial and contact information.
 */
public class GymOwner extends User {

    /**
     * A unique identifier for the gym owner.
     */
    private int gymOwnerId;

    /**
     * The bank account number of the gym owner.
     */
    private String accountNumber;

    /**
     * The phone number of the gym owner.
     */
    private String phone;

    /**
     * The PAN (Permanent Account Number) of the gym owner.
     */
    private String panNumber;

    /**
     * Constructs a new `GymOwner` object by inheriting properties from the `User` base class.
     *
     * @param username The gym owner's username.
     * @param userId The unique user ID.
     * @param password The gym owner's password.
     * @param email The gym owner's email address.
     * @param name The gym owner's full name.
     * @param roleId The role ID assigned to the gym owner.
     * @param status The current account status of the gym owner.
     */
    public GymOwner(String username, int userId, String password, String email, String name, int roleId, String status) {
        super(username, userId, password, email, name, roleId, status);
    }

    /**
     * Retrieves the gym owner's bank account number.
     *
     * @return The bank account number.
     */
    public String getAccountNumber() {
        return accountNumber;
    }

    /**
     * Sets the gym owner's bank account number.
     *
     * @param accountNumber The new bank account number.
     */
    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    /**
     * Retrieves the unique ID of the gym owner.
     *
     * @return The gym owner ID.
     */
    public int getGymOwnerId() {
        return gymOwnerId;
    }

    /**
     * Sets the unique ID for the gym owner.
     *
     * @param gymOwnerId The new gym owner ID.
     */
    public void setGymOwnerId(int gymOwnerId) {
        this.gymOwnerId = gymOwnerId;
    }

    /**
     * Retrieves the gym owner's phone number.
     *
     * @return The phone number.
     */
    public String getPhone() {
        return phone;
    }

    /**
     * Sets the gym owner's phone number.
     *
     * @param phone The new phone number.
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * Retrieves the gym owner's PAN number.
     *
     * @return The PAN number.
     */
    public String getPanNumber() {
        return panNumber;
    }

    /**
     * Sets the gym owner's PAN number.
     *
     * @param panNumber The new PAN number.
     */
    public void setPanNumber(String panNumber) {
        this.panNumber = panNumber;
    }
}