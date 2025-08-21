package com.flipkart.business;

import com.flipkart.bean.Booking;
import com.flipkart.bean.FlipFitGym;
import com.flipkart.bean.Slot;
import com.flipkart.bean.Customer;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class CustomerService implements CustomerInterface {

    private List<Customer> hardcodedCustomers;

    public CustomerService() {
        hardcodedCustomers = new ArrayList<>();
        // Example hardcoded customers (adjust as needed)
        Customer customer1 = new Customer("shivani", 1, "password123", "shivani@example.com", "Shivani Sharma", 1, "ACTIVE");
        customer1.setPhone("9876543210");
        customer1.setCustomerId(1);
        hardcodedCustomers.add(customer1);

        Customer customer2 = new Customer("sharma", 2, "securepass", "sharma@example.com", "Sharma Singh", 1, "ACTIVE");
        customer2.setPhone("1234567890");
        customer2.setCustomerId(2);
        hardcodedCustomers.add(customer2);
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
        if (gymId.equals("1") && date.isEqual(LocalDate.now())) {
            List<Slot> slots = new ArrayList<>();
            // slots.add(new Slot(1, 1, LocalDate.now(), LocalTime.of(8, 0)));
            // slots.add(new Slot(2, 1, LocalDate.now(), LocalTime.of(9, 0)));
            return slots;
        }
        return new ArrayList<>();
    }

    @Override
    public List<Booking> viewAllBookings(String userId) {
        if (userId.equals("1")) {
            List<Booking> bookings = new ArrayList<>();
            // bookings.add(new Booking(1, 1));
            return bookings;
        }
        return new ArrayList<>();
    }

    @Override
    public boolean bookSlot(int userId, String gymId, String slotId, LocalDate date, LocalTime time) {
        System.out.println("Booking slot " + slotId + " for user " + userId + " at gym " + gymId + " on " + date);
        return true;
    }

    @Override
    public boolean cancelSlot(String bookingId) {
        System.out.println("Cancelling booking with ID: " + bookingId);
        return bookingId.equals("B1");
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
    public Customer registerCustomer(String userName, String password, String email, String phoneNo, String customerCity) {
        System.out.println("Registering new customer: " + userName);
        int newUserId = hardcodedCustomers.size() + 1;
        int newCustomerId = newUserId; // For demo, userId == customerId
        String name = userName; // Or ask for real name separately
        int roleId = 1; // Example role id for customer
        String status = "ACTIVE";
        Customer newCustomer = new Customer(userName, newUserId, password, email, name, roleId, status);
        newCustomer.setPhone(phoneNo);
        newCustomer.setCustomerId(newCustomerId);
        hardcodedCustomers.add(newCustomer);
        System.out.println("Customer registered successfully.");
        return newCustomer;
    }

    @Override
    public boolean changePassword(String username, String oldPassword, String newPassword) {
        System.out.println("Changing password for user: " + username);
        for (Customer customer : hardcodedCustomers) {
            if (customer.getUsername().equals(username) && customer.getPassword().equals(oldPassword)) {
                customer.setPassword(newPassword);
                System.out.println("Password changed successfully.");
                return true;
            }
        }
        System.out.println("Password change failed.");
        return false;
    }

    @Override
    public Customer login(String userName, String password) {
        for (Customer customer : hardcodedCustomers) {
            if (customer.getUsername().equals(userName) && customer.getPassword().equals(password)) {
                System.out.println("Login successful for user: " + userName);
                return customer;
            }
        }
        System.out.println("Invalid username or password.");
        return null;
    }
}