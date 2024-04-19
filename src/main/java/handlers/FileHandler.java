package main.java.handlers;

import main.java.factory.CarFactory;
import main.java.factory.UserFactory;
import main.java.factory.Factory;
import main.java.models.Car;
import main.java.models.User;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * The FileHandler class provides methods for handling file operations related to users and cars.
 */
public class FileHandler {

    private static Factory<Car> carFactory = new CarFactory();
    private static Factory<User> userFactory = new UserFactory();

    /**
     * Reads the header line of a CSV file and creates a mapping of header names to their corresponding indices.
     *
     * @param filename The path to the CSV file.
     * @return A HashMap containing header names as keys and their corresponding indices as values.
     * @throws IOException If an I/O error occurs while reading the file.
     */
    public static LinkedHashMap<String, Integer> fileHeaderIndex(String filename) throws IOException {
        LinkedHashMap<String, Integer> headerIndexMap = new LinkedHashMap<>();
        try (Scanner scanner = new Scanner(new File(filename))) {
            if (scanner.hasNextLine()) {
                String[] headers = scanner.nextLine().split(",");
                for (int i = 0; i < headers.length; i++) {
                    headerIndexMap.put(headers[i], i);
                }
            }
        }
        return headerIndexMap;
    }

    /**
     * Creates a HashMap of cars read from a CSV file, with car IDs as keys and Car objects as values.
     *
     * @param filename The path to the CSV file containing car data.
     * @return A HashMap containing car IDs as keys and corresponding Car objects as values.
     * @throws IOException If an I/O error occurs while reading the file.
     */
    public static HashMap<Integer, Car> createCarMap(String filename) throws IOException {
        HashMap<Integer, Car> carMap = new HashMap<>();
        LinkedHashMap<String, Integer> carHeaderIndexMap = fileHeaderIndex(filename);

        try (Scanner scanner = new Scanner(new File(filename))) {
            scanner.nextLine(); // Skip the header line
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine().trim();
                String[] fields = line.split(","); // Split fields by comma
                HashMap<String, String> attributes = new HashMap<>();
                for (Map.Entry<String, Integer> entry : carHeaderIndexMap.entrySet()) {
                    String key = entry.getKey();
                    int index = entry.getValue();
                    String value = (index < fields.length) ? fields[index].trim() : "";
                    attributes.put(key, value);
                }
                try {
                    Car newCar = carFactory.create(attributes);
                    carMap.put(Integer.parseInt(attributes.get("ID")), newCar);
                } catch (Exception e) {
                    System.err.println("Failed to create car from line: " + line);
                    e.printStackTrace();
                }
            }
        }
        return carMap;
    }

    /**
     * Creates a HashMap of users read from a CSV file, with user IDs as keys and User objects as values.
     *
     * @param filename The path to the CSV file containing user data.
     * @return A HashMap containing user IDs as keys and corresponding User objects as values.
     * @throws IOException If an I/O error occurs while reading the file.
     */
    public static HashMap<String, User> createUserMap(String filename) throws IOException {
        HashMap<String, User> userMap = new HashMap<>();
        LinkedHashMap<String, Integer> userHeaderIndexMap = fileHeaderIndex(filename);

        try (Scanner scanner = new Scanner(new File(filename))) {
            scanner.nextLine(); // Skip the header line
            while (scanner.hasNextLine()) {
                String[] line = scanner.nextLine().split(",");
                HashMap<String, String> attributes = new HashMap<>();
                for (String key : userHeaderIndexMap.keySet()) {
                    attributes.put(key, line[userHeaderIndexMap.get(key)]);
                }
                try {
                    User newUser = userFactory.create(attributes);
                    userMap.put(attributes.get("Username"), newUser);
                } catch (Exception e) {
                    // Handling errors quietly or use a logger if needed
                }
            }
        }
        return userMap;
    }

    // TODO: We need to create a method that updates CSV file for Car and User
}
