package com.flipkart.client;

import com.flipkart.business.GymOwnerService;
import com.flipkart.bean.FlipFitGym;
import com.flipkart.bean.GymOwner;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static java.lang.System.in;

/**
 * The `GymFlipFitOwnerMenu` class provides the command-line interface for gym owners.
 * It displays a menu of options for managing gyms and slots and handles user input
 * to perform operations through the business layer.
 */
public class GymFlipFitOwnerMenu {

    private GymOwnerService gymOwnerService = new GymOwnerService();
    private Scanner sc = new Scanner(in);

    /**
     * Displays the main menu for the gym owner and processes their choices.
     * This method provides a loop for interacting with the gym owner's functionalities,
     * such as creating/deleting gyms and slots, and viewing bookings.
     */
    public void ownerMainPage() {
        int choice;
        boolean isLoggedIn = false;

        System.out.println("Welcome to the Gym Flip Fit Owner Menu");


        // Main menu loop after successful login
        while (true) {
            displayMenu();
            choice = sc.nextInt();
            sc.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    createGym();
                    break;
                case 2:
                    deleteGym();
                    break;
                case 3:
                    // updateGym() doesn't take parameters, so it can be called directly
                    gymOwnerService.updateGym();
                    break;
                case 4:
                    createSlot();
                    break;
                case 5:
                    removeSlot();
                    break;
                case 6:
                    gymOwnerService.getPendingSlots();
                    break;
                case 7:
                    gymOwnerService.getAllBookings();
                    break;
                case 8:
                    System.out.println("Logging out...");
                    return; // Exit the loop and return to the main menu
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }



    private void registerOwner() {
        System.out.print("Enter user ID: ");
        int userId = sc.nextInt();
        sc.nextLine();
        System.out.print("Enter username: ");
        String userName = sc.nextLine();
        System.out.print("Enter email: ");
        String email = sc.nextLine();
        System.out.print("Enter password: ");
        String password = sc.nextLine();
        System.out.print("Enter Aadhaar card number: ");
        String adharCardNumber = sc.nextLine();
        System.out.println("Enter status");
        String status = sc.nextLine();
        List<Integer> gymCenterIds = new ArrayList<>();
        System.out.println("Enter gym center IDs (enter 0 to finish):");
        int gymCenterId;
        while ((gymCenterId = sc.nextInt()) != 0) {
            gymCenterIds.add(gymCenterId);
        }

        gymOwnerService.register(userId, userName, email, password, adharCardNumber, gymCenterIds,status);
    }

    private void displayMenu() {
        System.out.println("\n--- Gym Owner Main Menu ---");
        System.out.println("1. Create Gym");
        System.out.println("2. Delete Gym");
        System.out.println("3. Update Gym");
        System.out.println("4. Create Slot");
        System.out.println("5. Remove Slot");
        System.out.println("6. Get Pending Slots");
        System.out.println("7. Get All Bookings");
        System.out.println("8. Logout");
        System.out.print("Enter your choice: ");
    }

    private void createGym() {
        System.out.print("Enter gym ID: ");
        int gymId = sc.nextInt();
        sc.nextLine();
        System.out.print("Enter gym name: ");
        String gymName = sc.nextLine();
        System.out.print("Enter owner ID: ");
        int ownerId = sc.nextInt();
        sc.nextLine();
        System.out.print("Enter owner name: ");
        String ownerName = sc.nextLine();
        System.out.print("Enter gym address: ");
        String gymAddress = sc.nextLine();
        System.out.print("Enter number of slots: ");
        int numberOfSlots = sc.nextInt();
        sc.nextLine();
        System.out.print("Enter cost: ");
        int cost = sc.nextInt();
        sc.nextLine();
        System.out.print("Enter gym status: ");
        int gymStatus = sc.nextInt();
        sc.nextLine();
        System.out.print("Enter owner GST number: ");
        String ownerGstNumber = sc.nextLine();

        gymOwnerService.createGym(gymId, gymName, ownerId, ownerName, gymAddress, numberOfSlots, cost, gymStatus, ownerGstNumber);
    }

    private void deleteGym() {
        System.out.print("Enter gym ID to delete: ");
        int gymId = sc.nextInt();
        sc.nextLine();
        gymOwnerService.deleteGym(gymId);
    }

    private void createSlot() {
        System.out.print("Enter slot ID: ");
        int slotId = sc.nextInt();
        System.out.print("Enter gym ID: ");
        int gymId = sc.nextInt();
        sc.nextLine();
        System.out.print("Enter slot date (YYYY-MM-DD): ");
        LocalDate slotDate = LocalDate.parse(sc.nextLine());
        System.out.print("Enter slot time (HH:MM): ");
        LocalTime slotTime = LocalTime.parse(sc.nextLine());

        gymOwnerService.createSlot(slotId, gymId, slotDate, slotTime);
    }

    private void removeSlot() {
        System.out.print("Enter slot ID to remove: ");
        int slotId = sc.nextInt();
        sc.nextLine();
        gymOwnerService.removeSlot(slotId);
    }
}