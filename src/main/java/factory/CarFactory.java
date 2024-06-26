package main.java.factory;

import main.java.models.Car;
import java.util.HashMap;

/**
 * Factory class for creating instances of Car.
 * Implements the Factory interface to provide a consistent method of constructing Car objects
 * based on a HashMap of attributes.
 */
public class CarFactory implements Factory<Car> {

    /**
     * Creates a Car object from a HashMap containing attribute values.
     * If values are missing in the HashMap, defaults are used to ensure the object remains consistent.
     *
     * @param attributes A HashMap containing Car attribute values.
     * @return A new Car object or null if critical attributes are invalid.
     */
    @Override
    public Car create(HashMap<String, String> attributes) {
        try {
            int capacity = Integer.parseInt(attributes.getOrDefault("Capacity", "0"));
            String carType = attributes.getOrDefault("Car Type", "Unknown");
            int carsAvailable = Integer.parseInt(attributes.getOrDefault("Cars Available", "0"));
            String condition = attributes.getOrDefault("Condition", "Unknown");
            String color = attributes.getOrDefault("Color", "Unknown");
            String id = attributes.getOrDefault("ID", "0");
            int year = Integer.parseInt(attributes.getOrDefault("Year", "0"));
            double price = Double.parseDouble(attributes.getOrDefault("Price", "0.0"));
            String transmission = attributes.getOrDefault("Transmission", "Unknown");
            String vin = attributes.getOrDefault("VIN", "Unknown");
            String fuelType = attributes.getOrDefault("Fuel Type", "Unknown");
            String model = attributes.getOrDefault("Model", "Unknown");
            boolean turbo = "Yes".equalsIgnoreCase(attributes.getOrDefault("hasTurbo", "No"));

            return new Car(capacity, carType, carsAvailable, condition, color, id, year,
                           price, transmission, vin, fuelType, model, turbo);
        } catch (NumberFormatException e) {
            System.err.println("Error creating car with attributes: " + attributes);
            System.err.println("Exception: " + e.getMessage());
            return null;  // Return null if there is an error in parsing integers or doubles
        }
    }
}
