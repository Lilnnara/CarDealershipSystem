package main.java.ui;

import java.util.HashMap;
import java.util.Scanner;

public class CarAdditionMenu {
    private Scanner scanner = new Scanner(System.in);



    // For general inputs (including color, VIN, and model) to ensure non-empty
    public HashMap<String, String> collectCarAttributes() {
        HashMap<String, String> carAttributes = new HashMap<>();

        // Menu-driven attributes
        carAttributes.put("Car Type", chooseCarType());
        carAttributes.put("Condition", chooseCondition());
        carAttributes.put("Transmission", chooseTransmission());
        carAttributes.put("Fuel Type", chooseFuelType());
        carAttributes.put("hasTurbo", chooseHasTurbo());

        // Direct input attributes with numeric validation
        carAttributes.put("Capacity", getNumericInput("\nEnter Capacity: "));
        carAttributes.put("Cars Available", getNumericInput("\nEnter Cars Available: "));
        carAttributes.put("Year", getNumericInput("\nEnter Year: "));
        carAttributes.put("Price", getNumericInput("\nEnter Price: "));

        // Direct input attributes with non-empty validation
        carAttributes.put("Color", getRequiredInput("\nEnter Color: "));
        carAttributes.put("VIN", getRequiredInput("\nEnter VIN: "));
        carAttributes.put("Model", getRequiredInput("\nEnter Model: "));

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
            if (choice >= 0 && choice < options.length) {
                return options[choice];
            } else {
                System.out.println("\nInvalid input. Please enter a number between 1 and " + options.length + ".");
            }
        } catch (NumberFormatException e) {
            System.out.println("\nInvalid input. Please enter a valid number.");
        }
        return getUserChoice(options); // Recursively call itself to get a valid input
    }
    private String getNumericInput(String prompt) {
        System.out.print(prompt);
        String input = scanner.nextLine().trim();
        while (!input.matches("\\d+")) {  // Regular expression to check if input is numeric
            System.out.println("Invalid input. Please enter a valid number.");
            System.out.print(prompt);
            input = scanner.nextLine().trim();
        }
        return input;
    }
    private String getRequiredInput(String prompt) {
        System.out.print(prompt);
        String input = scanner.nextLine().trim();
        while (input.isEmpty()) {
            System.out.println("Input cannot be empty. Please enter a valid value.");
            System.out.print(prompt);
            input = scanner.nextLine().trim();
        }
        return input;
    }
    
    
    
}
