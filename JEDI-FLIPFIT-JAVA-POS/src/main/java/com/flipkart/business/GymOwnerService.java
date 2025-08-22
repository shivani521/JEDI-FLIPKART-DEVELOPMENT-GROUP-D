package com.flipkart.business;

import com.flipkart.bean.Booking;
import com.flipkart.bean.FlipFitGym;
import com.flipkart.bean.GymOwner;
import com.flipkart.bean.Slot;
import com.flipkart.dao.FlipFitCustomerDaoImpl;
import com.flipkart.dao.FlipFitGymOwnerDaoImpl;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.sql.Date;
import java.sql.Time;

public class GymOwnerService implements GymOwnerInterface {

    private FlipFitGymOwnerDaoImpl gymOwnerDao = new FlipFitGymOwnerDaoImpl();
    private FlipFitCustomerDaoImpl userDao = new FlipFitCustomerDaoImpl(); // reuse createUser()

    @Override
    public boolean login(String userName, String password) {
        System.out.println("Login method called (DB-backed).");
        try {
            // Authenticate against GymOwner table (User joined with GymOwner)
            GymOwner owner = gymOwnerDao.getGymOwnerByCredentials(userName, password);
            if (owner != null) {
                // optional: check roleId (2 == GymOwner) to be extra safe
                if (owner.getRoleId() == 2) return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    // rest of class unchanged...
    // (keep your existing register/createGymOwner/etc.)
    @Override
    public boolean register(int userId, String userName, String email, String password, String adharCardNumber, List<Integer> gymCenterId, String status) {
        System.out.println("Register gym owner (DB-backed) called.");
        try {
            int createdUserId = userId;
            if (createdUserId <= 0) {
                createdUserId = userDao.createUser(userName, password, email, userName, 2, status == null || status.isEmpty() ? "PENDING" : status);
            }
            int gymOwnerId = gymOwnerDao.create(createdUserId, adharCardNumber);
            System.out.println("GymOwner created with id: " + gymOwnerId);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error registering gym owner: " + e.getMessage());
            return false;
        }
    }

    @Override public GymOwner createGymOwner(int userId, String userName, String email, String password, String phoneNumber, String adharCardNumber, String panNumber, List<Integer> gymCenterId, boolean status) {
        String name = userName; int roleId = 2; String statusStr = status ? "ACTIVE" : "INACTIVE";
        GymOwner gymOwner = new GymOwner(userName, userId, password, email, name, roleId, statusStr);
        gymOwner.setAccountNumber(adharCardNumber); gymOwner.setPhone(phoneNumber); gymOwner.setPanNumber(panNumber); gymOwner.setGymOwnerId(userId);
        return gymOwner;
    }

    @Override public boolean createGym(int gymId, String gymName, int ownerId, String ownerName, String gymAddress, int numberOfSlots, int cost, int gymStatus, String ownerGstNumber) { return true; }
    @Override public void deleteGym(int gymId) {}
    @Override public void updateGym() {}
    @Override public boolean createSlot(int slotId, int gymId, LocalDate slotDate, LocalTime slotTime) { return true; }
    @Override public boolean removeSlot(int slotId) { return true; }
    @Override public List<Slot> getPendingSlots() { return new ArrayList<>(); }
    @Override public List<Booking> getAllBookings() { return new ArrayList<>(); }
    @Override public List<FlipFitGym> getPendingGyms() { return List.of(); }

    // helper
    public List<GymOwner> getPendingGymOwners() {
        try { return gymOwnerDao.getByStatus("PENDING"); } catch (Exception e) { e.printStackTrace(); return List.of(); }
    }
}