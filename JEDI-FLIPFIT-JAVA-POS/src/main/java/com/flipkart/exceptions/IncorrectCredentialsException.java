package com.flipkart.exceptions;

/**
 * Thrown when a user provides incorrect email or password during authentication.
 * This is a custom checked exception.
 */
public class IncorrectCredentialsException extends Exception {

    /**
     * Constructs a new IncorrectCredentialsException.
     */
    public IncorrectCredentialsException() {

    }

    /**
     * Returns a detailed message about the exception.
     *
     * @return The error message indicating incorrect credentials.
     */
    @Override
    public String getMessage() {
        return "Email or password is incorrect.\n";
    }
}