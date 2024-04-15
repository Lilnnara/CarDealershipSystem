package main.java.ui;

import main.java.handlers.FileHandler;
import main.java.models.Car;
import main.java.models.User;

import java.util.HashMap;

import java.io.IOException;


public class ShopRunner {

    public static void main(String[] args) {

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
        // ------------------------------------------------------------------
        try {
            System.out.println("Working Directory = " + System.getProperty("user.dir"));

            String usersFilename = "resources/user_data.csv";
            String carsFilename = "resources/car_data.csv";

            // Load the user and car data into maps
            HashMap<Integer, User> userMap = FileHandler.createUserMap(usersFilename);
            HashMap<Integer, Car> carMap = FileHandler.createCarMap(carsFilename);

            // Print out the loaded users and cars
            System.out.println("Loaded Users:");
            userMap.values().forEach(System.out::println);

            System.out.println("\nLoaded Cars:");
            carMap.values().forEach(System.out::println);

        } catch (IOException e) {
            System.err.println("Error loading data: " + e.getMessage());
            e.printStackTrace();
        } catch (NumberFormatException e) {
            System.err.println("Number format exception: " + e.getMessage());
            e.printStackTrace();
        } catch (Exception e) {
            System.err.println("An unexpected error occurred: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
