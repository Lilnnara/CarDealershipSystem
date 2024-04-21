package main.java.ui;

import main.java.handlers.ShopManager;
import main.java.models.User;
import java.util.Scanner;

public class UserMenu implements Menu {
    private ShopManager shopManager;
    private Scanner scanner;
    private User currentUser; 

    public UserMenu(ShopManager shopManager, Scanner scanner) {
        this.shopManager = shopManager;
        this.scanner = scanner;
    }

    @Override
    public void login(){
        System.out.print("Username: ");
        String username = scanner.nextLine();
        System.out.print("Password: ");
        String password = scanner.nextLine();
        if(shopManager.authenticateUser(username,password) != null){
            currentUser = shopManager.authenticateUser(username,password);
            handleSelection();
        }
        else{
            currentUser = null;
            System.out.println("Invalid User Login.  Please check if Username and Password are correct and try again.");
        }
    }

    @Override
    public void displayOptions() {
        System.out.println("\n--- User Menu ---");
        System.out.println("1. Browse all cars");
        System.out.println("2. View available cars");
        System.out.println("3. Purchase a car");
        System.out.println("4. Return a car");
        System.out.println("5. View transaction history");
        System.out.println("0. Go back to main menu");
    }


    @Override
    public void handleSelection() {
    
        String input;
        do {
            displayOptions();
            System.out.print("Enter your choice: ");
            input = scanner.nextLine();
            switch (input) {
                case "1":
                    // Handle ALL CARS
                    break;
                case "2":
                    // View available cars
                    break;
                case "3":
                    // Purchase a car
                    //use currentUser to access the users finance and information
                    break;
                case "4":
                    // Return a car
                    break;
                case "5":
                    // View transaction history
                    break;
                case "0":
                System.out.println("\n----- Exiting User Menu... -----\n");
                // assign currentUser as null to sign the user out of the menu.
                currentUser = null;
                return; // Exit the loop
                default:
                    System.out.println("\n----- User: Invalid option. Please try again. -----\n");
            }
        } while (!input.equals("0"));
    }
}
