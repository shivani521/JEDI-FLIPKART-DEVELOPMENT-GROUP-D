package com.flipkart.bean;

/**
 * The `Payment` class represents a payment transaction in the FlipFit system.
 * It stores details related to a specific payment, including a unique payment ID
 * and a transaction ID.
 */
public class Payment {

    /**
     * The transaction ID provided by the payment gateway.
     */
    private String transactionId;

    /**
     * A unique identifier for the payment within the system.
     */
    private int paymentId;

    /**
     * A static counter to keep track of the total number of payments.
     */
    static int paymentCounter = 0;

    /**
     * Constructs a new `Payment` object with the specified payment and transaction IDs.
     *
     * @param paymentId The unique ID for the payment.
     * @param transactionId The transaction ID from the payment gateway.
     */
    public Payment(int paymentId, String transactionId) {
        this.paymentId = paymentId;
        this.transactionId = transactionId;
    }

    /**
     * Retrieves the unique ID of the payment.
     *
     * @return The payment ID.
     */
    public int getPaymentId() {
        return paymentId;
    }

    /**
     * Sets the unique ID of the payment.
     *
     * @param paymentId The new payment ID.
     */
    public void setPaymentId(int paymentId) {
        this.paymentId = paymentId;
    }

    /**
     * Retrieves the transaction ID of the payment.
     *
     * @return The transaction ID.
     */
    public String getTransactionId() {
        return transactionId;
    }

    /**
     * Sets the transaction ID for the payment.
     *
     * @param transactionId The new transaction ID.
     */
    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    /**
     * Retrieves the total count of payments.
     *
     * @return The static payment counter.
     */
    public static int getPaymentCounter() {
        return paymentCounter;
    }

    /**
     * Sets the total count of payments.
     *
     * @param paymentCounter The new value for the payment counter.
     */
    public static void setPaymentCounter(int paymentCounter) {
        Payment.paymentCounter = paymentCounter;
    }
}