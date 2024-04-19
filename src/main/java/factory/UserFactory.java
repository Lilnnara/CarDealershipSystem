package main.java.factory;

import main.java.models.User;
import java.util.HashMap;

/**
 * Factory class for creating instances of User.
 * Implements the Factory interface to provide a consistent method of constructing User objects
 * based on a HashMap of attributes.
 */
public class UserFactory implements Factory<User> {

    /**
     * Creates a User object from a HashMap containing attribute values.
     * This method ensures that all user data is validated before creating the User object.
     *
     * @param attributes A HashMap containing user attributes.
     * @return A new User instance if all data is valid, otherwise throws IllegalArgumentException.
     */
    @Override
    public User create(HashMap<String, String> attributes) {
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

    /**
     * Parses an integer value from a string and throws an IllegalArgumentException if the value is invalid.
     *
     * @param value     The string value to parse.
     * @param fieldName The name of the field being parsed (for error messages).
     * @return The parsed integer value.
     * @throws IllegalArgumentException If the string value is empty or cannot be parsed as an integer.
     */
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

    /**
     * Parses a double value from a string and throws an IllegalArgumentException if the value is invalid.
     *
     * @param value     The string value to parse.
     * @param fieldName The name of the field being parsed (for error messages).
     * @return The parsed double value.
     * @throws IllegalArgumentException If the string value is empty or cannot be parsed as a double.
     */
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

    /**
     * Parses a boolean value from a string.
     *
     * @param value     The string value to parse.
     * @param fieldName The name of the field being parsed (for error messages).
     * @return The parsed boolean value.
     */
    private static boolean parseBoolean(String value, String fieldName) {
        return "true".equalsIgnoreCase(value);
    }

    /**
     * Parses a string value and throws an IllegalArgumentException if the value is empty.
     *
     * @param value     The string value to parse.
     * @param fieldName The name of the field being parsed (for error messages).
     * @return The parsed string value.
     * @throws IllegalArgumentException If the string value is empty.
     */
    private static String parseString(String value, String fieldName) {
        if (value == null || value.trim().isEmpty()) {
            throw new IllegalArgumentException(fieldName + " cannot be empty");
        }
        return value.trim();
    }
}
