package com.flipkart.business;

import com.flipkart.bean.Booking;
import com.flipkart.bean.Customer;
import com.flipkart.bean.FlipFitGym;
import com.flipkart.bean.Slot;
import com.flipkart.dao.FlipFitCustomerDaoImpl;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class CustomerService implements CustomerInterface {

    private FlipFitCustomerDaoImpl customerDao = new FlipFitCustomerDaoImpl();

    public CustomerService() {
        // DB-backed service — no hardcoded in-memory users
    }

    @Override
    public List<FlipFitGym> viewAllGymCenters(String city) {
        if (city.equalsIgnoreCase("Bangalore")) {
            List<FlipFitGym> gyms = new ArrayList<>();
            gyms.add(new FlipFitGym(1, "Fitness Hub", 101, "shivani", "Bangalore", 10, 500, 1, "GST12345"));
            gyms.add(new FlipFitGym(2, "Powerhouse Gym", 102, "sharma", "Bangalore", 15, 600, 1, "GST67890"));
            return gyms;
        } else {
            return new ArrayList<>();
        }
    }

    @Override
    public List<Slot> viewAllFreeSlots(String gymId, LocalDate date) {
        // Demo only
        return new ArrayList<>();
    }

    @Override
    public List<Booking> viewAllBookings(String userId) {
        // Demo only
        return new ArrayList<>();
    }

    @Override
    public boolean bookSlot(int userId, String gymId, String slotId, LocalDate date, LocalTime time) {
        // Implement actual booking logic with DB if needed
        return true;
    }

    @Override
    public boolean cancelSlot(String bookingId) {
        // Implement actual cancel logic with DB if needed
        return true;
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
            // 1. Insert User, get userId
            int userId = customerDao.createUser(username, password, email, name, roleId, status);

            // 2. Insert Customer, get customerId
            int customerId = customerDao.createCustomer(userId, phoneNo);

            // 3. Build and return Customer bean
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
        // TODO: implement DB-backed password change via DAO
        // This method currently is unimplemented for DB — see notes below.
        return false;
    }

    @Override
    public Customer login(String userName, String password) {
        try {
            // Authenticate against DB only
            System.out.println("*****");
            return customerDao.getCustomerByCredentials(userName, password);
        } catch (Exception e) {
            System.out.println("Error during login: " + e.getMessage());
            return null;
        }
    }
}