package main.java.ui;

import main.java.handlers.FileHandler;
import main.java.handlers.ShopManager;
import main.java.models.Car;
import main.java.models.User;
import main.java.models.Ticket;
import main.java.utils.Log;

import java.util.HashMap;
import java.util.Scanner;
import java.util.LinkedHashMap;

/**
 * This class holds the Shop Runner for the project.
 */
public class ShopRunner {
    private static ShopManager shopManager;
    private static Scanner scanner = new Scanner(System.in);

    
    /** 
     * Main method for whole system.
     * @param args
     */
    public static void main(String[] args) {
        try {
            new Log("","Shop initialized."); // Initial Log value created for the session.
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
                        adminMenu.login();
                        break;
                    case "2":
                        userMenu.login();
                        break;
                    case "0":
                        System.out.println("Exiting the system...");
                        //Update files changed while shop is running
                        FileHandler.updateTicketFile("resources/ticket_data.csv", tickets, ticketHeaderIndexMap);
                        FileHandler.updateUserFile("resources/user_data.csv", users, userHeaderIndexMap);
                        FileHandler.updateCarFile("resources/car_data.csv", cars, carHeaderIndexMap);
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
            new Log("","Shop closed."); // Final Log value created for the session.
            FileHandler.updateLogFile("resources/Log.txt", "\n"); // create a new line to divide log file for different sessions
        }
    }
}

