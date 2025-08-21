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

    private List<Customer> hardcodedCustomers;
    private FlipFitCustomerDaoImpl customerDao = new FlipFitCustomerDaoImpl();

    public CustomerService() {
        hardcodedCustomers = new ArrayList<>();
        // Optionally, add demo customers here for testing
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
        for (Customer customer : hardcodedCustomers) {
            if (customer.getUsername().equals(userName) && customer.getPassword().equals(password)) {
                return true;
            }
        }
        return false;
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
        for (Customer customer : hardcodedCustomers) {
            if (customer.getUsername().equals(username) && customer.getPassword().equals(oldPassword)) {
                customer.setPassword(newPassword);
                return true;
            }
        }
        return false;
    }

    @Override
    public Customer login(String userName, String password) {
        for (Customer customer : hardcodedCustomers) {
            if (customer.getUsername().equals(userName) && customer.getPassword().equals(password)) {
                return customer;
            }
        }
        return null;
    }
}