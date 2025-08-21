package com.flipkart.business;

import com.flipkart.bean.Booking;
import com.flipkart.bean.FlipFitGym;
import com.flipkart.bean.Slot;
import com.flipkart.bean.Customer;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public interface CustomerInterface {
    public List<FlipFitGym> viewAllGymCenters(String city);
    public List<Slot> viewAllFreeSlots (String gymId, LocalDate date);
    public List<Booking> viewAllBookings (String userId);
    public boolean bookSlot (int userId, String gymId, String slotId, LocalDate date, LocalTime time);
    public boolean cancelSlot (String bookingId);
    public boolean checkValidCustomer (String userName, String password);
    public Customer registerCustomer(String userName, String password, String email, String phoneNo, String customerCity);
    public boolean changePassword(String username, String oldPassword, String newPassword);
    public Customer login(String userName, String password);
}