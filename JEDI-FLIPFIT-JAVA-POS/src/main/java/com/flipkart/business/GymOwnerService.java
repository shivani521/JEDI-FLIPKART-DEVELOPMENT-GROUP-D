package com.flipkart.business;

import com.flipkart.bean.Booking;
import com.flipkart.bean.FlipFitGym;
import com.flipkart.bean.GymOwner;
import com.flipkart.bean.Slot;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.sql.Date;
import java.sql.Time;

public class GymOwnerService implements GymOwnerInterface {

    // Hardcoded credentials for login
    private static final String ADMIN_USERNAME = "admin";
    private static final String ADMIN_PASSWORD = "password123";

    public static List<GymOwner> getPendingGymOwners() {
        return List.of();
    }

    @Override
    public boolean login(String userName, String password) {
        System.out.println("Login method called.");
        if (userName.equals(ADMIN_USERNAME) && password.equals(ADMIN_PASSWORD)) {
            System.out.println("Login successful!");
            return true;
        } else {
            System.out.println("Login failed. Invalid credentials.");
            return false;
        }
    }

    @Override
    public boolean register(int userId, String userName, String email, String password, String adharCardNumber, List<Integer> gymCenterId) {
        System.out.println("Register method called.");
        System.out.println("Registering new gym owner: " + userName);
        return true; // Assume registration is always successful for now
    }

    @Override
    public GymOwner createGymOwner(int userId, String userName, String email, String password, String phoneNumber, String adharCardNumber, String panNumber, List<Integer> gymCenterId, boolean status) {
        System.out.println("createGymOwner method called.");
        String name = userName;
        int roleId = 2; // Example roleId for GymOwner
        String statusStr = status ? "ACTIVE" : "INACTIVE";
        GymOwner gymOwner = new GymOwner(userName, userId, password, email, name, roleId, statusStr);

        gymOwner.setAccountNumber(adharCardNumber); // Or use a separate field for account number
        gymOwner.setGymOwnerId(userId);

        return gymOwner;
    }

    @Override
    public boolean createGym(int gymId, String gymName, int ownerId, String ownerName, String gymAddress, int numberOfSlots, int cost, int gymStatus, String ownerGstNumber) {
        System.out.println("createGym method called.");
        System.out.println("Creating new gym: " + gymName + " with ID " + gymId);
        return true; // Always return true to simulate success
    }

    @Override
    public void deleteGym(int gymId) {
        System.out.println("deleteGym method called.");
        System.out.println("Deleting gym with ID: " + gymId);
    }

    @Override
    public void updateGym() {
        System.out.println("updateGym method called.");
        System.out.println("Gym information updated successfully.");
    }

    @Override
    public boolean createSlot(int slotId, int gymId, LocalDate slotDate, LocalTime slotTime) {
        System.out.println("createSlot method called.");
        System.out.println("Creating slot " + slotId + " for gym " + gymId + " at " + slotDate + " " + slotTime);
        return true; // Always return true
    }

    @Override
    public boolean removeSlot(int slotId) {
        System.out.println("removeSlot method called.");
        System.out.println("Removing slot with ID: " + slotId);
        return true; // Always return true
    }

    @Override
    public List<Slot> getPendingSlots() {
        System.out.println("getPendingSlots method called.");
        System.out.println("Fetching all pending slots...");
        List<Slot> pendingSlots = new ArrayList<>();

        // Example slots, using the correct constructor (convert to java.sql.Date and java.sql.Time)
        pendingSlots.add(
                new Slot(
                        "PENDING", // status
                        101,       // slotId
                        1,         // gymId
                        Time.valueOf(LocalTime.of(10, 0)), // startTime
                        Time.valueOf(LocalTime.of(11, 0)), // endTime (dummy)
                        10,        // seatsAvailable (dummy)
                        500.0,     // price (dummy)
                        Date.valueOf(LocalDate.of(2025, 8, 22)), // slotDate
                        10         // totalSeats (dummy)
                )
        );
        pendingSlots.add(
                new Slot(
                        "PENDING",
                        102,
                        2,
                        Time.valueOf(LocalTime.of(15, 0)),
                        Time.valueOf(LocalTime.of(16, 0)),
                        10,
                        500.0,
                        Date.valueOf(LocalDate.of(2025, 8, 23)),
                        10
                )
        );
        return pendingSlots;
    }

    @Override
    public List<Booking> getAllBookings() {
        System.out.println("getAllBookings method called.");
        System.out.println("Fetching all bookings...");
        return new ArrayList<>();
    }

    @Override
    public List<FlipFitGym> getPendingGyms() {
        return List.of();
    }
}