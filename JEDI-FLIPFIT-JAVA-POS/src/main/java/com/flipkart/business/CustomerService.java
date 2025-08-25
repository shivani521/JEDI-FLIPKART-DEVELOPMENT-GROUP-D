package com.flipkart.business;

import com.flipkart.bean.Booking;
import com.flipkart.bean.Customer;
import com.flipkart.bean.FlipFitGym;
import com.flipkart.bean.Slot;
import com.flipkart.dao.FlipFitBookingDaoImpl;
import com.flipkart.dao.FlipFitCustomerDaoImpl;
import com.flipkart.dao.FlipFitGymDaoImpl;
import com.flipkart.dao.FlipFitSlotDaoImpl;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.sql.Date;
import java.sql.Time;

/**
 * The `CustomerService` class implements the `CustomerInterface` to provide
 * all business logic and operations related to a customer.
 * This service interacts with the data access objects (DAOs) to perform
 * actions like viewing gyms, booking slots, and managing customer accounts.
 */
public class CustomerService implements CustomerInterface {

    private FlipFitCustomerDaoImpl customerDao = new FlipFitCustomerDaoImpl();
    private FlipFitGymDaoImpl gymDao = new FlipFitGymDaoImpl();
    private FlipFitSlotDaoImpl slotDao = new FlipFitSlotDaoImpl();
    private FlipFitBookingDaoImpl bookingDao = new FlipFitBookingDaoImpl();

    public CustomerService() {
        // DB-backed service
    }

    /**
     * Retrieves a list of all gym centers available in a specific city.
     *
     * @param city The city for which to view gym centers.
     * @return A list of `FlipFitGym` objects located in the specified city.
     */
    @Override
    public List<FlipFitGym> viewAllGymCenters(String city) {
        try {
            return List.of();
            //return gymDao.getGymsByCity(city);
        } catch (Exception e) {
            System.out.println("Error fetching gyms: " + e.getMessage());
            return new ArrayList<>();
        }
    }

    /**
     * Retrieves a list of all free slots for a specific gym on a given date.
     *
     * @param gymId The unique ID of the gym.
     * @param date The date for which to check free slots.
     * @return A list of available `Slot` objects.
     */
    @Override
    public List<Slot> viewAllFreeSlots(String gymId, LocalDate date) {
        try {
            int gid = Integer.parseInt(gymId);
            return slotDao.getSlotsByGym(gid, Date.valueOf(date));
        } catch (NumberFormatException e) {
            System.out.println("Invalid gym id: " + gymId);
            return new ArrayList<>();
        } catch (Exception e) {
            System.out.println("Error fetching slots: " + e.getMessage());
            return new ArrayList<>();
        }
    }

    /**
     * Retrieves a list of all bookings made by a specific user.
     *
     * @param userId The unique ID of the user.
     * @return A list of `Booking` objects for the specified user.
     */
    @Override
    public List<Booking> viewAllBookings(String userId) {
        try {
            int uid = Integer.parseInt(userId);
            return bookingDao.getBookingsByCustomer(uid);
        } catch (NumberFormatException e) {
            System.out.println("Invalid user id: " + userId);
            return new ArrayList<>();
        } catch (Exception e) {
            System.out.println("Error fetching bookings: " + e.getMessage());
            return new ArrayList<>();
        }
    }

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
    @Override
    public boolean bookSlot(int userId, String gymId, String slotId, LocalDate date, LocalTime time) {
        try {
            int gid = Integer.parseInt(gymId);
            int sid = Integer.parseInt(slotId);
            Date sqlDate = Date.valueOf(date);
            Time sqlTime = Time.valueOf(time);
            int bookingId = bookingDao.createBooking(userId, gid, sid, sqlDate, sqlTime);
            if (bookingId > 0) {
                System.out.println("Booking created with id: " + bookingId);
                return true;
            } else {
                System.out.println("Booking failed (no seats or error).");
                return false;
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid gymId/slotId number.");
            return false;
        } catch (Exception e) {
            System.out.println("Error while booking slot: " + e.getMessage());
            return false;
        }
    }

    /**
     * Cancels an existing booking.
     *
     * @param bookingId The unique ID of the booking to be cancelled.
     * @return `true` if the booking was cancelled successfully, `false` otherwise.
     */
    @Override
    public boolean cancelSlot(String bookingId) {
        try {
            int bid = Integer.parseInt(bookingId);
            boolean ok = bookingDao.cancelBooking(bid);
            if (ok) System.out.println("Booking cancelled: " + bookingId);
            else System.out.println("Cancellation failed for booking: " + bookingId);
            return ok;
        } catch (NumberFormatException e) {
            System.out.println("Invalid booking id: " + bookingId);
            return false;
        } catch (Exception e) {
            System.out.println("Error while cancelling booking: " + e.getMessage());
            return false;
        }
    }

    /**
     * Checks if the provided credentials belong to a valid customer.
     *
     * @param userName The username to validate.
     * @param password The password to validate.
     * @return `true` if the credentials are valid, `false` otherwise.
     */
    @Override
    public boolean checkValidCustomer(String userName, String password) {
        try {
            Customer c = customerDao.getCustomerByCredentials(userName, password);
            return c != null;
        } catch (Exception e) {
            System.out.println("Error checking valid customer: " + e.getMessage());
            return false;
        }
    }

    /**
     * Registers a new customer with the system.
     *
     * @param username The desired username.
     * @param password The password for the new account.
     * @param email The customer's email address.
     * @param phoneNo The customer's phone number.
     * @param customerCity The city where the customer is located.
     * @return The newly created `Customer` object, or `null` if registration fails.
     */
    @Override
    public Customer registerCustomer(String username, String password, String email, String phoneNo, String customerCity) {
        String name = username; // Or ask for real name separately
        int roleId = 1; // Customer
        String status = "ACTIVE";

        try {
            int userId = customerDao.createUser(username, password, email, name, roleId, status);
            int customerId = customerDao.createCustomer(userId, phoneNo);
            Customer customer = new Customer(username, userId, password, email, name, roleId, status);
            customer.setPhone(phoneNo);
            customer.setCustomerId(customerId);
            return customer;
        } catch (Exception e) {
            System.out.println("Error registering customer: " + e.getMessage());
            return null;
        }
    }

    /**
     * Changes a customer's password.
     *
     * @param username The username of the customer.
     * @param oldPassword The current password.
     * @param newPassword The new password to set.
     * @return `true` if the password was changed successfully, `false` otherwise.
     */
    @Override
    public boolean changePassword(String username, String oldPassword, String newPassword) {
        try {
            return customerDao.changePassword(username, oldPassword, newPassword);
        } catch (Exception e) {
            System.out.println("Error changing password: " + e.getMessage());
            return false;
        }
    }

    /**
     * Authenticates a customer for login.
     *
     * @param userName The username of the customer.
     * @param password The password of the customer.
     * @return The `Customer` object if login is successful, `null` otherwise.
     */
    @Override
    public Customer login(String userName, String password) {
        try {
            return customerDao.getCustomerByCredentials(userName, password);
        } catch (Exception e) {
            System.out.println("Error during login: " + e.getMessage());
            return null;
        }
    }
}