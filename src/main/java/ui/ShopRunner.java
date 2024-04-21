package main.java.ui;

import main.java.handlers.FileHandler;
import main.java.handlers.ShopManager;
import main.java.models.Car;
import main.java.models.User;
import main.java.models.Ticket;

import java.util.HashMap;
import java.util.Scanner;
import java.util.LinkedHashMap;

public class ShopRunner {
    private static ShopManager shopManager;
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        try {
            // File paths for data
            String carFile = "resources/car_data.csv";
            String userFile = "resources/user_data.csv";
            String ticketFile = "resources/ticket_data.csv"; // Path to the ticket data file

            // Load headers and data
            LinkedHashMap<String, Integer> carHeaderIndexMap = FileHandler.fileHeaderIndex(carFile);
            LinkedHashMap<String, Integer> userHeaderIndexMap = FileHandler.fileHeaderIndex(userFile);
            LinkedHashMap<String, Integer> ticketHeaderIndexMap = FileHandler.fileHeaderIndex(ticketFile); // For tickets

            // Create maps for each type of data
            HashMap<String, User> users = FileHandler.createUserMap(userFile, userHeaderIndexMap);
            HashMap<Integer, Car> cars = FileHandler.createCarMap(carFile, carHeaderIndexMap);
            HashMap<Integer, Ticket> tickets = FileHandler.createTicketMap(ticketFile, ticketHeaderIndexMap); // For tickets

            // Initialize ShopManager with all data
            shopManager = new ShopManager(cars, users, tickets, carHeaderIndexMap, userHeaderIndexMap, ticketHeaderIndexMap);
            UserMenu userMenu = new UserMenu(shopManager, scanner);
            AdminMenu adminMenu = new AdminMenu(shopManager, scanner);

            // Welcome message
            System.out.println("****************************************************************");
            System.out.println("*                                                              *");
            System.out.println("*    ********** Welcome to the Car Dealership System! **********");
            System.out.println("*                                                              *");
            System.out.println("****************************************************************");

            // Print loaded data
            // System.out.println("Loaded Users:");
            // users.values().forEach(user -> System.out.println(user));
            // System.out.println("Loaded Cars:");
            // cars.values().forEach(car -> System.out.println(car));
            // System.out.println("Loaded Tickets:");
            // tickets.values().forEach(ticket -> System.out.println(ticket)); 
            
            // Main interaction loop
            while (true) {
                System.out.println("Please sign in as:");
                System.out.println("1. Admin");
                System.out.println("2. User");
                System.out.println("0. Exit");
                System.out.print("Enter your choice: ");

                String option = scanner.nextLine().trim();

                switch (option) {
                    case "1":
                        adminMenu.handleSelection();
                        break;
                    case "2":
                        userMenu.login();
                        break;
                    case "0":
                        System.out.println("Exiting the system...");
                        return; // Exit the loop and the program
                    default:
                        System.out.println("\n----- Invalid option selected. Please try again. -----\n");
                }
            }

        } catch (Exception e) {
            System.err.println("An unexpected error occurred: " + e.getMessage());
            e.printStackTrace();
        } finally {
            scanner.close(); // Always close the scanner when done
        }
    }
}






        // TODO: Basic outline of what the RunShop class might handle:

        // 1. Main Menu
        // Display a list of options that users can choose from, such as:

        // Sign in / Register
        // View available cars
        // Purchase a car
        // Return a car
        // View transaction history
        // 2. User Authentication
        // When a user attempts to sign in, RunShop should collect the user's credentials 
        // and validate them against the existing HashMap of users. If the credentials are correct, grant 
        // access to the user-specific options in the system.


        // ------------ OPTIONAL, idk if we will need to handle this in Part 3---------------------
        // 3. Registration
        // If a new user wants to register, collect the required information and use UserFactory to 
        // create a new User object, which then gets added to the HashMap and persisted in the user CSV file through FileHandler.
        // ----------------------------------------------------------------------------------------
        // 4. Car Inventory Management
        // Display the current stock of cars and handle the purchasing and returning process by invoking methods from ShopManager.

        // 5. Transaction Handling
        // For purchases and returns, use TicketFactory to generate tickets and ShopManager to 
        // update the stock and persist changes to the CSV files.

        // 6. Logging
        // Log significant actions, such as user sign-ins, purchases, and returns, which could 
        // either be displayed in the UI or stored in a log file for audit purposes.



        // Shows the current hashmap of Users and Cars 