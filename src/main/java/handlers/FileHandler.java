package main.java.handlers;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Scanner;

import main.java.models.Car;
import main.java.models.User;

public class FileHandler {

    public static HashMap<String, Integer> fileHeaderIndex(String filename) throws IOException {
        HashMap<String, Integer> headerIndexMap = new HashMap<>();
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

    public static HashMap<Integer, Car> createCarMap(String filename) throws IOException {
        // Create scanner
        Scanner scanner = new Scanner(new File(filename));
        // Create hashmap to store our cars (id , car object)
        HashMap<Integer, Car> carMap = new HashMap<>();
        // Create hashmap to get the indexes of the header to make it dynamic
        HashMap<String, Integer> carHeaderIndexMap;
        try {
            carHeaderIndexMap = fileHeaderIndex(filename);
        } catch (IOException e) {
            e.printStackTrace();
            scanner.close();
            return null; // Handle the exception appropriately
        }

        // Skip the header line
        scanner.nextLine();

        while (scanner.hasNextLine()) {
            String[] line = scanner.nextLine().split(",");
            // Check if the line contains all attributes for a car with turbo
            if (line.length == carHeaderIndexMap.size()) {
                // Create a new Car object with turbo
                Car newCar = new Car(Integer.parseInt(line[carHeaderIndexMap.get("Capacity")]),
                        line[carHeaderIndexMap.get("Car Type")],
                        Integer.parseInt(line[carHeaderIndexMap.get("Cars Available")]),
                        line[carHeaderIndexMap.get("Condition")], line[carHeaderIndexMap.get("Color")],
                        line[carHeaderIndexMap.get("ID")], Integer.parseInt(line[carHeaderIndexMap.get("Year")]),
                        Double.parseDouble(line[carHeaderIndexMap.get("Price")]),
                        line[carHeaderIndexMap.get("Transmission")], line[carHeaderIndexMap.get("VIN")],
                        line[carHeaderIndexMap.get("Fuel Type")], line[carHeaderIndexMap.get("Model")],
                        line[carHeaderIndexMap.get("hasTurbo")]);
                // Add the car to the map
                carMap.put(Integer.parseInt(line[carHeaderIndexMap.get("ID")]), newCar);
            }
            // Check if the line contains all attributes except turbo
            else if (line.length == carHeaderIndexMap.size() - 1) {
                // Create a new Car object without turbo
                Car newCar = new Car(Integer.parseInt(line[carHeaderIndexMap.get("Capacity")]),
                        line[carHeaderIndexMap.get("Car Type")],
                        Integer.parseInt(line[carHeaderIndexMap.get("Cars Available")]),
                        line[carHeaderIndexMap.get("Condition")], line[carHeaderIndexMap.get("Color")],
                        line[carHeaderIndexMap.get("ID")], Integer.parseInt(line[carHeaderIndexMap.get("Year")]),
                        Double.parseDouble(line[carHeaderIndexMap.get("Price")]),
                        line[carHeaderIndexMap.get("Transmission")], line[carHeaderIndexMap.get("VIN")],
                        line[carHeaderIndexMap.get("Fuel Type")], line[carHeaderIndexMap.get("Model")]);
                // Add the car to the map
                carMap.put(Integer.parseInt(line[carHeaderIndexMap.get("ID")]), newCar);
            } else {
                System.out.println("Incomplete data for a car in the file: " + String.join(", ", line));
            }
        }
        scanner.close();
        return carMap;
    }

    public static HashMap<Integer, User> createUserMap(String filename) throws IOException {
        // Create scanner
        Scanner scanner = new Scanner(new File(filename));
        // Create hashmap to store our cars (id , car object)
        HashMap<Integer, User> userMap = new HashMap<>();
        // Create hashmap to get the indexes of the header to make it dynamic
        HashMap<String, Integer> userHeaderIndexMap;
        try {
            userHeaderIndexMap = fileHeaderIndex(filename);
        } catch (IOException e) {
            e.printStackTrace();
            scanner.close();
            return null; // Handle the exception appropriately
        }

        // Skip the header line
        scanner.nextLine();

        while (scanner.hasNextLine()) {
            String[] line = scanner.nextLine().split(",");
            // Check if the line contains all attributes for a car with turbo
            if (line.length == userHeaderIndexMap.size()) {
                // Create a new Car object with turbo
                User newUser = new User(Integer.parseInt(line[userHeaderIndexMap.get("ID")]),
                        line[userHeaderIndexMap.get("First Name")],
                        line[userHeaderIndexMap.get("Last Name")], 
                        Double.parseDouble(line[userHeaderIndexMap.get("Money Available")]), 
                        Integer.parseInt(line[userHeaderIndexMap.get("Cars Purchased")]),
                        Boolean.parseBoolean(line[userHeaderIndexMap.get("MinerCars Membership")]), 
                        line[userHeaderIndexMap.get("Username")], 
                        line[userHeaderIndexMap.get("Password")]);
                // Add the user to the map
                userMap.put(Integer.parseInt(line[userHeaderIndexMap.get("ID")]), newUser);
            }
            else {
                System.out.println("Incomplete data for a USER in the file: " + String.join(", ", line));
            }
        }
        scanner.close();
        return userMap;
    }
    
}
