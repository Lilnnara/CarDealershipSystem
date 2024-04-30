package main.java.ui;
import main.java.models.User;

import java.util.HashMap;
import java.util.Scanner;
import java.util.LinkedHashMap;

public class UserAdditionMenu {
    private Scanner scanner = new Scanner(System.in);
    private HashMap<String, User> existingUsers;

    public UserAdditionMenu(HashMap<String, User> existingUsers) {
        this.existingUsers = existingUsers; // Use this to check for existing usernames
    }

    // Collect user attributes, now checking against existingUsers
    public HashMap<String, String> collectUserAttributes(LinkedHashMap<String, Integer> userHeaderIndexMap) {
        HashMap<String, String> userAttributes = new HashMap<>();
        System.out.println("\nPlease enter the following information for the new user:");

        for (String key : userHeaderIndexMap.keySet()) {
            switch (key) {
                case "Username":
                    String username = getUniqueUsername("\nEnter Username: ");
                    userAttributes.put(key, username);
                    break;
                case "Password":
                    userAttributes.put(key, getRequiredInput("\nEnter Password (min 6 characters): ", 6));
                    break;
                case "First Name":
                case "Last Name":
                    userAttributes.put(key, getStringInput("\nEnter " + key + " (no numbers): "));
                    break;
                case "Money Available":
                    userAttributes.put(key, getNumericInput("\nEnter Money Available: "));
                    break;
                case "Cars Purchased":
                    userAttributes.put(key, getNumericInput("\nEnter Cars Purchased: "));
                    break;
                case "MinerCars Membership":
                    userAttributes.put(key, chooseMembership());
                    break;
            }
        }
        return userAttributes;
    }

    private String getUniqueUsername(String prompt) {
        System.out.print(prompt);
        String username = scanner.nextLine().trim();
        while (existingUsers.containsKey(username)) {
            System.out.println("This username is already taken. Please choose another one.");
            System.out.print(prompt);
            username = scanner.nextLine().trim();
        }
        return username;
    }



    // Helper method for required inputs
    private String getRequiredInput(String prompt, int minLength) {
        System.out.print(prompt);
        String input = scanner.nextLine().trim();
        while (input.length() < minLength) {
            System.out.println("Input is too short. Must be at least " + minLength + " characters.");
            System.out.print(prompt);
            input = scanner.nextLine().trim();
        }
        return input;
    }


    // Helper method for numeric inputs
    private String getNumericInput(String prompt) {
        System.out.print(prompt);
        String input = scanner.nextLine().trim();
        // This regular expression matches optional negative signs and decimal numbers
        while (!input.matches("-?\\d+(\\.\\d+)?")) {
            System.out.println("Invalid input. Please enter a valid number.");
            System.out.print(prompt);
            input = scanner.nextLine().trim();
        }
        return input;
    }
    

    // Helper method for boolean inputs via menu selection
    private String chooseMembership() {
        System.out.println("\nEnter MinerCars Membership:");
        return getUserChoice(new String[]{"True", "False"});
    }

    // Helper method for name inputs with validation for non-empty and no numbers
    private String getStringInput(String prompt) {
        System.out.print(prompt);
        String input = scanner.nextLine().trim();
        while (input.isEmpty() || input.matches(".*\\d.*")) {
            if (input.isEmpty()) {
                System.out.println("Input cannot be empty. Please enter a valid value.");
            } else {
                System.out.println("Invalid input. Names cannot contain numbers. Please re-enter.");
            }
            System.out.print(prompt);
            input = scanner.nextLine().trim();
        }
        return input;
    }

    // Generic choice method used for boolean fields
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
}
