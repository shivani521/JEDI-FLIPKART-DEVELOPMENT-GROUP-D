package com.flipkart.exceptions;

/**
 * Thrown when a user with the specified ID is not found in the system.
 * This is a custom checked exception.
 */
public class UserNotFoundException extends Exception {

    /**
     * The ID of the user that was not found.
     */
    int userId;

    /**
     * Constructs a new UserNotFoundException with the specified user ID.
     *
     * @param userId The ID of the user that was not found.
     */
    public UserNotFoundException(int userId) {
        this.userId = userId;
    }

    /**
     * Returns a detailed message about the exception.
     *
     * @return The error message including the user ID.
     */
    @Override
    public String getMessage() {
        return "User with id " + userId + " does not exist\n";
    }
}