package main.java.ui;

import main.java.handlers.ShopManager;
import java.util.Scanner;

public class AdminMenu implements Menu {
    private ShopManager shopManager;
    private Scanner scanner;

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
                    // shopManager.addCar();
                    break;
                case "2":
                    // shopManager.removeCar();
                    break;
                case "3":
                    // shopManager.updateCarInfo();
                    break;
                case "4":
                    // shopManager.viewTransactions();
                    break;
                case "5":
                    // shopManager.manageUsers();
                    break;
                case "0":
                    System.out.println("\n----- Exiting Admin Menu... -----\n");
                    return; // Exit the loop
                default:
                    System.out.println("\n----- Admin: Invalid option, please try again. -----\n");
            }
        } while (!input.equals("0"));
    }
}
