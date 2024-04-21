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
        this.ticketHeaderIndexMap = ticketHeaderIndexMap;
        
    }

    public boolean authenticateAdmin(String adminCode) {
        return "1234".equals(adminCode); // This could later check against a more secure method
    }


    public User authenticateUser(String username, String password){
        User user = users.get(username);
        if(user != null && user.getPassword().equals(password)){
            return user;
        }
        else{
            return null;
        }
    }


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

    public void browseCars() {
        System.out.println("Displaying all cars:\n");
        for (Car car : cars.values()) {
            System.out.println(car);
            System.out.println("--------------------------------------------------"); // separator for readability
    }

   
    }
    
    public void filterCars(String filterOption) {
        System.out.println();
        switch (filterOption) {
            case "New":
                cars.values().stream()
                    .filter(car -> "New".equalsIgnoreCase(car.getCondition()))
                    .forEach(System.out::println);
                break;
            case "Used":
                cars.values().stream()
                    .filter(car -> "Used".equalsIgnoreCase(car.getCondition()))
                    .forEach(System.out::println);
                break;
            case "Available":
                cars.values().stream()
                    .filter(car -> car.getCarsAvailable() > 0)
                    .forEach(System.out::println);
                break;
            default:
                System.out.println("No such filter option available");
        }
    }
    

    public void viewUserTransactions(String username) {
        System.out.println();
        System.out.println("Fetching transactions for user: " + username);
        boolean transactionsFound = false;
        
        for (Ticket ticket : tickets.values()) {
            if (ticket.getUsername().equals(username)) {
                System.out.println(ticket);
                transactionsFound = true;
            }
        }
        
        if (!transactionsFound) {
            System.out.println("No transactions found for the specified user.");
        }
    }
    

    // Additional helper methods as needed...
}
