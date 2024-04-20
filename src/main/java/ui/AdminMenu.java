package main.java.ui;

import main.java.handlers.ShopManager;
import java.util.Scanner;

import main.java.models.Car;
import java.util.HashMap;

import main.java.models.User;

public class AdminMenu implements Menu {
    private ShopManager shopManager;
    private Scanner scanner;
    private HashMap<Integer, Car> cars;
    private HashMap<String, User> users;
    private String carFile;
    private String userFile;

    public AdminMenu(ShopManager shopManager, Scanner scanner) {
        this.shopManager = shopManager;
        this.scanner = scanner;
    }

    @Override
    public void displayOptions() {
        System.out.println("\n--- Admin Menu ---");
        System.out.println("1. Add a new car");
        System.out.println("2. Remove a car");
        System.out.println("3. Update car information");
        System.out.println("4. View all transactions");
        System.out.println("5. Manage users");
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
                    System.out.println("add car");
                    cars = shopManager.addCar();
                    break;
                case "2":
                    System.out.println("To delete a car, please enter the car ID: ");
                    int id = scanner.nextInt();
                    shopManager.removeCar(id);
                    break;
                case "3":
                    // shopManager.updateCarInfo();
                    break;
                case "4":
                    // shopManager.viewTransactions();
                    break;
                case "5":
                    users = shopManager.addNewUser();
                    // shopManager.manageUsers();
                    break;
                case "0":
                    System.out.println("\n----- Exiting Admin Menu... -----\n");
                    // FileHandler.updateCarFile(carFile, cars, carHeaderIndexMap); 
                    return; // Exit the loop
                default:
                    System.out.println("\n----- Admin: Invalid option, please try again. -----\n");
            }
        } while (!input.equals("0"));
    }
}
