package main.java.ui;

import main.java.handlers.FileHandler;
import main.java.models.Car;
import main.java.models.User;

import java.util.HashMap;

import java.io.IOException;


public class ShopRunner {

    public static void main(String[] args) {
        try {
            System.out.println("Working Directory = " + System.getProperty("user.dir"));

            String usersFilename = "resources/user_data.csv";
            String carsFilename = "resources/car_data.csv";

            // Load the user and car data into maps
            HashMap<Integer, User> userMap = FileHandler.createUserMap(usersFilename);
            HashMap<Integer, Car> carMap = FileHandler.createCarMap(carsFilename);

            // Print out the loaded users and cars
            System.out.println("Loaded Users:");
            userMap.values().forEach(System.out::println);

            System.out.println("\nLoaded Cars:");
            carMap.values().forEach(System.out::println);

        } catch (IOException e) {
            System.err.println("Error loading data: " + e.getMessage());
            e.printStackTrace();
        } catch (NumberFormatException e) {
            System.err.println("Number format exception: " + e.getMessage());
            e.printStackTrace();
        } catch (Exception e) {
            System.err.println("An unexpected error occurred: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
