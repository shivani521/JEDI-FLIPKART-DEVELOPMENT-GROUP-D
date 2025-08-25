package com.flipkart.exceptions;

/**
 * Thrown when a gym with the specified ID is not found.
 * This is a custom checked exception.
 */
public class GymNotFoundException extends Exception {

    /**
     * The ID of the gym that was not found.
     */
    int gymId;

    /**
     * Constructs a new GymNotFoundException with the specified gym ID.
     *
     * @param gymId The ID of the gym that was not found.
     */
    public GymNotFoundException(int gymId) {
        this.gymId = gymId;
    }

    /**
     * Returns a detailed message about the exception.
     *
     * @return The error message including the gym ID.
     */
    @Override
    public String getMessage() {
        return "Gym " + gymId + " not found\n";
    }
}