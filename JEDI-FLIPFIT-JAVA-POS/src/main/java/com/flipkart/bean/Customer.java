package com.flipkart.bean;

/**
 * The Customer class extends the base User class to represent a customer within the system.
 * It contains specific attributes relevant to a customer, such as a unique customer ID and phone number.
 *
 * @see com.flipkart.bean.User
 * @author YourName
 * @version 1.0
 * @since 2025-08-24
 */
public class Customer extends User {

    /**
     * A unique identifier for the customer.
     */
    private int customerId;

    /**
     * The customer's phone number.
     */
    private String phone;

    /**
     * Constructs a new Customer object by inheriting properties from the User class.
     *
     * @param username The customer's username.
     * @param userId The unique user ID.
     * @param password The customer's password.
     * @param email The customer's email address.
     * @param name The customer's full name.
     * @param roleId The role ID assigned to the customer.
     * @param status The current status of the customer's account.
     */
    public Customer(String username, int userId, String password, String email, String name, int roleId, String status) {
        super(username, userId, password, email, name, roleId, status);
    }

    /**
     * Retrieves the customer's phone number.
     *
     * @return The phone number as a String.
     */
    public String getPhone() {
        return phone;
    }

    /**
     * Sets the customer's phone number.
     *
     * @param phone The new phone number to set.
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * Retrieves the unique customer ID.
     *
     * @return The customer ID as an integer.
     */
    public int getCustomerId() {
        return customerId;
    }

    /**
     * Sets the unique customer ID.
     *
     * @param customerId The new customer ID to set.
     */
    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }
}