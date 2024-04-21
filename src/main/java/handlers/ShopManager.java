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

        // call factory to create car object
        Car newCar = carFactory.create(carAttributes);

        cars.put(Integer.parseInt(newCar.getId()), newCar);
        System.out.println("Car added sucessfully!");
        FileHandler.updateCarFile("resources/car_data.csv", cars, carHeaderIndexMap); 


        return cars;
    }

    /**
     * Retrieves the revenue for a specific car ID by summing up all final prices
     * of purchase-type tickets and subtracting the final prices of return-type tickets.
     *
     * @param carId The ID of the car for which revenue is calculated.
     * @return The total revenue for the car if found, or -1.0 if no transactions were found.
     */
    public double getRevenueById(String carId) {
        double revenue = 0.0;
        boolean typeFound = false; // Flag to check if any relevant tickets are found

        for (Ticket ticket : tickets.values()) {
            if (carId.equals(ticket.getCarId())) { // Check if car IDs match
                typeFound = true; // Set flag to true indicating that at least one ticket is processed
                if ("Purchase".equals(ticket.getTicketStatus())) {
                    revenue += ticket.getFinalPrice(); // Add to revenue for purchase type
                } else if ("Return".equals(ticket.getTicketStatus())) {
                    revenue -= ticket.getFinalPrice(); // Subtract from revenue for return type
                }
            }
        }

        if (typeFound) {
            System.out.println("\nTotal Revenue for Car ID " + carId + ": $" + revenue);
            return revenue;
        } else {
            System.out.println("\nNo transactions found for Car ID " + carId);
            return -1.0; // Return -1.0 to indicate no transactions were found
        }
    }


    
    

    /**
     * Retrieves the revenue for a specific car type by summing up all final prices
     * of purchase-type tickets and subtracting the final prices of return-type tickets.
     *
     * @param carType The type of the car for which revenue is calculated.
     * @return The total revenue for the car type if found, or -1.0 if no transactions were found.
     */
    public double getRevenueByCarType(String carType) {
        double revenue = 0.0;
        boolean typeFound = false;

        // Loop through each car to find those that match the specified car type
        for (Car car : cars.values()) {
            if (carType.equals(car.getCarType())) {
                typeFound = true; // We found at least one car of this type
                // Now we loop through all tickets and match them to the car by ID
                for (Ticket ticket : tickets.values()) {
                    if (car.getId().equals(ticket.getCarId())) {
                        if ("Purchase".equals(ticket.getTicketStatus())) {
                            revenue += ticket.getFinalPrice(); // Add to revenue for purchase type
                        } else if ("Return".equals(ticket.getTicketStatus())) {
                            revenue -= ticket.getFinalPrice(); // Subtract from revenue for return type
                        }
                    }
                }
            }
        }

        // Outputting results based on whether any cars of the specified type were found
        if (!typeFound) {
            System.out.println("\nNo cars found for the car type: '" + carType + "'.");
            return -1.0; // Indicate that no transactions were found
        } else {
            System.out.println("\nTotal Revenue for Car Type '" + carType + "': $" + revenue);
        }
        return revenue;
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

        // User should be able to return a car (The user should get the money back, the car should no longer appear in user’s purchased cars, car csv should be updated accordingly) 
    }

    /**
     * Displays all cars available in the system.
     * Each car's details are printed followed by a separator for clear readability.
     */
    public void browseCars() {
        System.out.println("Displaying all cars:\n");
        for (Car car : cars.values()) {
            System.out.println(car);
            System.out.println("--------------------------------------------------"); // separator for readability
        }
    }

    /**
     * Filters cars based on a specific condition and displays them.
     * The method supports filtering by 'New', 'Used', or 'Available'.
     *
     * @param filterOption The filter condition to apply ('New', 'Used', 'Available').
     */
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

    /**
     * Retrieves and displays all transactions associated with a specific user.
     * Each transaction is printed out; if no transactions are found, a message is displayed.
     *
     * @param username The username whose transactions are to be fetched and displayed.
     */
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



}
