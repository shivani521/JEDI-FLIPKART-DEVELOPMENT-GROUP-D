package com.flipkart.client;

import com.flipkart.business.CustomerService;
import com.flipkart.bean.Customer;
import com.flipkart.bean.FlipFitGym;
import com.flipkart.bean.Slot;
import com.flipkart.bean.Booking;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

/**
 * The `GymFlipFitCustomerMenu` class provides the command-line interface for customer-specific operations.
 * It allows customers to view gyms, check for available slots, make and cancel bookings,
 * and view their existing bookings.
 */
public class GymFlipFitCustomerMenu {

    private CustomerService customerService;
    private Customer currentCustomer;

    /**
     * Constructs a new `GymFlipFitCustomerMenu` with a customer service and the currently logged-in customer.
     *
     * @param customerService The service layer for customer-related business logic.
     * @param customer The `Customer` object representing the logged-in user.
     */
    public GymFlipFitCustomerMenu(CustomerService customerService, Customer customer) {
        this.customerService = customerService;
        this.currentCustomer = customer;
    }

    /**
     * Displays the main customer menu and handles user input.
     * This method presents various options to the customer and invokes the
     * corresponding service methods based on their choice.
     */
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
                        customerService.viewAllGymCenters(city)
                                .forEach(gym -> System.out.println("Gym ID: " + gym.getGymId() + ", Name: " + gym.getGymName()));
                        break;
                    case 2:
                        try {
                            System.out.print("Enter Gym ID: ");
                            String gymId = scanner.nextLine();
                            System.out.print("Enter date (YYYY-MM-DD): ");
                            LocalDate date = LocalDate.parse(scanner.nextLine());
                            customerService.viewAllFreeSlots(gymId, date)
                                    .forEach(slot -> System.out.println(
                                            "Slot ID: " + slot.getSlotId() +
                                                    ", Start Time: " + slot.getStartTime() +
                                                    ", End Time: " + slot.getEndTime()
                                    ));
                        } catch (DateTimeParseException e) {
                            System.out.println("Invalid date format. Please use YYYY-MM-DD.");
                        }
                        break;
                    case 3:
                        try {
                            System.out.print("Enter Gym ID: ");
                            String bookGymId = scanner.nextLine();
                            System.out.print("Enter Slot ID: ");
                            String slotId = scanner.nextLine();
                            System.out.print("Enter Date (YYYY-MM-DD): ");
                            LocalDate bookDate = LocalDate.parse(scanner.nextLine());
                            System.out.print("Enter Start Time (HH:mm): ");
                            LocalTime bookTime = LocalTime.parse(scanner.nextLine());

                            boolean booked = customerService.bookSlot(currentCustomer.getUserId(), bookGymId, slotId, bookDate, bookTime);
                            if (booked) {
                                System.out.println("Slot booked successfully!");
                            } else {
                                System.out.println("Booking failed.");
                            }
                        } catch (DateTimeParseException e) {
                            System.out.println("Invalid date or time format. Please use YYYY-MM-DD for date and HH:mm for time.");
                        }
                        break;
                    case 4:
                        customerService.viewAllBookings(String.valueOf(currentCustomer.getUserId()))
                                .forEach(booking -> System.out.println("Booking ID: " + booking.getBookingId() + ", User ID: " + booking.getCustomerId()));
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

        // Commented out to avoid closing System.in if used elsewhere in the application
        // scanner.close();
    }
}