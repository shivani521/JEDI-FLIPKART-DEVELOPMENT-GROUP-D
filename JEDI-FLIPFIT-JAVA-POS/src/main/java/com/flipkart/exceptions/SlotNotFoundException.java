package com.flipkart.exceptions;

/**
 * Thrown when a specific gym slot with the given ID cannot be found.
 * This is a custom checked exception.
 */
public class SlotNotFoundException extends Exception {

    /**
     * The ID of the slot that was not found.
     */
    int slotId;

    /**
     * Constructs a new SlotNotFoundException with the specified slot ID.
     *
     * @param slotId The ID of the slot that was not found.
     */
    public SlotNotFoundException(int slotId) {
        this.slotId = slotId;
    }

    /**
     * Returns the ID of the slot that caused the exception.
     *
     * @return The slot ID.
     */
    public int getSlotId() {
        return slotId;
    }

    /**
     * Returns a detailed message about the exception.
     *
     * @return The error message including the slot ID.
     */
    @Override
    public String getMessage() {
        return "Slot " + slotId + " does not exist\n";
    }
}