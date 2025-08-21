package com.flipkart.business;

import com.flipkart.bean.Booking;
import com.flipkart.bean.FlipFitGym;
import com.flipkart.bean.GymOwner;
import com.flipkart.bean.Slot;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

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
    public GymOwner createGymOwner(int userId, String userName, String email, String password, String phoneNumber,String adharCardNumber,String panNumber,List<Integer> gymCenterId,boolean status) {
        System.out.println("createGymOwner method called.");
        // Returns a hardcoded GymOwner object
        return new GymOwner(userId, userName, email, password,phoneNumber, adharCardNumber,panNumber,gymCenterId,status);
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
        // Returns a hardcoded list of Slots
        List<Slot> pendingSlots = new ArrayList<>();
        pendingSlots.add(new Slot(101, 1, LocalDate.of(2025, 8, 22), LocalTime.of(10, 0)));
        pendingSlots.add(new Slot(102, 2, LocalDate.of(2025, 8, 23), LocalTime.of(15, 0)));
        return pendingSlots;
    }

    @Override
    public List<Booking> getAllBookings() {
        System.out.println("getAllBookings method called.");
        System.out.println("Fetching all bookings...");
        // Returns an empty list to simulate no bookings for now
        return new ArrayList<>();
    }

    @Override
    public List<FlipFitGym> getPendingGyms() {
        return List.of();
    }
}