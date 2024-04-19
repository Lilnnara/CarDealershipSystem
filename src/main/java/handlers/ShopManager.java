package main.java.handlers;

import main.java.models.Car;
import main.java.models.User;

import java.util.HashMap;
import java.util.List;

public class ShopManager {

    private HashMap<Integer, Car> cars;
    private HashMap<String, User> users;
    private HashMap<Integer, List<String>> userTransactions; // Key: UserID, Value: List of transaction descriptions

    public ShopManager(HashMap<Integer, Car> cars, HashMap<String, User> users) {
        this.cars = cars;
        this.users = users;
        this.userTransactions = new HashMap<>();
    }
    
    public boolean authenticateAdmin(String adminCode) {
        return "1234".equals(adminCode);  // This could later check against a more secure method
    }

    // public boolean authenticateUser(String username, String password) {
    //     // Here, you would check the credentials against those stored in userMap
    //     User user = userMap.get(username.hashCode());  // Example hash mapping, adjust based on actual key
    //     return user != null && user.getPassword().equals(password);
    // }



    // Admin functionality
    public void addCar(Car car) {
        // Add car to the HashMap
        // If needed, write changes to the CSV file
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
    }

    public void addNewUser(User user) {
        // Add new user to the HashMap
        // If needed, write changes to the CSV file
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
