package com.flipkart.business;

import com.flipkart.bean.Booking;
import com.flipkart.bean.FlipFitGym;
import com.flipkart.bean.Slot;
import com.flipkart.bean.Customer;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

/**
 * The `CustomerInterface` defines the business operations and logic
 * available to a customer in the FlipFit system.
 */
public interface CustomerInterface {

    /**
     * Retrieves a list of all gym centers available in a specific city.
     *
     * @param city The city for which to view gym centers.
     * @return A list of `FlipFitGym` objects located in the specified city.
     */
    List<FlipFitGym> viewAllGymCenters(String city);

    /**
     * Retrieves a list of all free slots for a specific gym on a given date.
     *
     * @param gymId The unique ID of the gym.
     * @param date The date for which to check free slots.
     * @return A list of available `Slot` objects.
     */
    List<Slot> viewAllFreeSlots (String gymId, LocalDate date);

    /**
     * Retrieves a list of all bookings made by a specific user.
     *
     * @param userId The unique ID of the user.
     * @return A list of `Booking` objects for the specified user.
     */
    List<Booking> viewAllBookings (String userId);

    /**
     * Books a specific slot for a user at a gym.
     *
     * @param userId The unique ID of the user making the booking.
     * @param gymId The unique ID of the gym.
     * @param slotId The unique ID of the slot to be booked.
     * @param date The date of the booking.
     * @param time The time of the booking.
     * @return `true` if the slot was booked successfully, `false` otherwise.
     */
    boolean bookSlot (int userId, String gymId, String slotId, LocalDate date, LocalTime time);

    /**
     * Cancels an existing booking.
     *
     * @param bookingId The unique ID of the booking to be cancelled.
     * @return `true` if the booking was cancelled successfully, `false` otherwise.
     */
    boolean cancelSlot (String bookingId);

    /**
     * Checks if the provided credentials belong to a valid customer.
     *
     * @param userName The username to validate.
     * @param password The password to validate.
     * @return `true` if the credentials are valid, `false` otherwise.
     */
    boolean checkValidCustomer (String userName, String password);

    /**
     * Registers a new customer with the system.
     *
     * @param userName The desired username.
     * @param password The password for the new account.
     * @param email The customer's email address.
     * @param phoneNo The customer's phone number.
     * @param customerCity The city where the customer is located.
     * @return The newly created `Customer` object.
     */
    Customer registerCustomer(String userName, String password, String email, String phoneNo, String customerCity);

    /**
     * Changes a customer's password after verifying their old one.
     *
     * @param username The username of the customer.
     * @param oldPassword The current password.
     * @param newPassword The new password to set.
     * @return `true` if the password was changed successfully, `false` otherwise.
     */
    boolean changePassword(String username, String oldPassword, String newPassword);

    /**
     * Authenticates a customer for login.
     *
     * @param userName The username of the customer.
     * @param password The password of the customer.
     * @return The `Customer` object if login is successful, `null` otherwise.
     */
    Customer login(String userName, String password);
}