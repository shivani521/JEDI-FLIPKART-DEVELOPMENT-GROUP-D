package com.flipkart.exceptions;

/**
 * Thrown when a user selects a role that is not valid or doesn't match their credentials.
 * This is a custom checked exception.
 */
public class IncorrectRoleSelectedException extends Exception {

    /**
     * Constructs a new IncorrectRoleSelectedException.
     */
    public IncorrectRoleSelectedException() {

    }

    /**
     * Returns a detailed message about the exception.
     *
     * @return The error message indicating an incorrect role was selected.
     */
    @Override
    public String getMessage() {
        return "Incorrect role selected\n";
    }
}