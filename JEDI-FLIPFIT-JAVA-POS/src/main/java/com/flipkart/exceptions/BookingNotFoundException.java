package com.flipkart.exceptions;

/**
 * The `BookingNotFoundException` is a custom exception class that is thrown when a booking
 * with a specified ID cannot be found in the system.
 */
public class BookingNotFoundException extends Exception {

    /**
     * The unique ID of the booking that was not found.
     */
    private int bookingId;

    /**
     * Constructs a new `BookingNotFoundException` with the ID of the booking that could not be found.
     *
     * @param bookingId The ID of the non-existent booking.
     */
    public BookingNotFoundException(int bookingId) {
        this.bookingId = bookingId;
    }

    /**
     * Returns a detailed error message for this exception.
     *
     * @return A string containing the error message.
     */
    @Override
    public String getMessage() {
        return "Booking with booking ID " + bookingId + " does not exist.";
    }
}