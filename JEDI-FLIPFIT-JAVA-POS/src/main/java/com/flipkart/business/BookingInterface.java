package com.flipkart.business;

public interface BookingInterface {
    public boolean addBooking (String userId, String slotId, String gymId);
    public boolean checkValidBooking (String slotId, String gymId);
    public boolean cancelBooking (String bookingId);
}