package com.flipkart.client;

import com.flipkart.bean.*;
import com.flipkart.business.*;

import java.util.List;
import java.util.Scanner;

import static java.lang.System.in;

/**
 * The `GymFlipFitAdminMenu` class provides the command-line interface for the administrator.
 * It displays a menu of administrative options and handles user input to interact
 * with the business layer for managing gym owners, gyms, slots, and bookings.
 */
public class GymFlipFitAdminMenu {

    private AdminInterface adminService=new AdminService();
    private GymOwnerService gymOwnerService=new GymOwnerService();


    /**
     * Displays the main menu for the administrator and processes their choices.
     * This method provides options to view and approve/reject gym owners and gyms,
     * as well as to view bookings and manage user accounts.
     */
    public void adminMainPage() {
        System.out.println("Welcome to Admin Menu Page\n");
        System.out.println("\n------------------------------\nWelcome to FlipFit Admin Client");
        System.out.println("1. View pending registrations");
        System.out.println("2. Approve Gym Owner");
        System.out.println("3. Reject Gym Owner");
        System.out.println("4. View pending gym requests");
        System.out.println("5. View pending slot requests");
        System.out.println("6. Approve Gym");
        System.out.println("7. Reject Gym");
        System.out.println("8. Approve Slot");
        System.out.println("9. Reject Slot");
        System.out.println("10. View all customers");
        System.out.println("11. View all gymOwners");
        System.out.println("12. View bookings");
        System.out.println("13. Logout");



        Scanner sc = new Scanner(in);
        int choice = Integer.parseInt(sc.nextLine());
        switch (choice) {
            case 1:
                //List<GymOwner> pendingGymOwnerList = GymOwnerService.getPendingGymOwners();
//                showPendingGymOwners(pendingGymOwnerList);
                break;
            case 2:
                try {
                    System.out.println("Enter User ID: ");
                    int approvedUserId = Integer.parseInt(sc.nextLine());
                    adminService.approveGymOwner(approvedUserId, false);
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
                break;

            case 3:
                try {
                    System.out.println("Enter User ID: ");
                    int rejectedUserId = Integer.parseInt(sc.nextLine());
                    adminService.removeGymOwner(rejectedUserId);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }

            case 4:
                System.out.println("Pending gym requests displayed");
                List<FlipFitGym> pendingGyms = gymOwnerService.getPendingGyms();
//                showPendingGyms(pendingGyms);
                break;
            case 5:
                System.out.println("Pending slot requests displayed");
                List<Slot> pendingSlots = gymOwnerService.getPendingSlots();
//                Helper.showSlots(pendingSlots, "Pending slots");
                break;
            case 6:
                try {
                    System.out.println("Enter gymId: ");
                    int approvedGymId = Integer.parseInt(sc.nextLine());
                    adminService.approveGymOwner(approvedGymId, false);
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }

                break;
            case 7:
                try {
                    System.out.println("Enter gymId: ");
                    int rejectedGymId = Integer.parseInt(sc.nextLine());
                    adminService.removeGymOwner(rejectedGymId);
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
                break;
            case 8:
                try {
                    System.out.println("Enter slotId: ");
                    int approvedSlotId = Integer.parseInt(sc.nextLine());
                    adminService.approveSlot(approvedSlotId);
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
                break;
            case 9:
                try {
                    System.out.println("Enter slotId: ");
                    int rejectedSlotId = Integer.parseInt(sc.nextLine());
                    adminService.rejectSlot(rejectedSlotId);
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
                break;
//            case 10:
//                CustomerService customerService = new CustomerService();
//                List<Customer> customers = customerService.getAllCustomers();
//                System.out.printf("Registered Customers%n");
//                System.out.printf("| %-12s | %-15s | %-30s | %-15s |%n", "CUSTOMER ID", "USERNAME", "EMAIL-ID", "PHONE");
//                for (Customer customer : customers) {
//                    System.out.printf("| %-12s | %-15s | %-30s | %-15s |%n", customer.getUserID(), customer.getUserName(), customer.getEmailID(), customer.getPhone());
//                }
//                System.out.printf("-------------------------------------------------------------------------------------%n");
//
//                break;
//            case 11:
//                GymOwnerService gymOwnerService = new GymOwnerService();
//                List<GymOwner> gymOwners = gymOwnerService.getAllGymOwners();
//
//                System.out.printf("Gym Owners%n");
//                System.out.printf("| %-15s | %-15s | %-30s | %-20s |%n", "GYM OWNER ID", "USERNAME", "EMAIL-ID", "ACCOUNT NUMBER");
//                for (GymOwner gymOwner : gymOwners) {
//                    System.out.printf("| %-15s | %-15s | %-30s | %-20s |%n", gymOwner.getGymOwnerId(), gymOwner.getUsername(), gymOwner.getEmail(), gymOwner.getAccountNumber());
//                }
//                System.out.printf("---------------------------------------------------------------------------------------------%n");

//                break;
            case 10:
                System.out.println("List of bookings displayed");
                List<Booking> allBookings = gymOwnerService.getAllBookings();
//                showAllBookings(allBookings);
                break;
            case 11:
                System.out.println("Logged out successfully");
        }

    }

//    public void showPendingGymOwners(List<GymOwner> pendingGymOwners) {
//        System.out.printf("Pending Gym Owners%n");
//        System.out.printf("| %-10s | %-15s | %-30s | %-20s | %-15s | %-20s |%n", "USER ID", "USERNAME", "EMAIL-ID", "FULL NAME", "GYM OWNER ID", "ACCOUNT NUMBER");
//        for (GymOwner gymOwner : pendingGymOwners) {
//            System.out.printf("| %-10s | %-15s | %-30s | %-20s | %-15s | %-20s |%n", gymOwner.getUserID(), gymOwner.getUserName(), gymOwner.getEmailID(), gymOwner.getAadharCardNumber());
//        }
//    }
//
//    public void showPendingGyms(List<FlipFitGym> pendingGyms) {
//        System.out.printf("Pending Gyms%n");
//        System.out.printf("| %-10s | %-15s | %-30s | %-20s | %-20s |%n", "GYM ID", "GYM OWNER ID", "GYM NAME", "GYM CITY", "GYM AREA");
//        for (FlipFitGym gym : pendingGyms) {
//            System.out.printf("| %-10s | %-15s | %-30s | %-20s | %-20s |%n", gym.getOwnerId(), gym.getGymId(), gym.getGymName(), gym.getGymAddress(), gym.getGymStatus());
//        }
//    }
//    private void showAllBookings(List<Booking> allBookings) {
//        System.out.printf("All Bookings%n");
//        System.out.printf("| %-10s | %-15s | %-30s | %-20s | %-20s | %-20s |%n", "Customer ID", "Slot ID", "Booking Status", "Payment ID", "Booking Date", "Waitlist Rank");
//        for(Booking booking: allBookings){
//            System.out.printf("| %-10s | %-15s | %-30s | %-20s | %-20s | %-20s |%n", booking.getCustomerId(), booking.getSlotId(), booking.getBookingStatus(), booking.getPaymentId(), booking.getcreatedAt().toString());
//        }
//    }

}