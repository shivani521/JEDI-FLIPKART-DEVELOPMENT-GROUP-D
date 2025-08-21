package com.flipkart.business;

import com.flipkart.bean.Booking;
import com.flipkart.bean.FlipFitGym;
import com.flipkart.bean.Slot;

import java.util.List;

public interface GymOwnerInterface {
    public boolean login (String userName, String password);
    public boolean register (String userId, String userName, String email, String password, String adharCardNumber,
                             List<String> gymCenterId);
    public boolean addCenter (String ownerId, String gymId, String city, int capacity, int cost);
    public boolean removeCenter (String ownerId, String gymId);
    public boolean changePassword(String username, String oldPassword, String newPassword);
    public List<FlipFitGym> getPendingGyms();
    public List<Slot> getPendingSlots();
    public List<Booking> getAllBookings();
}