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

public class CustomerService implements CustomerInterface {

    private FlipFitCustomerDaoImpl customerDao = new FlipFitCustomerDaoImpl();
    private FlipFitGymDaoImpl gymDao = new FlipFitGymDaoImpl();
    private FlipFitSlotDaoImpl slotDao = new FlipFitSlotDaoImpl();
    private FlipFitBookingDaoImpl bookingDao = new FlipFitBookingDaoImpl();

    public CustomerService() {
        // DB-backed service
    }

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

    @Override
    public boolean changePassword(String username, String oldPassword, String newPassword) {
        try {
            return customerDao.changePassword(username, oldPassword, newPassword);
        } catch (Exception e) {
            System.out.println("Error changing password: " + e.getMessage());
            return false;
        }
    }

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