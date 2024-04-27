package main.java.ui;

import java.util.HashMap;
import java.util.Scanner;

public class CarAdditionMenu {
    private Scanner scanner = new Scanner(System.in);

    public HashMap<String, String> collectCarAttributes() {
        HashMap<String, String> carAttributes = new HashMap<>();

        // Menu-driven attributes
        carAttributes.put("Car Type", chooseCarType());
        carAttributes.put("Condition", chooseCondition());
        carAttributes.put("Transmission", chooseTransmission());
        carAttributes.put("Fuel Type", chooseFuelType());
        carAttributes.put("hasTurbo", chooseHasTurbo());  // Using menu for hasTurbo

        // Direct input attributes
        System.out.print("\nEnter Capacity: ");
        carAttributes.put("Capacity", scanner.nextLine().trim());

        System.out.print("\nEnter Cars Available: ");
        carAttributes.put("Cars Available", scanner.nextLine().trim());

        System.out.print("\nEnter Color: ");
        carAttributes.put("Color", scanner.nextLine().trim());

        System.out.print("\nEnter Year: ");
        carAttributes.put("Year", scanner.nextLine().trim());

        System.out.print("\nEnter Price: ");
        carAttributes.put("Price", scanner.nextLine().trim());

        System.out.print("\nEnter VIN: ");
        carAttributes.put("VIN", scanner.nextLine().trim());

        System.out.print("\nEnter Model: ");
        carAttributes.put("Model", scanner.nextLine().trim());

        return carAttributes;
    }

    private String chooseCarType() {
        System.out.println("\nChoose a Car Type:");
        return getUserChoice(new String[]{"Hatchback", "Sedan", "SUV", "Pickup"});
    }

    private String chooseCondition() {
        System.out.println("\nChoose the Condition of the car:");
        return getUserChoice(new String[]{"New", "Used"});
    }

    private String chooseTransmission() {
        System.out.println("\nChoose Transmission Type:");
        return getUserChoice(new String[]{"Automatic", "Manual"});
    }

    private String chooseFuelType() {
        System.out.println("\nChoose Fuel Type:");
        return getUserChoice(new String[]{"Hybrid", "Diesel", "Gasoline", "Electric"});
    }

    private String chooseHasTurbo() {
        System.out.println("\nDoes the car have a turbo (turbocharger)?");
        return getUserChoice(new String[]{"True", "False"});
    }

    private String getUserChoice(String[] options) {
        for (int i = 0; i < options.length; i++) {
            System.out.println((i + 1) + ". " + options[i]);
        }
        System.out.print("Enter your choice: ");
        String input = scanner.nextLine();
        try {
            int choice = Integer.parseInt(input) - 1;
            return options[Math.max(0, Math.min(choice, options.length - 1))];
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please enter a valid number.");
            return getUserChoice(options); // Recursively call itself to get a valid input
        }
    }
}
