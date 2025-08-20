package com.flipkart.bean;

public class Payment {
    private int paymentId;
    private String bookingId;
    private float amountPaid;

    public Payment() {}

    public int getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(int paymentId) {
        this.paymentId = paymentId;
    }

    public String getBookingId() {
        return bookingId;
    }
    public void setBookingId(String bookingId) {
        this.bookingId = bookingId;
    }

    public float getAmountPaid() {
        return amountPaid;
    }
    public void setAmountPaid(float amountPaid) {
        this.amountPaid = amountPaid;
    }
}