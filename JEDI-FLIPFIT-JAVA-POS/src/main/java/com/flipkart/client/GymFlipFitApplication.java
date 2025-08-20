package com.flipkart.client;

import com.flipkart.business.*;
import com.flipkart.client.*;

import java.util.*;

///Users/garvit.mukhija/Documents/JEDI-FLIPFIT-JAVA-POS/JEDI-FLIPFIT-JAVA-POS/src/main/java/com/flipkart

public class GymFlipFitApplication {


    private static AdminInterface adminClient=new AdminService();
    private static CustomerInterface customerClient=new CustomerService();
    private static GymOwnerInterface gymOwnerClient=new GymOwnerService();

    private static GymFlipFitAdminMenu gymFlipFitAdminMenu=new GymFlipFitAdminMenu();
    private static GymFlipFitCustomerMenu gymFlipFitCustomerMenu=new GymFlipFitCustomerMenu();
    private static GymFlipFitOwnerMenu gymFlipFitOwnerMenu=new GymFlipFitOwnerMenu();

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

    else if (choice == 2)
    {
        customerClient.login(userName,password);
        gymFlipFitCustomerMenu.customerMainPage();
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
    System.out.println("Register Customer");
}

private static void registerGymOwner() {
        System.out.println("Register Gym Owner");
}
}



