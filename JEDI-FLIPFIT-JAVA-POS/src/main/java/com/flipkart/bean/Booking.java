package com.flipkart.bean;

import java.sql.Time;
import java.sql.Timestamp;
import java.util.Date;

public class Booking {
    private int bookingId;
    private int customerId;
    private String bookingStatus;
    private int paymentId;
    private Timestamp createdAt;

    public Booking(int customerId, int bookingId, String bookingStatus, int paymentId, Timestamp createdAt) {
        this.customerId = customerId;
        this.bookingId = bookingId;
        this.bookingStatus = bookingStatus;
        this.paymentId = paymentId;
        this.createdAt = createdAt;
    }


    public int getBookingId() {
        return bookingId;
    }

    public void setBookingId(int bookingId) {
        this.bookingId = bookingId;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public String getBookingStatus() {
        return bookingStatus;
    }

    public void setBookingStatus(String bookingStatus) {
        this.bookingStatus = bookingStatus;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    public int getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(int paymentId) {
        this.paymentId = paymentId;
    }
}