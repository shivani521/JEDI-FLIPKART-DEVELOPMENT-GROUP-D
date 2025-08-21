package com.flipkart.business;

import com.flipkart.bean.Booking;
import com.flipkart.bean.FlipFitGym;
import com.flipkart.bean.Slot;
import com.flipkart.bean.Customer;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public interface CustomerInterface {
    List<FlipFitGym> viewAllGymCenters(String city);
    List<Slot> viewAllFreeSlots (String gymId, LocalDate date);
    List<Booking> viewAllBookings (String userId);
    boolean bookSlot (int userId, String gymId, String slotId, LocalDate date, LocalTime time);
    boolean cancelSlot (String bookingId);
    boolean checkValidCustomer (String userName, String password);
    Customer registerCustomer(String userName, String password, String email, String phoneNo, String customerCity);
    boolean changePassword(String username, String oldPassword, String newPassword);
    Customer login(String userName, String password);
}