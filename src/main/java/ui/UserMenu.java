package main.java.ui;

import main.java.handlers.ShopManager;
import main.java.models.User;
import java.util.Scanner;
import main.java.utils.Log;

/**
 * This class represents the user menu for navigating the car dealership system.
 */
public class UserMenu implements Menu {
    private ShopManager shopManager;
    private Scanner scanner;
    private User currentUser; 

    /**
     * Constructs a UserMenu with the specified ShopManager and Scanner.
     *
     * @param shopManager the manager handling business logic and data interaction
     * @param scanner the scanner to read input from the console
     */

    public UserMenu(ShopManager shopManager, Scanner scanner) {
        this.shopManager = shopManager;
        this.scanner = scanner;
    }

    /**
     * Logs in the user by requesting username and password and authenticating it.
     */
    @Override
    public void login(){
        System.out.print("Username: ");
        String username = scanner.nextLine();
        System.out.print("Password: ");
        String password = scanner.nextLine();
        if(shopManager.authenticateUser(username,password) != null){
            currentUser = shopManager.authenticateUser(username,password);
            new Log("User " + currentUser.getUsername() + " ","Logged in.");
            handleSelection();
        }
        else{
            currentUser = null;
            System.out.println("Invalid User Login.  Please check if Username and Password are correct and try again.");
        }
    }

    /**
     * Displays the main menu options to the user.
     */
    @Override
    public void displayOptions() {
        System.out.println("\n--- User Menu ---");
        System.out.println("1. Browse all cars");
        System.out.println("2. Filter cars");
        System.out.println("3. Purchase a car");
        System.out.println("4. Return a car");
        System.out.println("5. View transaction history");
        System.out.println("0. Go back to main menu");
    }

    /**
     * Handles the user's menu selection.
     * This method processes input from the user and calls the corresponding methods in ShopManager.
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
                    new Log("User " + currentUser.getUsername() + " ","Browsed Cars.");
                    shopManager.browseCars();
                    break;
                case "2":
                    new Log("User " + currentUser.getUsername() + " ","Filtered Cars.");
                    filterCarsMenu(); 
                    break;
                case "3":
                    // Purchase a car
                    //use currentUser to access the users finance and information
                    shopManager.purchaseCarUi(currentUser);
                    break;
                case "4":
                    // Return a car
                    shopManager.returnCarUi(currentUser);
                    break;
                case "5":
                    if (currentUser != null) {
                        shopManager.viewUserTransactions(currentUser.getUsername());
                    } else {
                        System.out.println("You need to be logged in to view transaction history.");
                    }
                    break;
                case "0":
                    new Log("User " + currentUser.getUsername() + " ","Logged out.");
                    System.out.println("\n----- Exiting User Menu... -----\n");
                    // assign currentUser as null to sign the user out of the menu.
                    currentUser = null;
                    return; // Exit the loop
                default:
                    System.out.println("\n----- User: Invalid option. Please try again. -----\n");
            }
        } while (!input.equals("0"));
    }

    /**
     * Method to print and handle options to filter the cars
     */
    private void filterCarsMenu() {
        String choice;
        do {
            System.out.println("\n--- Filter Cars ---");
            System.out.println("1. New Cars");
            System.out.println("2. Used Cars");
            System.out.println("3. Available Cars");
            System.out.println("0. Go back");
            System.out.print("Enter your choice: ");
            choice = scanner.nextLine();
            switch (choice) {
                case "1":
                    shopManager.filterCars("New");
                    break;
                case "2":
                    shopManager.filterCars("Used");
                    break;
                case "3":
                    shopManager.filterCars("Available");
                    break;
                case "0":
                    System.out.println("Returning to main menu...");
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        } while (!choice.equals("0"));
    }
    
}
