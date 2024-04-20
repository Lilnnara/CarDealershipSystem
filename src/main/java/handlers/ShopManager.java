package main.java.handlers;

import java.util.Scanner;
import main.java.models.Car;
import main.java.models.User;
import main.java.models.Ticket;

import java.util.LinkedHashMap;
import java.util.HashMap;
import java.util.Map;
import java.util.List;
import main.java.factory.CarFactory;
import main.java.factory.UserFactory;
import main.java.factory.TicketFactory;


public class ShopManager {

    private HashMap<Integer, Car> cars;
    private HashMap<String, User> users;
    private HashMap<Integer, Ticket> tickets;
    private HashMap<Integer, List<String>> userTransactions; // Key: UserID, Value: List of transaction descriptions
    private LinkedHashMap<String, Integer> carHeaderIndexMap;
    private LinkedHashMap<String, Integer> userHeaderIndexMap;
    private LinkedHashMap<String, Integer> ticketHeaderIndexMap;
    private CarFactory carFactory = new CarFactory();
    private UserFactory userFactory = new UserFactory();
    private TicketFactory ticketFactory = new TicketFactory();
    

    public ShopManager(HashMap<Integer, Car> cars, HashMap<String, User> users, HashMap<Integer, Ticket> tickets,
                       LinkedHashMap<String, Integer> carHeaderIndexMap,
                       LinkedHashMap<String, Integer> userHeaderIndexMap,
                       LinkedHashMap<String, Integer> ticketHeaderIndexMap) {
        this.cars = cars;
        this.users = users;
        this.tickets = tickets;
        this.carHeaderIndexMap = carHeaderIndexMap;
        this.userHeaderIndexMap = userHeaderIndexMap;
        this.userTransactions = new HashMap<>();
        this.ticketHeaderIndexMap = ticketHeaderIndexMap;
        
    }

    public boolean authenticateAdmin(String adminCode) {
        return "1234".equals(adminCode); // This could later check against a more secure method
    }

    // public boolean authenticateUser(String username, String password) {
    // // Here, you would check the credentials against those stored in userMap
    // User user = userMap.get(username.hashCode()); // Example hash mapping, adjust
    // based on actual key
    // return user != null && user.getPassword().equals(password);
    // }

    // Admin functionality
    public HashMap<Integer, Car> addCar() {
        HashMap<String, String> carAttributes = new HashMap<>();

        Scanner scanner = new Scanner(System.in);
        // get admin input
        System.out.println("Hello! to add a car we will need the following information: \n ");

        for (Map.Entry<String, Integer> entry : carHeaderIndexMap.entrySet()) {
            System.out.println("Enter " + entry.getKey() + ":");
            String userInput = scanner.nextLine();
            carAttributes.put(entry.getKey(), userInput);
        }

        System.out.println(carAttributes);

        // call factory to create car object
        Car newCar = carFactory.create(carAttributes);

        //print car to test
        // System.out.println(newCar.toString());
        //add car to hashmap
        cars.put(Integer.parseInt(newCar.getId()), newCar);

        System.out.println("Car added sucessfully!");
        
        //update csv with the new car <3 uncomment if we want to update evrytime we add a car
        FileHandler.updateCarFile("resources/car_data.csv", cars, carHeaderIndexMap); 

        //scanner.close();
        // RETURN HASHMAP
        return cars;
    }

    public void getRevenueById(int id) {
        // Calculate revenue for a given car ID
    }

    public void getRevenueByCarType(String carType) {
        // Calculate revenue for a given car type
    }

    public void removeCar(int carId) {
        // Remove car from HashMap
        // If needed, write changes to the CSV file
        cars.remove(carId);
        System.out.println("car with ID: "+ carId + " removed sucessfully");

    }

    public HashMap<String, User> addNewUser() {
        // Add new user to the HashMap
        // If needed, write changes to the CSV file
        HashMap<String, String> userAttributes = new HashMap<>();

        Scanner scanner = new Scanner(System.in);
        // get admin input
        System.out.println("Hello! to add a new user we will need the following information: \n ");

        for (Map.Entry<String, Integer> entry : userHeaderIndexMap.entrySet()) {
            System.out.println("Enter " + entry.getKey() + ":");
            String userInput = scanner.nextLine();
            userAttributes.put(entry.getKey(), userInput);
        }

        System.out.println(userAttributes);

        // call factory to create car object
        User newUser = userFactory.create(userAttributes);

        //print car to test
        // System.out.println(newCar.toString());
        //add car to hashmap
        users.put(newUser.getUsername(), newUser);

        System.out.println("User added sucessfully!");
        //update csv with the new User <3 uncomment if we want to update evrytime we add a User
        FileHandler.updateUserFile("resources/user_data.csv", users, userHeaderIndexMap); 

        // scanner.close();
        // RETURN HASHMAP
        return users;
    }

    // User functionality
    public void purchaseCar(int userId, int carId) {
        // Process car purchase
        // Update user's cars purchased
        // Update cars available
        // Record transaction
        // If needed, write changes to the CSV files
    }

    public void returnCar(int userId, int carId) {
        // Process car return
        // Update user's cars purchased
        // Update cars available
        // Record transaction
        // If needed, write changes to the CSV files
    }

    public HashMap<Integer, Car> browseCars() {
        // Return a list of available cars
        return cars;
    }

    public List<String> viewUserTransactions(int userId) {
        // Return a list of past transactions for a given user
        return userTransactions.getOrDefault(userId, List.of());
    }

    // Additional helper methods as needed...
}
