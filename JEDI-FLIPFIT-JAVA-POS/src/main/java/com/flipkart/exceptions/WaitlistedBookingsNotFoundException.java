package com.flipkart.exceptions;

/**
 * Thrown when no waitlisted bookings are found for a given slot.
 * This is a custom checked exception.
 */
public class WaitlistedBookingsNotFoundException extends Exception {

    /**
     * The ID of the slot for which no waitlisted bookings were found.
     */
    int slotId;

    /**
     * Constructs a new WaitlistedBookingsNotFoundException with the specified slot ID.
     *
     * @param slotId The ID of the slot where no waitlisted bookings were found.
     */
    public WaitlistedBookingsNotFoundException(int slotId) {
        this.slotId = slotId;
    }

    /**
     * Returns a detailed message about the exception.
     *
     * @return The error message including the slot ID.
     */
    @Override
    public String getMessage() {
        return "No Waitlisted bookings exist in slot with slot ID " + slotId;
    }
}