package com.flipkart.exceptions;

/**
 * The `EmailAlreadyExistsException` is a custom exception class thrown when a user
 * registration is attempted with an email address that already exists in the system.
 */
public class EmailAlreadyExistsException extends Exception {

    /**
     * The email address that caused the exception.
     */
    private String email;

    /**
     * Constructs a new `EmailAlreadyExistsException` with the email address that already exists.
     *
     * @param email The email address that is not unique.
     */
    public EmailAlreadyExistsException(String email) {
        // Correct assignment
        this.email = email;
    }

    /**
     * Returns a detailed error message for this exception.
     *
     * @return A string containing the error message.
     */
    @Override
    public String getMessage(){
        return "User with email " + email + " already exists. Please enter a unique email address.";
    }
}