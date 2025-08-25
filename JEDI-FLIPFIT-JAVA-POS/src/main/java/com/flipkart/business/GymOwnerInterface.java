package com.flipkart.business;

import com.flipkart.bean.Booking;
import com.flipkart.bean.FlipFitGym;
import com.flipkart.bean.GymOwner;
import com.flipkart.bean.Slot;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

/**
 * The `GymOwnerInterface` defines the business logic and operations available
 * to a gym owner. It provides methods for managing gym details, slots, and bookings.
 */
public interface GymOwnerInterface {

    /**
     * Authenticates a gym owner for login.
     *
     * @param userName The username of the gym owner.
     * @param password The password of the gym owner.
     * @return `true` if the login credentials are valid, `false` otherwise.
     */
    public boolean login (String userName, String password);

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
    public boolean register (int userId, String userName, String email, String password, String adharCardNumber,
                             List<Integer> gymCenterId,String status);

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
    public GymOwner createGymOwner(int userId, String userName, String email, String password, String phoneNumber,String adharCardNumber,String panNumber,List<Integer> gymCenterId,boolean status) ;

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
    public boolean createGym(int gymId,String gymName,int ownerId,String ownerName,String gymAddress,int numberOfSlots,int cost,int gymStatus,String ownerGstNumber);

    /**
     * Deletes a gym from the system.
     *
     * @param gymId The unique ID of the gym to be deleted.
     */
    public void deleteGym(int gymId);

    /**
     * Updates an existing gym's details.
     */
    public void updateGym();

    /**
     * Creates a new slot for a gym.
     *
     * @param slotId The unique ID for the new slot.
     * @param gymId The ID of the gym to which the slot belongs.
     * @param slotDate The date of the slot.
     * @param slotTime The time of the slot.
     * @return `true` if the slot was created successfully, `false` otherwise.
     */
    public boolean createSlot( int slotId,int gymId,LocalDate slotDate, LocalTime slotTime);

    /**
     * Removes a slot from the system.
     *
     * @param slotId The unique ID of the slot to be removed.
     * @return `true` if the slot was removed successfully, `false` otherwise.
     */
    public boolean removeSlot(int slotId);

    /**
     * Retrieves a list of all slots that are pending approval.
     *
     * @return A list of `Slot` objects that are pending.
     */
    public List<Slot> getPendingSlots();

    /**
     * Retrieves a list of all bookings for the gym owner's gyms.
     *
     * @return A list of all `Booking` objects.
     */
    public List<Booking> getAllBookings();

    /**
     * Retrieves a list of all gyms that are pending approval.
     *
     * @return A list of `FlipFitGym` objects that are pending.
     */
    public List<FlipFitGym> getPendingGyms();
}