package com.flipkart.exceptions;

/**
 * Thrown when a new slot is created that overlaps in time with an existing slot in the same gym.
 * This is a custom checked exception.
 */
public class SlotIntersectException extends Exception {

    /**
     * Constructs a new SlotIntersectException.
     */
    public SlotIntersectException() {

    }

    /**
     * Returns a detailed message about the exception.
     *
     * @return The error message indicating a time overlap.
     */
    @Override
    public String getMessage() {
        return "Slot timings intersect with those of other slots in the same gym.";
    }
}