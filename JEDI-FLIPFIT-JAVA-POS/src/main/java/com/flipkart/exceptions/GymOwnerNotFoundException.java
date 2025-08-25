package com.flipkart.exceptions;

/**
 * Thrown when a gym owner with the specified ID is not found in the system.
 * This is a custom checked exception.
 */
public class GymOwnerNotFoundException extends Exception {

    /**
     * The ID of the gym owner that was not found.
     */
    int gymOwnerId;

    /**
     * Constructs a new GymOwnerNotFoundException with the specified gym owner ID.
     *
     * @param gymOwnerId The ID of the gym owner that was not found.
     */
    public GymOwnerNotFoundException(int gymOwnerId) {
        this.gymOwnerId = gymOwnerId;
    }

    /**
     * Returns a detailed message about the exception.
     *
     * @return The error message including the gym owner ID.
     */
    @Override
    public String getMessage() {
        return "Gym owner with id " + gymOwnerId + " does not exist.";
    }
}