package com.flipkart.bean;

public class Payment {
    private String transactionId;
    private int paymentId;
    static int paymentCounter = 0;

    public Payment(int paymentId, String transactionId) {
        this.paymentId = paymentId;
        this.transactionId = transactionId;
    }

    public int getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(int paymentId) {
        this.paymentId = paymentId;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public static int getPaymentCounter() {
        return paymentCounter;
    }

    public static void setPaymentCounter(int paymentCounter) {
        Payment.paymentCounter = paymentCounter;
    }
}