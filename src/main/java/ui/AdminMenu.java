package main.java.ui;

import main.java.handlers.ShopManager;
import java.util.Scanner;
import main.java.utils.Log;

/**
 * This class represents the administration menu for managing the car dealership system.
 * It provides options for adding or removing cars, managing users, and retrieving revenue information.
 */
public class AdminMenu implements Menu {
    private ShopManager shopManager;
    private Scanner scanner;

    /**
     * Constructs an AdminMenu with the specified ShopManager and Scanner.
     *
     * @param shopManager the manager handling business logic and data interaction
     * @param scanner the scanner to read input from the console
     */
    public AdminMenu(ShopManager shopManager, Scanner scanner) {
        this.shopManager = shopManager;
        this.scanner = scanner;
    }

    /**
     * Logs in the admin user by requesting an admin code and authenticating it.
     */
    @Override
    public void login() {
        System.out.print("Hello Admin.\nPlease enter Admin code: ");
        String code = scanner.nextLine();
        if (shopManager.authenticateAdmin(code)) {
            shopManager.logsLinkedList.add(new Log("Admin ","Logged in."));
            handleSelection();
        } else {
            System.out.println("Invalid Admin Login. Please check if the password is correct and try again.");
        }
    }

    /**
     * Displays the main menu options to the admin user.
     */
    @Override
    public void displayOptions() {
        System.out.println("\n--- Admin Menu ---");
        System.out.println("1. Add a new car");
        System.out.println("2. Remove a car");
        System.out.println("3. Retrieve revenue by car ID");
        System.out.println("4. Retrieve revenue by car type");
        System.out.println("5. Manage users");
        System.out.println("0. Go back to main menu");
    }

    /**
     * Handles the admin user's menu selection.
     * This method processes input from the admin and calls the corresponding methods in ShopManager.
     */
    @Override
    public void handleSelection() {
        String input;
        do {
            displayOptions();
            System.out.print("Enter your choice: ");
            input = scanner.nextLine();
            switch (input) {
                case "1":
                    shopManager.addCar();
                    break;
                case "2":
                    System.out.print("To delete a car, please enter the car ID: ");
                    String id = scanner.nextLine();
                    shopManager.removeCar(id);
                    break;
                case "3":
                    System.out.print("Enter the car ID to retrieve revenue: ");
                    String carId = scanner.nextLine();
                    shopManager.getRevenueById(carId);
                    break;
                case "4":
                    System.out.print("Enter the car type to retrieve revenue: ");
                    String carType = scanner.nextLine();
                    shopManager.getRevenueByCarType(carType);
                    break;
                case "5":
                    shopManager.addNewUser();
                    break;
                case "0":
                    shopManager.logsLinkedList.add(new Log("Admin ","Logged out."));
                    System.out.println("\n----- Exiting Admin Menu... -----\n");
                    return; // Exit the loop
                default:
                    System.out.println("\n----- Admin: Invalid option, please try again. -----\n");
            }
        } while (!input.equals("0"));
    }
}
