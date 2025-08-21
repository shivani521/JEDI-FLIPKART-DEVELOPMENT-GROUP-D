package com.flipkart.client;

import com.flipkart.business.CustomerService;
import com.flipkart.bean.Customer;
import com.flipkart.bean.FlipFitGym;
import com.flipkart.bean.Slot; // Added import for Slot
import com.flipkart.bean.Booking; // Added import for Booking

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Scanner;

public class GymFlipFitCustomerMenu {

    private CustomerService customerService;
    private Customer currentCustomer;

    public GymFlipFitCustomerMenu(CustomerService customerService, Customer customer) {
        this.customerService = customerService;
        this.currentCustomer = customer;
    }

    public void customerMainPage() {
        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            System.out.println("\n--- Gym Flip Fit Customer Menu ---");
            System.out.println("Welcome !");
            System.out.println("1. View available gyms");
            System.out.println("2. View available slots");
            System.out.println("3. Book a slot");
            System.out.println("4. View my bookings");
            System.out.println("5. Cancel a booking");
            System.out.println("6. Logout");
            System.out.print("Enter your choice: ");

            try {
                choice = scanner.nextInt();
                scanner.nextLine(); // Consume newline

                switch (choice) {
                    case 1:
                        System.out.print("Enter city to view gyms: ");
                        String city = scanner.nextLine();
                        customerService.viewAllGymCenters(city).forEach(gym -> System.out.println("Gym ID: " + gym.getGymId() + ", Name: " + gym.getGymName()));
                        break;
                    case 2:
                        System.out.print("Enter Gym ID: ");
                        String gymId = scanner.nextLine();
                        System.out.print("Enter date (YYYY-MM-DD): ");
                        LocalDate date = LocalDate.parse(scanner.nextLine());
                        // Corrected the method call from getStartTime() to getSlotTime()
                        customerService.viewAllFreeSlots(gymId, date).forEach(slot -> System.out.println("Slot ID: " + slot.getSlotId() + ", Time: " + slot.getSlotTime()));
                        break;
                    case 3:
                        System.out.print("Enter Gym ID: ");
                        String bookGymId = scanner.nextLine();
                        System.out.print("Enter Slot ID: ");
                        String slotId = scanner.nextLine();
                        System.out.print("Enter Date (YYYY-MM-DD): ");
                        LocalDate bookDate = LocalDate.parse(scanner.nextLine());
                        System.out.print("Enter Start Time (HH:mm): ");
                        LocalTime bookTime = LocalTime.parse(scanner.nextLine());

                        boolean booked = customerService.bookSlot(currentCustomer.getUserID(), bookGymId, slotId, bookDate, bookTime);
                        if (booked) {
                            System.out.println("Slot booked successfully!");
                        } else {
                            System.out.println("Booking failed.");
                        }
                        break;
                    case 4:
                        customerService.viewAllBookings(String.valueOf(currentCustomer.getUserID())).forEach(booking -> System.out.println("Booking ID: " + booking.getBookingID() + ", User ID: " + booking.getUserID()));
                        break;
                    case 5:
                        System.out.print("Enter Booking ID to cancel: ");
                        String cancelBookingId = scanner.nextLine();
                        boolean cancelled = customerService.cancelSlot(cancelBookingId);
                        if (cancelled) {
                            System.out.println("Booking cancelled successfully.");
                        } else {
                            System.out.println("Cancellation failed.");
                        }
                        break;
                    case 6:
                        System.out.println("Logging out...");
                        currentCustomer = null;
                        break;
                    default:
                        System.out.println("Invalid choice. Please try again.");
                }
            } catch (Exception e) {
                System.out.println("Invalid input. Please enter a number.");
                scanner.nextLine(); // Clear the invalid input
                choice = 0; // Ensure loop continues
            }
        } while (choice != 6);
        scanner.close(); // It's good practice to close the scanner.
    }
}