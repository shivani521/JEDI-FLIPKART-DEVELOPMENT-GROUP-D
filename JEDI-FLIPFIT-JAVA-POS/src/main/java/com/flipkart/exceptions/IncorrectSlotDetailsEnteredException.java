package com.flipkart.exceptions;

/**
 * Thrown when the details provided for a gym slot (e.g., start time, end time) are incorrect or invalid.
 * This is a custom checked exception.
 */
public class IncorrectSlotDetailsEnteredException extends Exception {

    /**
     * Constructs a new IncorrectSlotDetailsEnteredException.
     */
    public IncorrectSlotDetailsEnteredException() {

    }

    /**
     * Returns a detailed message about the exception.
     *
     * @return The error message indicating incorrect slot details.
     */
    @Override
    public String getMessage() {
        return "Slot details entered incorrectly.\n";
    }
}