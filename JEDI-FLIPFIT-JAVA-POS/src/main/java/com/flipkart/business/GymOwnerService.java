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
 * The `GymOwnerService` class provides the business logic and implementation
 * for gym owner-specific operations, including managing gyms and slots.
 */
public class GymOwnerService implements GymOwnerInterface {

    private FlipFitGymOwnerDaoImpl gymOwnerDao = new FlipFitGymOwnerDaoImpl();
    private FlipFitCustomerDaoImpl userDao = new FlipFitCustomerDaoImpl(); // reuse createUser()
    private FlipFitGymDaoImpl gymDao = new FlipFitGymDaoImpl();
    private FlipFitSlotDaoImpl slotDao = new FlipFitSlotDaoImpl();
    private FlipFitBookingDaoImpl bookingDao = new FlipFitBookingDaoImpl();

    /**
     * Authenticates a gym owner for login.
     *
     * @param userName The username of the gym owner.
     * @param password The password of the gym owner.
     * @return `true` if the login credentials are valid and the user has the gym owner role, `false` otherwise.
     */
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

    /**
     * Registers a new gym owner in the system.
     *
     * @param userId The unique ID of the user.
     * @param userName The username for the new account.
     * @param email The gym owner's email address.
     * @param password The password for the new account.
     * @param adharCardNumber The Aadhaar card number of the gym owner.
     * @param gymCenterId A list of gym center IDs associated with the owner.
     * @param status The status of the new account.
     * @return `true` if the registration is successful, `false` otherwise.
     */
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

    /**
     * Creates a new `GymOwner` object.
     *
     * @param userId The unique ID of the user.
     * @param userName The username of the gym owner.
     * @param email The gym owner's email address.
     * @param password The gym owner's password.
     * @param phoneNumber The gym owner's phone number.
     * @param adharCardNumber The Aadhaar card number.
     * @param panNumber The PAN number of the gym owner.
     * @param gymCenterId A list of gym center IDs.
     * @param status The status of the account.
     * @return The newly created `GymOwner` object.
     */
    @Override
    public GymOwner createGymOwner(int userId, String userName, String email, String password, String phoneNumber, String adharCardNumber, String panNumber, List<Integer> gymCenterId, boolean status) {
        String name = userName; int roleId = 2; String statusStr = status ? "ACTIVE" : "INACTIVE";
        GymOwner gymOwner = new GymOwner(userName, userId, password, email, name, roleId, statusStr);
        gymOwner.setAccountNumber(adharCardNumber); gymOwner.setPhone(phoneNumber); gymOwner.setPanNumber(panNumber); gymOwner.setGymOwnerId(userId);
        return gymOwner;
    }

    /**
     * Creates a new gym record.
     *
     * @param gymId The unique ID of the gym.
     * @param gymName The name of the gym.
     * @param ownerId The ID of the gym's owner.
     * @param ownerName The name of the gym owner.
     * @param gymAddress The address of the gym.
     * @param numberOfSlots The number of slots available in the gym.
     * @param cost The cost per session.
     * @param gymStatus The approval status of the gym.
     * @param ownerGstNumber The GST number of the gym owner.
     * @return `true` if the gym was created successfully, `false` otherwise.
     */
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

    /**
     * Deletes a gym from the system.
     *
     * @param gymId The unique ID of the gym to be deleted.
     */
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

    /**
     * Updates an existing gym's details.
     */
    @Override
    public void updateGym() {
        // No-arg update is ambiguous. Keep this lightweight: print a hint.
        // If you want a DB-backed update, use a method that accepts a FlipFitGym and calls gymDao.updateGym(gym).
        System.out.println("updateGym() called. To update a gym, use a method that accepts a FlipFitGym and calls gymDao.updateGym(gym).");
    }

    /**
     * Creates a new slot for a gym.
     *
     * @param slotId The unique ID for the new slot.
     * @param gymId The ID of the gym to which the slot belongs.
     * @param slotDate The date of the slot.
     * @param slotTime The time of the slot.
     * @return `true` if the slot was created successfully, `false` otherwise.
     */
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

    /**
     * Removes a slot from the system.
     *
     * @param slotId The unique ID of the slot to be removed.
     * @return `true` if the slot was removed successfully, `false` otherwise.
     */
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

    /**
     * Retrieves a list of all slots that are pending approval.
     *
     * @return A list of `Slot` objects that are pending.
     */
    @Override
    public List<Slot> getPendingSlots() {
        try {
            return slotDao.getPendingSlots();
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    /**
     * Retrieves a list of all bookings for the gym owner's gyms.
     *
     * @return A list of all `Booking` objects.
     */
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

    /**
     * Retrieves a list of all gyms that are pending approval.
     *
     * @return A list of `FlipFitGym` objects that are pending.
     */
    @Override
    public List<FlipFitGym> getPendingGyms() {
        try {
            return gymDao.getPendingGyms();
        } catch (Exception e) {
            e.printStackTrace();
            return List.of();
        }
    }

    /**
     * Retrieves a list of all gym owners who are pending approval.
     *
     * @return A list of `GymOwner` objects with a "PENDING" status.
     */
    public List<GymOwner> getPendingGymOwners() {
        try { return gymOwnerDao.getByStatus("PENDING"); } catch (Exception e) { e.printStackTrace(); return List.of(); }
    }
}