package com.flipkart.exceptions;

/**
 * Thrown when an administrator attempts to approve a gym slot that has already been approved.
 * This is a custom checked exception.
 */
public class SlotAlreadyApprovedException extends Exception {

    /**
     * The ID of the slot that has already been approved.
     */
    int slotId;

    /**
     * Constructs a new SlotAlreadyApprovedException with the specified slot ID.
     *
     * @param slotId The ID of the slot that has already been approved.
     */
    public SlotAlreadyApprovedException(int slotId) {
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
        return "Slot with slot ID" + slotId + " already approved\n";
    }
}