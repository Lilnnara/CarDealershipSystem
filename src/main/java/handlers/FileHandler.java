package main.java.handlers;

import main.java.factory.CarFactory;
import main.java.factory.UserFactory;
import main.java.factory.Factory;
import main.java.factory.TicketFactory;
import main.java.models.Ticket;
import main.java.models.Car;
import main.java.models.User;

import java.io.FileWriter;
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
    private static Factory<Ticket> ticketFactory = new TicketFactory();


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
     * Creates a HashMap of tickets read from a CSV file, with ticket IDs as keys and Ticket objects as values.
     *
     * @param filename The path to the CSV file containing ticket data. Must inclue resources/____.csv to scan from the correct file
     * @param ticketHeaderIndexMap A LinkedHashMap containing header names as keys and their corresponding indices as values.
     * @return A HashMap containing ticket IDs as keys and corresponding Ticket objects as values.
     * @throws IOException If an I/O error occurs while reading the file.
     */
    public static HashMap<Integer, Ticket> createTicketMap(String filename, LinkedHashMap<String, Integer> ticketHeaderIndexMap) throws IOException {
        HashMap<Integer, Ticket> ticketMap = new HashMap<>();

        try (Scanner scanner = new Scanner(new File(filename))) {
            scanner.nextLine(); // Skip the header line
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine().trim();
                String[] fields = line.split(","); // Split fields by comma
                HashMap<String, String> attributes = new HashMap<>();
                for (Map.Entry<String, Integer> entry : ticketHeaderIndexMap.entrySet()) {
                    String key = entry.getKey();
                    int index = entry.getValue();
                    String value = (index < fields.length) ? fields[index].trim() : "";
                    attributes.put(key, value);
                }
                try {
                    Ticket newTicket = ticketFactory.create(attributes);
                    ticketMap.put(Integer.parseInt(attributes.get("TicketID")), newTicket);
                } catch (Exception e) {
                    System.err.println("Failed to create ticket from line: " + line);
                    e.printStackTrace();
                }
            }
        }
        return ticketMap;
    }

    /**
     * Creates a HashMap of cars read from a CSV file, with car IDs as keys and Car objects as values.
     *
     * @param filename The path to the CSV file containing car data. Must inclue resources/____.csv to scan from the correct file
     * @return A HashMap containing car IDs as keys and corresponding Car objects as values.
     * @throws IOException If an I/O error occurs while reading the file.
     */
    public static HashMap<Integer, Car> createCarMap(String filename, LinkedHashMap<String, Integer> carHeaderIndexMap) throws IOException {
        HashMap<Integer, Car> carMap = new HashMap<>();
        
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
     * @param filename The path to the CSV file containing user data. Must inclue resources/____.csv to scan from the correct file
     * @return A HashMap containing user IDs as keys and corresponding User objects as values.
     * @throws IOException If an I/O error occurs while reading the file.
     */
    public static HashMap<String, User> createUserMap(String filename, LinkedHashMap<String, Integer> userHeaderIndexMap) throws IOException {
        HashMap<String, User> userMap = new HashMap<>();

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

    

    /**
 * Updates a CSV file with car data using the provided filename, car map, and headers.
 *
 * @param filename The name of the CSV file to be updated. Must inclue resources/____.csv to save to the correct folder
 * @param carMap   A HashMap containing car data, where the keys are integer IDs and the values are Car objects.
 * @param headers  A LinkedHashMap containing header names and their corresponding indices.
 *                 This is used to ensure consistent ordering of columns in the CSV file.
 */
public static void updateCarFile(String filename, HashMap<Integer, Car> carMap, LinkedHashMap<String, Integer> headers) {
    try (FileWriter csvWriter = new FileWriter(filename, false)) {

        int size = headers.size();
        int count = 0;

        // Write headers
        for (Map.Entry<String, Integer> entry : headers.entrySet()) {
            csvWriter.append(entry.getKey());
            if (++count < size) {
                csvWriter.append(",");
            }
        }
        csvWriter.append("\n");

        // Write car data
        for (int i = 1; i <= carMap.size(); i++) {
            Car car = carMap.get(i);
            csvWriter.append(car.toStringCSV());
            if (i != carMap.size()) {
                csvWriter.append("\n");
            }
        }
    } catch (IOException e) {
        e.printStackTrace();
    }
}

/**
 * Updates a CSV file with user data using the provided filename, user map, and headers.
 *
 * @param filename The name of the CSV file to be updated. Must inclue resources/____.csv to save to the correct folder
 * @param users  A HashMap containing user data, where the keys are integer IDs and the values are User objects.
 * @param headers  A LinkedHashMap containing header names and their corresponding indices.
 *                 This is used to ensure consistent ordering of columns in the CSV file.
 */
public static void updateUserFile(String filename, HashMap<String, User> users, LinkedHashMap<String, Integer> headers) {
    try (FileWriter csvWriter = new FileWriter(filename, false)) {
        int size = headers.size();
        int count = 0;

        // Write headers
        for (Map.Entry<String, Integer> entry : headers.entrySet()) {
            csvWriter.append(entry.getKey());
            if (++count < size) {
                csvWriter.append(",");
            }
        }
        csvWriter.append("\n");
        //collect the usernames in a hashmap to have the ID values to print to file in the correct order
        HashMap<Integer, String> usernameIds = new HashMap<>();
        for (Map.Entry<String, User> entry : users.entrySet()) {
            usernameIds.put(entry.getValue().getId(),entry.getKey());
        }

        // Write user data
        for (int i = 1; i <= users.size(); i++) {
            User user = users.get(usernameIds.get(i));
            csvWriter.append(user.ToStringCSV());
            if (i != users.size()) {
                csvWriter.append("\n");
            }
        }
    } catch (IOException e) {
        e.printStackTrace();
    }
}


}
