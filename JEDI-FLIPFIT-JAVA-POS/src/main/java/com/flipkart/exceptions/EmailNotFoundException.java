package com.flipkart.exceptions;

/**
 * Thrown when an email ID is not found in the system.
 * This is a custom checked exception.
 */
public class EmailNotFoundException extends Exception {

    /**
     * The email ID that was not found.
     */
    String email;

    /**
     * Constructs a new EmailNotFoundException with the specified email ID.
     *
     * @param email The email ID that was not found.
     */
    public EmailNotFoundException(String email) {
        this.email = email;
    }

    /**
     * Returns a detailed message describing the exception.
     *
     * @return The error message including the email ID.
     */
    public String getMessage() {
        return "Email ID: " + email + " does not exist.";
    }
}