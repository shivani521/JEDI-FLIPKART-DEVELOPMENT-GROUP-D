package com.flipkart.exceptions;

/**
 * Thrown when the details provided for a booking are invalid.
 * This is a custom checked exception.
 */
public class InvalidBookingDetailsException extends Exception {

    /**
     * Constructs a new InvalidBookingDetailsException.
     */
    public InvalidBookingDetailsException() {
    }

    /**
     * Returns a detailed message about the exception.
     *
     * @return The error message indicating invalid booking details.
     */
    @Override
    public String getMessage() {
        return "Invalid booking details entered";
    }
}