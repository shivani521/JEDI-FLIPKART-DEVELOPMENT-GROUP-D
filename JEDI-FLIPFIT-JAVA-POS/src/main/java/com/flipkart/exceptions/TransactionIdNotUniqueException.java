package com.flipkart.exceptions;

/**
 * Thrown when a transaction ID provided for a new transaction already exists in the system.
 * This ensures that each transaction can be uniquely identified.
 * This is a custom checked exception.
 */
public class TransactionIdNotUniqueException extends Exception {

    /**
     * The transaction ID that was not unique.
     */
    String trransactionId;

    /**
     * Constructs a new TransactionIdNotUniqueException with the specified transaction ID.
     *
     * @param trransactionId The transaction ID that was not unique.
     */
    public TransactionIdNotUniqueException(String trransactionId) {
        this.trransactionId = trransactionId;
    }

    /**
     * Returns a detailed message about the exception.
     *
     * @return The error message including the non-unique transaction ID.
     */
    @Override
    public String getMessage() {
        return "Transaction ID " + trransactionId + " is not unique. Please enter a unique transaction ID.";
    }
}