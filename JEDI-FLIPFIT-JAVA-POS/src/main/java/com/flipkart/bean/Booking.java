package com.flipkart.bean;

import java.sql.Date;
import java.sql.Timestamp;

public class Booking {
    private int bookingId;
    private int customerId;
    private int gymId;
    private int slotId;
    private Date bookingDate;
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

    public Booking() {

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

    public int getGymId() {
        return gymId;
    }

    public void setGymId(int gymId) {
        this.gymId = gymId;
    }

    public int getSlotId() {
        return slotId;
    }

    public void setSlotId(int slotId) {
        this.slotId = slotId;
    }

    public Date getBookingDate() {
        return bookingDate;
    }

    public void setBookingDate(Date bookingDate) {
        this.bookingDate = bookingDate;
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