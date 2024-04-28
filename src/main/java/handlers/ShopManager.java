package main.java.handlers;

import java.util.Scanner;
import main.java.models.Car;
import main.java.models.User;
import main.java.ui.CarAdditionMenu;
import main.java.models.Ticket;

import java.util.Collections;
import java.util.Date;
import main.java.utils.Log;

import java.util.LinkedHashMap;
import java.util.HashMap;
import java.util.Map;
import java.util.LinkedList;
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
    public LinkedList<Log> logsLinkedList = new LinkedList<Log>();
    private CarFactory carFactory = new CarFactory();
    private UserFactory userFactory = new UserFactory();
    private TicketFactory ticketFactory = new TicketFactory();
    private int lastUsedId = 100;
    

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

        this.lastUsedId = initializeLastUsedId();
    }

    private int initializeLastUsedId() {
    return cars.isEmpty() ? 100 : Collections.max(cars.keySet());
}

    
    /** 
     * Method to check a Admin Login is valid.
     * @param adminCode
     * @return boolean
     */
    public boolean authenticateAdmin(String adminCode) {
        return "1234".equals(adminCode); // This could later check against a more secure method
    }


    
    /** 
     * Method to check a User Login is valid.
     * @param username
     * @param password
     * @return User
     */
    public User authenticateUser(String username, String password){
        User user = users.get(username);
        if(user != null && user.getPassword().equals(password)){
            return user;
        }
        else{
            return null;
        }
    }


    /** 
     * Method to add a car.
     * @return HashMap<Integer, Car>
     */
    public void addCar() {
        CarAdditionMenu carAdditionMenu = new CarAdditionMenu();
        HashMap<String, String> carAttributes = carAdditionMenu.collectCarAttributes();
    
        // Calculate the next ID
        int nextId = lastUsedId + 1;
        carAttributes.put("ID", String.valueOf(nextId)); // Add the ID to the car attributes HashMap
    
        // Call factory to create car object
        Car newCar = carFactory.create(carAttributes);
        cars.put(nextId, newCar); // Use nextId as the key for the HashMap of cars
        lastUsedId = nextId; // Update last used ID
    
        System.out.println("\nCar added successfully.");
        System.out.println("-----------------------------------------");
        System.out.println(newCar);
    
        // Update car file and logs
        FileHandler.updateCarFile("resources/car_data.csv", cars, carHeaderIndexMap);
        logsLinkedList.add(new Log("Admin", "Added a car with ID: " + nextId));
    }
    

    /**
     * Retrieves the revenue for a specific car ID by summing up all final prices
     * of purchase-type tickets and subtracting the final prices of return-type tickets.
     *
     * @param carId The ID of the car for which revenue is calculated.
     * @return The total revenue for the car if found, or -1.0 if no transactions were found.
     */
    public double getRevenueById(String carId) {
        logsLinkedList.add(new Log("Admin ","got revenue by ID."));
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
        logsLinkedList.add(new Log("Admin ","got revenue by Car Type."));
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
    
    /** 
     * Method to remove a car.
     * @param carId
     */
    public void removeCar(int carId) {
        // Remove car from HashMap
        // If needed, write changes to the CSV file
        cars.remove(carId);
        System.out.println("car with ID: "+ carId + " removed sucessfully");
        FileHandler.updateCarFile("resources/car_data.csv", cars, carHeaderIndexMap); 
        logsLinkedList.add(new Log("Admin ","Removed a car."));
    }

    
    /** 
     * Method to add a new User.
     * @return HashMap<String, User>
     */
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
        logsLinkedList.add(new Log("Admin ","Added a user."));
        // scanner.close();
        // RETURN HASHMAP
        return users;
    }

    
    /** 
     * Uses updateCarsAvailable to update the car values affected by a car purchase or return.
     * @param car
     * @param isPurchase
     * @param amount
     */
    public void updateStock(Car car, boolean isPurchase, int amount){
        //! is purchase because updateCarsAvailable uses is increase value
        car.updateCarsAvailable(!isPurchase, amount);
        FileHandler.updateCarFile("resources/car_data.csv", cars, carHeaderIndexMap); 
    }

    
    /** 
     * Uses updateCarsPurchased and updateMoneyAvailable to update the user values affected by a car purchase or return.
     * @param user
     * @param isPurchase
     * @param moneyAmount
     * @param carAmount
     */
    public void updateUserData(User user, boolean isPurchase, double moneyAmount, int carAmount){
        user.updateCarsPurchased(isPurchase, carAmount);
        //! is purchase because updateMoneyAvailable uses is increase value
        user.updateMoneyAvailable(!isPurchase, moneyAmount);
        FileHandler.updateUserFile("resources/user_data.csv", users, userHeaderIndexMap); 
    }

    
    /** 
     * Calculates the membership discount of a car.
     * @param car
     * @return double
     */
    public double getMembershipCost(Car car){
        //10% off means cost is 90% of the original aka 0.9
        return 0.9*(car.getPrice());
    }

    
    /** 
     * Calculates and returns the cost with Taxes of the given price
     * @param price
     * @return double
     */
    public double getCostAfterTaxes(double price){
        // state taxes are 6.25% so an aditional 0.0625 out of 1 so 1.0625 times price
        return 1.0625 * price;
    }
    
    
    /** 
     * User Interface for purchaseCar.  Validates if a car is within budget and in stock before calling PurchaseCar.
     * @param user
     */
    public void purchaseCarUi(User user) {
        System.out.println("Which car would you like to purchase?");
        System.out.println("Please enter a car Id:");
        Scanner scanner = new Scanner(System.in);
        if(scanner.hasNextInt()){
            int input = scanner.nextInt();
            if(cars.get(input)!= null){
                Car car = cars.get(input);
                if(car.getCarsAvailable() < 1){
                    //if the number of available cars is zero or less return eror and exit method with return;
                    System.out.println("\t The car you are trying to purchase is currently out of stock.");
                    return;
                }
                double price = 0;
                //calculate reduced cost if the user is a member or not.
                if(user.isMinecarsMembership()){
                    //10% off or 90% of the total cost
                    price = (0.90) * car.getPrice();
                }
                else{
                    price = car.getPrice();
                }
                //if the car is outside of their budget return eror and exit method with return;
                if(price > user.getMoneyAvailable()){
                    System.out.println("\t The car you are trying to purchase is outside of your available funds.");
                    return;
                }
                //if car is within budget for the user, ask them if they would like to continue with purchse and if so generate and return ticket.  
                System.out.println("\t The car you are trying to purchase is in stock and within your budget!");
                if(user.isMinecarsMembership()){
                    System.out.println("\t Would you like to purchase for the cost of: " + price + "(The member discout of %10 off the asking price?)");
                }
                else{
                    System.out.println("\t Would you like to purchase for the cost of: " + price);
                }
                System.out.println(car);
                System.out.println("\t 1) Yes, 2) No");
                if(scanner.hasNextInt()){
                    input = scanner.nextInt();
                    if(input == 1){
                        purchaseCar(user, car);
                    }
                    else{
                        System.out.println("Purchase failed.  Please enter a valid response.");
                    }
                }
            }
            else{
                System.out.println("Invalid Car ID for purchase.");
                return;
            }
        }
        else {
            System.out.println("Invalid Car ID for purchase.");
            return;
        }

    }

    /** 
     * Takes user and car objects and processes a car purchase.
     * @param user
     * @param car
     */
    public void purchaseCar(User user, Car car) {
        double price = 0;
        if(user.isMinecarsMembership()){
            price = getCostAfterTaxes(getMembershipCost(car));
        }
        else{
            price = getCostAfterTaxes(car.getPrice());
        }
        // Process car purchase
        // Update user's cars purchased
        updateUserData(user, true, price, 1);
        // Update cars available
        updateStock(car, true, 1);
        // Record transaction
        Ticket returnTicket = new Ticket(tickets.size()+1, user.getFirstName(), user.getLastName(), user.getUsername(), car.getId(), car.getModel(), car.getCarType(), car.getYear(), car.getPrice(), price, new Date(), false);
        tickets.put(tickets.size()+1, returnTicket);
        FileHandler.updateTicketFile("resources/ticket_data.csv", tickets, ticketHeaderIndexMap); 
        logsLinkedList.add(new Log("User " + user.getUsername() + " ","Purchased a car."));
    }

    
    /** 
     * User interface for returnCar.
     * @param user
     */
    public void returnCarUi(User user) {    
        HashMap<Integer, Ticket> userCars = getUserCars(user.getUsername());
        if(userCars.size()==0){
            System.out.println("User does not have any Cars to return.");
            return;
        }
        System.out.println("Which car would you like to return?");
        viewUserCars(user.getUsername());
        System.out.println("Please enter a ticket Id from the above list:");

        Scanner scanner = new Scanner(System.in);
        if(scanner.hasNextInt()){
            int input = scanner.nextInt();
            if(userCars.get(input)!= null){
                returnCar(user, userCars.get(input));
            }
            else{
                System.out.println("Invalid Ticket ID for return.");
            }
        }
    }

    /** 
     * Takes user and ticket objects and processes a car return.
     * @param user
     * @param ticket
     */
    public void returnCar(User user, Ticket ticket) {
        Car car = cars.get(Integer.parseInt(ticket.getCarId()));
        // Process car return
        // Update user's cars purchased
        updateUserData(user, false, ticket.getFinalPrice(), 1);
        // Update cars available
        updateStock(car, false, 1);
        // Record transaction
        Ticket returnTicket = new Ticket(tickets.size()+1, ticket.getUserFirstName(), ticket.getUserLastName(), ticket.getUsername(), ticket.getCarId(), ticket.getPurchasedCarModel(), ticket.getCarType(), ticket.getCarYear(), ticket.getOriginalPrice(), ticket.getFinalPrice(), new Date(), true);
        tickets.put(tickets.size()+1, returnTicket);
        FileHandler.updateTicketFile("resources/ticket_data.csv", tickets, ticketHeaderIndexMap); 
        logsLinkedList.add(new Log("User " + user.getUsername() + " ","Returned a car."));
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
        HashMap<Integer, Ticket> userTickets = getUserTransactions(username);
        for (Ticket ticket : userTickets.values()) {
            System.out.println(ticket);
        }
        if (userTickets.size() == 0) {
            System.out.println("No transactions found for the specified user.");
        }
        logsLinkedList.add(new Log("User " + username + " ","Viewed transactions."));
    }
   
    /** 
     * Retrieves all transactions associated with a specific user.
     * @param username The username whose transactions are to be retrieved.
     * @return HashMap<Integer, Ticket>
     */
    public HashMap<Integer, Ticket> getUserTransactions(String username) {
        HashMap<Integer, Ticket> userTickets = new HashMap<>();
        for (int i = 0; i <= tickets.size(); i++){
            if (tickets.get(i) != null && tickets.get(i).getUsername().equals(username)) {
                userTickets.put(i, tickets.get(i));
            }
        }
        return userTickets;
    }

