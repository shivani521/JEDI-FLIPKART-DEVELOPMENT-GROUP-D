package com.flipkart.client;

import com.flipkart.bean.Customer;
import com.flipkart.business.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * The `GymFlipFitApplication` class is the main entry point for the FlipFit application.
 * It provides a command-line interface for users to log in, register as either a customer
 * or a gym owner, and navigate to their respective menus.
 */
public class GymFlipFitApplication {

    private static AdminInterface adminClient = new AdminService();
    private static CustomerInterface customerClient = new CustomerService();
    private static GymOwnerInterface gymOwnerClient = new GymOwnerService();
    private static CustomerService customerService = new CustomerService();

    private static GymFlipFitAdminMenu gymFlipFitAdminMenu = new GymFlipFitAdminMenu();
    private static GymFlipFitCustomerMenu gymFlipFitCustomerMenu;
    private static GymFlipFitOwnerMenu gymFlipFitOwnerMenu = new GymFlipFitOwnerMenu();

    // Use a single Scanner instance for the whole class (avoid shadowing / resource issues)
    private static final Scanner scanner = new Scanner(System.in);

    /**
     * The main method that starts the application.
     * It displays the initial menu and handles user choices for login, registration, and exiting.
     *
     * @param args Command-line arguments.
     */
    public static void main(String[] args) {
        System.out.println("***** Welcome to FlipFit Application ******");

        while (true) {
            System.out.println("\nChoose an option :");
            System.out.println("1: Login");
            System.out.println("2: Register as Customer");
            System.out.println("3: Register as Gym Owner");
            System.out.println("4: Exit");

            String line = scanner.nextLine().trim();
            if (line.isEmpty()) {
                System.out.println("Please enter a choice.");
                continue;
            }

            int choice;
            try {
                choice = Integer.parseInt(line);
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
                continue;
            }

            switch (choice) {
                case 1:
                    login();
                    break;
                case 2:
                    registerCustomer();
                    break;
                case 3:
                    registerGymOwner();
                    break;
                case 4:
                    System.out.println("Exiting FlipFit App. Goodbye!");
                    scanner.close();
                    System.exit(0);
                    break;
                default:
                    System.out.println("Please choose a valid option (1-4).");
            }
        }
    }

    /**
     * Handles the user login process by prompting for credentials and role.
     * It validates the credentials and directs the user to the appropriate menu.
     */
    private static void login() {
        System.out.print("Enter your username: ");
        String userName = scanner.nextLine().trim();

        System.out.print("Enter your password: ");
        String password = scanner.nextLine().trim();

        System.out.println("Please choose role to login as:");
        System.out.println("1: Admin");
        System.out.println("2: Customer");
        System.out.println("3: Gym Owner");

        String line = scanner.nextLine().trim();
        int choice;
        try {
            choice = Integer.parseInt(line);
        } catch (NumberFormatException e) {
            System.out.println("Invalid choice.");
            return;
        }

        if (choice == 1) {
            boolean ok = adminClient.login(userName, password);
            if (ok) {
                gymFlipFitAdminMenu.adminMainPage();
            } else {
                System.out.println("Invalid admin credentials.");
            }
        } else if (choice == 2) {
            Customer loggedInCustomer = customerService.login(userName, password);
            if (loggedInCustomer != null) {
                gymFlipFitCustomerMenu = new GymFlipFitCustomerMenu(customerService, loggedInCustomer);
                gymFlipFitCustomerMenu.customerMainPage();
            } else {
                System.out.println("Invalid login credentials for customer.");
            }
        } else if (choice == 3) {
            boolean ok = gymOwnerClient.login(userName, password);
            if (ok) {
                gymFlipFitOwnerMenu.ownerMainPage();
            } else {
                System.out.println("Invalid login credentials for gym owner.");
            }
        } else {
            System.out.println("Please choose a valid role option (1-3).");
        }
    }

    /**
     * Handles the customer registration process.
     * It prompts for customer details and calls the business service to register the new customer.
     */
    private static void registerCustomer() {
        System.out.println("Registering as a new Customer:");
        System.out.print("Enter username: ");
        String username = scanner.nextLine().trim();
        System.out.print("Enter password: ");
        String password = scanner.nextLine().trim();
        System.out.print("Enter email: ");
        String email = scanner.nextLine().trim();
        System.out.print("Enter phone number: ");
        String phoneNo = scanner.nextLine().trim();
        System.out.print("Enter your city: ");
        String city = scanner.nextLine().trim();

        Customer newCustomer = customerService.registerCustomer(username, password, email, phoneNo, city);
        if (newCustomer != null) {
            System.out.println("Registration successful! You can now log in with your credentials.");
        } else {
            System.out.println("Registration failed. Please try again.");
        }
    }

    /**
     * Handles the gym owner registration process.
     * It prompts for gym owner details and calls the business service to register the new owner.
     */
    private static void registerGymOwner() {
        System.out.println("Registering as a new Gym Owner:");
        System.out.print("Enter username: ");
        String userName = scanner.nextLine().trim();
        System.out.print("Enter password: ");
        String password = scanner.nextLine().trim();
        System.out.print("Enter email: ");
        String email = scanner.nextLine().trim();
        System.out.print("Enter Aadhar/Account number: ");
        String adharCardNumber = scanner.nextLine().trim();

        // If you collect gym center ids during registration, read them; otherwise pass empty list.
        List<Integer> gymCenterIds = new ArrayList<>();
        System.out.print("Do you want to add gym center IDs now? (y/N): ");
        String addGyms = scanner.nextLine().trim().toLowerCase();
        if (addGyms.equals("y") || addGyms.equals("yes")) {
            System.out.print("Enter comma-separated gym center IDs: ");
            String idsLine = scanner.nextLine().trim();
            if (!idsLine.isEmpty()) {
                String[] parts = idsLine.split(",");
                for (String p : parts) {
                    try {
                        gymCenterIds.add(Integer.parseInt(p.trim()));
                    } catch (NumberFormatException ignored) {
                    }
                }
            }
        }
        System.out.println("Enter status");
        String status = scanner.nextLine().trim();
        // The register method signature expects a userId; pass 0 to indicate create new user.
        boolean success = gymOwnerClient.register(0, userName, email, password, adharCardNumber, gymCenterIds,status);
        if (success) {
            System.out.println("Gym owner registration submitted. It may need approval depending on your workflow.");
        } else {
            System.out.println("Gym owner registration failed. Please try again.");
        }
    }
}