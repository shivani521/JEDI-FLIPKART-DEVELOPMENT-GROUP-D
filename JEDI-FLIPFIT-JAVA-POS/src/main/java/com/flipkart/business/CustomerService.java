package com.flipkart.business;

import com.flipkart.bean.Booking;
import com.flipkart.bean.FlipFitGym;
import com.flipkart.bean.Slot;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public class CustomerService  implements CustomerInterface {
    @Override
    public List<FlipFitGym> viewAllGymCenters(String city) {
        return List.of();
    }

    @Override
    public List<Slot> viewAllFreeSlots(String gymId, LocalDate date) {
        return List.of();
    }

    @Override
    public List<Booking> viewAllBookings(String gymId, LocalDate date) {
        return List.of();
    }

    @Override
    public boolean bookSlot(String userId, String gymId, String slotId, LocalDate date, LocalTime time) {
        return false;
    }

    @Override
    public boolean cancelSlot(String gymId, LocalDate date, LocalTime time) {
        return false;
    }

    @Override
    public boolean checkValidCustomer(String userName, String password) {
        return false;
    }

    @Override
    public boolean registerCustomer(String userName, String password, String email, String city, String cardNumber) {
        return false;
    }

    @Override
    public boolean changePassword(String username, String oldPassword, String newPassword) {
        return false;
    }

    @Override
    public void login(String userName, String password) {

    }
}
