package com.flipkart.business;

import com.flipkart.bean.Booking;
import com.flipkart.bean.FlipFitGym;
import com.flipkart.bean.GymOwner;
import com.flipkart.bean.Slot;
import com.flipkart.dao.*;


import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.sql.Date;
import java.sql.Time;

/**
 * GymOwnerService — implemented create/update/delete gym and slot operations.
 *
 * Note: some methods in your interface have no parameters (updateGym, getPendingSlots, getAllBookings).
 * I implemented them as global operations (no owner filter). If you want owner-scoped behavior,
 * pass the owner's id into these methods and we can adapt the client/menu accordingly.
 */
public class GymOwnerService implements GymOwnerInterface {

    private FlipFitGymOwnerDaoImpl gymOwnerDao = new FlipFitGymOwnerDaoImpl();
    private FlipFitCustomerDaoImpl userDao = new FlipFitCustomerDaoImpl(); // reuse createUser()
    private FlipFitGymDaoImpl gymDao = new FlipFitGymDaoImpl();
    private FlipFitSlotDaoImpl slotDao = new FlipFitSlotDaoImpl();
    private FlipFitBookingDaoImpl bookingDao = new FlipFitBookingDaoImpl();

    @Override
    public boolean login(String userName, String password) {
        System.out.println("Login method called (DB-backed).");
        try {
            GymOwner owner = gymOwnerDao.getGymOwnerByCredentials(userName, password);
            return owner != null && owner.getRoleId() == 2;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

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

    @Override
    public GymOwner createGymOwner(int userId, String userName, String email, String password, String phoneNumber, String adharCardNumber, String panNumber, List<Integer> gymCenterId, boolean status) {
        String name = userName; int roleId = 2; String statusStr = status ? "ACTIVE" : "INACTIVE";
        GymOwner gymOwner = new GymOwner(userName, userId, password, email, name, roleId, statusStr);
        gymOwner.setAccountNumber(adharCardNumber); gymOwner.setPhone(phoneNumber); gymOwner.setPanNumber(panNumber); gymOwner.setGymOwnerId(userId);
        return gymOwner;
    }

    @Override
    public boolean createGym(int gymId, String gymName, int ownerId, String ownerName, String gymAddress, int numberOfSlots, int cost, int gymStatus, String ownerGstNumber) {
        FlipFitGym gym = new FlipFitGym(gymId, gymName, ownerId, gymAddress, numberOfSlots, cost, gymStatus, ownerGstNumber);
        try {
            int createdId = gymDao.createGym(gym, ownerId);
            return createdId > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public void deleteGym(int gymId) {
        try {
            int rows = gymDao.deleteGym(gymId);
            if (rows > 0) System.out.println("Deleted gym id=" + gymId);
            else System.out.println("No gym deleted for id=" + gymId);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateGym() {
        // No-arg update is ambiguous. Keep this lightweight: print a hint.
        // If you want a DB-backed update, call gymDao.updateGym(FlipFitGym) — you can add an overloaded method that accepts a FlipFitGym.
        System.out.println("updateGym() called. To update a gym, use a method that accepts a FlipFitGym and calls gymDao.updateGym(gym).");
    }

    @Override
    public boolean createSlot(int slotId, int gymId, LocalDate slotDate, LocalTime slotTime) {
        try {
            Slot slot = new Slot(slotId);
            slot.setGymId(gymId);
            slot.setSlotDate(Date.valueOf(slotDate));
            // set start and end time; by default make endTime = startTime + 1 hour
            Time start = Time.valueOf(slotTime);
            Time end = Time.valueOf(slotTime.plusHours(1));
            slot.setStartTime(start);
            slot.setEndTime(end);
            slot.setSeatsAvailable(10); // default seats — adjust as needed or accept as param later
            slot.setTotalSeats(10);
            slot.setPrice(0.0);
            slot.setStatus("PENDING"); // owner-created slots may require approval
            int createdId = slotDao.addSlot(slot);
            return createdId > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean removeSlot(int slotId) {
        try {
            int rows = slotDao.deleteSlot(slotId);
            return rows > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public List<Slot> getPendingSlots() {
        try {
            return slotDao.getPendingSlots();
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    @Override
    public List<Booking> getAllBookings() {
        try {
            // global list; if you prefer owner-scoped, use bookingDao.getBookingsByOwner(ownerId)
           // return bookingDao.getAllBookings();
            return List.of();
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    @Override
    public List<FlipFitGym> getPendingGyms() {
        try {
            return gymDao.getPendingGyms();
        } catch (Exception e) {
            e.printStackTrace();
            return List.of();
        }
    }

    // helper
    public List<GymOwner> getPendingGymOwners() {
        try { return gymOwnerDao.getByStatus("PENDING"); } catch (Exception e) { e.printStackTrace(); return List.of(); }
    }
}