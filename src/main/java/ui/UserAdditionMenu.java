package main.java.ui;

import main.java.models.User;
import java.util.HashMap;
import java.util.Scanner;
import java.util.LinkedHashMap;

/**
 * Handles the interactive addition of new users through the console interface.
 * Validates user input to ensure the creation of valid User objects.
 */
public class UserAdditionMenu {
    private Scanner scanner = new Scanner(System.in);
    private HashMap<String, User> existingUsers;

    /**
     * Constructs a UserAdditionMenu with a reference to existing users to prevent duplicate usernames.
     * @param existingUsers A HashMap containing existing users where the key is the username.
     */
    public UserAdditionMenu(HashMap<String, User> existingUsers) {
        this.existingUsers = existingUsers;
    }

    /**
     * Collects user attributes from input, validating and ensuring data integrity before user creation.
     * @param userHeaderIndexMap A LinkedHashMap defining the order and required fields for user input.
     * @return A HashMap containing attribute names and their corresponding user-provided values.
     */
    public HashMap<String, String> collectUserAttributes(LinkedHashMap<String, Integer> userHeaderIndexMap) {
        HashMap<String, String> userAttributes = new HashMap<>();
        System.out.println("\nPlease enter the following information for the new user:");

        for (String key : userHeaderIndexMap.keySet()) {
            switch (key) {
                case "Username":
                    userAttributes.put(key, getUniqueUsername("\nEnter Username: "));
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

    /**
     * Ensures the uniqueness of a username, prompting the user until a non-duplicate username is entered.
     * @param prompt The input prompt displayed to the user.
     * @return A unique username.
     */
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

    /**
     * Prompts for user input and ensures it meets a specified minimum length.
     * @param prompt The input prompt.
     * @param minLength The minimum length required for the input.
     * @return A string that meets the required length.
     */
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

    /**
     * Prompts for and validates numeric input, ensuring it is a valid number (integer or decimal).
     * @param prompt The input prompt.
     * @return A string representing a valid numeric input.
     */
    private String getNumericInput(String prompt) {
        System.out.print(prompt);
        String input = scanner.nextLine().trim();
        while (!input.matches("-?\\d+(\\.\\d+)?")) {
            System.out.println("Invalid input. Please enter a valid number.");
            System.out.print(prompt);
            input = scanner.nextLine().trim();
        }
        return input;
    }

    /**
     * Handles input for boolean values by providing a selection menu.
     * @return A string representation of the user's choice ("True" or "False").
     */
    private String chooseMembership() {
        System.out.println("\nEnter MinerCars Membership:");
        return getUserChoice(new String[]{"True", "False"});
    }

    /**
     * Validates name input, ensuring it is not empty and does not contain numbers.
     * @param prompt The input prompt.
     * @return A valid name string.
     */
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

    /**
     * Provides a user choice menu for selecting between predefined options.
     * @param options An array of strings representing the available choices.
     * @return The selected option.
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
}
