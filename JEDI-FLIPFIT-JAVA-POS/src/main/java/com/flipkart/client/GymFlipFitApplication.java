package com.flipkart.client;

import com.flipkart.bean.Customer;
import com.flipkart.business.*;
import com.flipkart.client.*;

import java.util.*;

///Users/garvit.mukhija/Documents/JEDI-FLIPFIT-JAVA-POS/JEDI-FLIPFIT-JAVA-POS/src/main/java/com/flipkart

public class GymFlipFitApplication {


    private static AdminInterface adminClient=new AdminService();
    private static CustomerInterface customerClient=new CustomerService();
    private static GymOwnerInterface gymOwnerClient=new GymOwnerService();
    private static CustomerService customerService = new CustomerService();

    private static GymFlipFitAdminMenu gymFlipFitAdminMenu=new GymFlipFitAdminMenu();
    private static GymFlipFitCustomerMenu gymFlipFitCustomerMenu;
    private static GymFlipFitOwnerMenu gymFlipFitOwnerMenu=new GymFlipFitOwnerMenu();

    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args)
    {
        System.out.println("*****Welcome to FLipFit Application******\n");
        System.out.println("Choose a option : \n1: Enter to login\n2:Enter to register as Customer\n3:Enter to register as Gym " +
                "Owner\n4: Exit");

        Scanner scanner = new Scanner(System.in);
        int choice = scanner.nextInt();

        switch(choice) {
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
                System.exit(0);
                break;
            default:
                System.out.println("Please choose a valid option");

    }


}

private static void login() {
    System.out.println("Enter your UserName");
    Scanner scanner = new Scanner(System.in);
    String userName = scanner.next();

    System.out.println("Enter your Passkey");
    String password = scanner.next();

    System.out.println("Please Choose : \n1: Enter 1 to login as Admin\n2: Enter 2 to login as Customer\n3: " +
            "Enter 3 to login as GymOwner");
    scanner = new Scanner(System.in);
    int choice = scanner.nextInt();


    if (choice == 1)
    {
        adminClient.login(userName, password);
        gymFlipFitAdminMenu.adminMainPage();
    }

    else if (choice == 2) {
        Customer loggedInCustomer = customerService.login(userName, password);
        if (loggedInCustomer != null) {
            gymFlipFitCustomerMenu = new GymFlipFitCustomerMenu(customerService, loggedInCustomer);
            gymFlipFitCustomerMenu.customerMainPage();
        } else {
            System.out.println("Invalid login credentials for customer.");
        }
    }

    else if (choice == 3)
    {
        gymOwnerClient.login(userName,password);
        gymFlipFitOwnerMenu.OwnerMainPage();
    }

    else
    {
        System.out.println("Please choose a valid option");
    }
}

    private static void registerCustomer() {
        System.out.println("Registering as a new Customer:");
        System.out.print("Enter username: ");
        String username = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();
        System.out.print("Enter email: ");
        String email = scanner.nextLine();
        System.out.print("Enter phone number: ");
        String phoneNo = scanner.nextLine();
        System.out.print("Enter your city: ");
        String city = scanner.nextLine();

        Customer newCustomer = customerService.registerCustomer(username, password, email, phoneNo, city);
        if (newCustomer != null) {
            System.out.println("Registration successful! You can now log in with your credentials.");
        } else {
            System.out.println("Registration failed. Please try again.");
        }
    }

private static void registerGymOwner() {
        System.out.println("Register Gym Owner");
}
}