/**
     * Retrieves and displays all cars associated with a specific user. (Removes all purchase and return pairs so Cars that have been returned are not included.)
     *
     * @param username The username whose cars are to be fetched and displayed.
     */
    public void viewUserCars(String username) {
        HashMap<Integer, Ticket> userCars = getUserCars(username);
        for (Ticket ticket : userCars.values()) {
            System.out.println(ticket);
        }
    }

    /** 
     * Retrieves all cars owned by a specific user. (Removes all purchase and return pairs so Cars that have been returned are not included.)
     * @param username The username whose transactions are to be retrieved.
     * @return HashMap<Integer, Ticket>
     */
    public HashMap<Integer, Ticket> getUserCars(String username) {
        HashMap<Integer, Ticket> userTickets = getUserTransactions(username);
        HashMap<Integer, Ticket> userCars = new HashMap<>();
        //for(Ticket ticket : userTickets.values()){
        for (Map.Entry<Integer, Ticket> ticket : userTickets.entrySet()) {
            if(ticket.getValue() != null && ticket.getValue().getTicketStatus().equals("Purchase")){
                boolean isUserCar = true;
                for (Map.Entry<Integer, Ticket> possibleReturn : userTickets.entrySet()) {
                    if(possibleReturn.getValue() != null && possibleReturn.getValue().getCarId().equals(ticket.getValue().getCarId()) && possibleReturn.getValue().getTicketStatus().equals("Return")){
                        possibleReturn.setValue(null);
                        ticket.setValue(null);
                        isUserCar = false;
                        break; 
                    }
                }
                if(isUserCar){
                    userCars.put(ticket.getKey(), ticket.getValue());
                }
            }
        }
        return userCars;
    }
}
