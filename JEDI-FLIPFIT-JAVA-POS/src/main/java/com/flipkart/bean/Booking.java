package com.flipkart.bean;

public class Booking {
    private int bookingId;
    private String userID;

    public Booking (int bookingId, String userID) {
        this.bookingId = bookingId;
        this.userID = userID;
    }

    public int getBookingID() {
        return bookingId;
    }

    public void setBookingID(int     bookingID) {
        this.bookingId = bookingID;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }


}