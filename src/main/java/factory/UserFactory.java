package main.java.factory;

import main.java.models.User;
import java.util.HashMap;

public class UserFactory {

    /**
     * Creates a User object using a HashMap containing user attributes.
     * This method ensures that all user data is valid before creating the User object.
     *
     * @param attributes A HashMap containing user attributes.
     * @return A new User instance if all data is valid, otherwise throws IllegalArgumentException.
     */
    public static User createUser(HashMap<String, String> attributes) {
        try {
            int id = parseInteger(attributes.get("ID"), "ID");
            String firstName = parseString(attributes.get("First Name"), "First Name");
            String lastName = parseString(attributes.get("Last Name"), "Last Name");
            double moneyAvailable = parseDouble(attributes.get("Money Available"), "Money Available");
            int carsPurchased = parseInteger(attributes.get("Cars Purchased"), "Cars Purchased");
            boolean minecarsMembership = parseBoolean(attributes.get("MinerCars Membership"), "MinerCars Membership");
            String username = parseString(attributes.get("Username"), "Username");
            String password = parseString(attributes.get("Password"), "Password");

            return new User(id, firstName, lastName, moneyAvailable, carsPurchased, minecarsMembership, username, password);
        } catch (IllegalArgumentException e) {
            System.err.println("Error creating user: " + e.getMessage());
            return null;
        }
    }

    private static int parseInteger(String value, String fieldName) {
        if (value == null || value.trim().isEmpty()) {
            throw new IllegalArgumentException(fieldName + " cannot be empty");
        }
        try {
            return Integer.parseInt(value.trim());
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(fieldName + " must be a valid integer");
        }
    }

    private static double parseDouble(String value, String fieldName) {
        if (value == null || value.trim().isEmpty()) {
            throw new IllegalArgumentException(fieldName + " cannot be empty");
        }
        try {
            return Double.parseDouble(value.trim());
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(fieldName + " must be a valid double");
        }
    }

    private static boolean parseBoolean(String value, String fieldName) {
        return "true".equalsIgnoreCase(value);
    }

    private static String parseString(String value, String fieldName) {
        if (value == null || value.trim().isEmpty()) {
            throw new IllegalArgumentException(fieldName + " cannot be empty");
        }
        return value.trim();
    }
}
