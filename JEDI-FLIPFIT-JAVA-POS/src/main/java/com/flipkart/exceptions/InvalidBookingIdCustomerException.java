package com.flipkart.exceptions;

/**
 * Thrown when a customer tries to access a booking with an ID that does not belong to them.
 * This is a custom checked exception.
 */
public class InvalidBookingIdCustomerException extends Exception {

    /**
     * The ID of the booking that was not found for the customer.
     */
    int bookingId;

    /**
     * Constructs a new InvalidBookingIdCustomerException with the specified booking ID.
     *
     * @param bookingId The ID of the booking that was not found.
     */
    public InvalidBookingIdCustomerException(int bookingId) {
        this.bookingId = bookingId;
    }

    /**
     * Returns a detailed message about the exception.
     *
     * @return The error message including the booking ID.
     */
    @Override
    public String getMessage() {
        return "Booking id " + bookingId + " does not exist in your bookings. Please enter a valid Booking ID.";
    }
}