package main.java.handlers;

import main.java.factory.CarFactory;
import main.java.factory.UserFactory;
import main.java.models.Car;
import main.java.models.User;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class FileHandler {

    public static HashMap<String, Integer> fileHeaderIndex(String filename) throws IOException {
        HashMap<String, Integer> headerIndexMap = new HashMap<>();
        try (Scanner scanner = new Scanner(new File(filename))) {
            if (scanner.hasNextLine()) {
                String[] headers = scanner.nextLine().split(",");
                for (int i = 0; i < headers.length; i++) {
                    headerIndexMap.put(headers[i].trim(), i);  // Trim headers to prevent whitespace issues
                }
            }
        }
        return headerIndexMap;
    }

    public static HashMap<Integer, Car> createCarMap(String filename) throws IOException {
        HashMap<Integer, Car> carMap = new HashMap<>();
        HashMap<String, Integer> carHeaderIndexMap = fileHeaderIndex(filename);
    
        try (Scanner scanner = new Scanner(new File(filename))) {
            scanner.nextLine(); // Skip the header line
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine().trim();
                    String[] fields = line.split(",(?=([^\"]*\"[^\"]*\")*[^\"]*$)"); // Split on commas outside quotes
                    HashMap<String, String> attributes = new HashMap<>();
                    for (Map.Entry<String, Integer> entry : carHeaderIndexMap.entrySet()) {
                        String key = entry.getKey();
                        int index = entry.getValue();
                        String value = (index < fields.length) ? fields[index].trim() : "";
                        attributes.put(key, value);
                    }
                    try {
                        Car newCar = CarFactory.createCar(attributes);
                        carMap.put(Integer.parseInt(attributes.get("ID")), newCar);
                    } catch (Exception e) {
                        System.err.println("Failed to create car from line: " + line);
                        e.printStackTrace();
                    }
            }
        }
        return carMap;
    }
    

    public static HashMap<Integer, User> createUserMap(String filename) throws IOException {
        HashMap<Integer, User> userMap = new HashMap<>();
        HashMap<String, Integer> userHeaderIndexMap = fileHeaderIndex(filename);

        try (Scanner scanner = new Scanner(new File(filename))) {
            scanner.nextLine(); // Skip the header line
            while (scanner.hasNextLine()) {
                String[] line = scanner.nextLine().split(",");
                HashMap<String, String> attributes = new HashMap<>();
                for (String key : userHeaderIndexMap.keySet()) {
                    attributes.put(key, line[userHeaderIndexMap.get(key)]);
                }
                try {
                    User newUser = UserFactory.createUser(attributes);
                    userMap.put(Integer.parseInt(attributes.get("ID")), newUser);
                } catch (Exception e) {
                    // Handling errors quietly or use a logger if needed
                }
            }
        }
        return userMap;
    }
}
