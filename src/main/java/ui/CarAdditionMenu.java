package main.java.ui;

import java.util.HashMap;
import java.util.Scanner;

/**
 * A menu for collecting attributes of a car.
 */
public class CarAdditionMenu {
    private Scanner scanner = new Scanner(System.in);

    /**
     * Collects attributes of a car including car type, condition, transmission,
     * fuel type, etc.
     * 
     * @return A HashMap containing car attributes.
     */
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

    /**
     * Allows the user to choose a car type.
     * 
     * @return The chosen car type.
     */
    private String chooseCarType() {
        System.out.println("\nChoose a Car Type:");
        return getUserChoice(new String[] { "Hatchback", "Sedan", "SUV", "Pickup" });
    }

    /**
     * Allows the user to choose the condition of the car.
     * 
     * @return The chosen condition.
     */
    private String chooseCondition() {
        System.out.println("\nChoose the Condition of the car:");
        return getUserChoice(new String[] { "New", "Used" });
    }

    /**
     * Allows the user to choose the transmission type.
     * 
     * @return The chosen transmission type.
     */
    private String chooseTransmission() {
        System.out.println("\nChoose Transmission Type:");
        return getUserChoice(new String[] { "Automatic", "Manual" });
    }

    /**
     * Allows the user to choose the fuel type.
     * 
     * @return The chosen fuel type.
     */
    private String chooseFuelType() {
        System.out.println("\nChoose Fuel Type:");
        return getUserChoice(new String[] { "Hybrid", "Diesel", "Gasoline", "Electric" });
    }

    /**
     * Allows the user to choose if the car has a turbocharger.
     * 
     * @return The chosen option.
     */
    private String chooseHasTurbo() {
        System.out.println("\nDoes the car have a turbo (turbocharger)?");
        return getUserChoice(new String[] { "True", "False" });
    }

    /**
     * Helper method to get user choice from a list of options.
     * 
     * @param options The available options.
     * @return The user's choice.
     */
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

    /**
     * Helper method to get numeric input from the user.
     * 
     * @param prompt The prompt for input.
     * @return The numeric input from the user.
     */
    private String getNumericInput(String prompt) {
        System.out.print(prompt);
        String input = scanner.nextLine().trim();
        while (!input.matches("\\d+")) { // Regular expression to check if input is numeric
            System.out.println("Invalid input. Please enter a valid number.");
            System.out.print(prompt);
            input = scanner.nextLine().trim();
        }
        return input;
    }

    /**
     * Helper method to get non-empty input from the user.
     * 
     * @param prompt The prompt for input.
     * @return The non-empty input from the user.
     */
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
