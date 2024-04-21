package main.java.handlers;

import java.util.Scanner;
import main.java.models.Car;
import main.java.models.User;
import main.java.models.Ticket;
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
        logsLinkedList.add(new Log("Admin ","Added a car.")); 
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

    
    
    

    public void removeCar(int carId) {
        // Remove car from HashMap
        // If needed, write changes to the CSV file
        cars.remove(carId);
        System.out.println("car with ID: "+ carId + " removed sucessfully");
        FileHandler.updateCarFile("resources/car_data.csv", cars, carHeaderIndexMap); 
        logsLinkedList.add(new Log("Admin ","Removed a car."));
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
        logsLinkedList.add(new Log("Admin ","Added a user."));
        // scanner.close();
        // RETURN HASHMAP
        return users;
    }

    public void updateStock(Car car, boolean isPurchase, int amount){
        car.updateCarsAvailable(isPurchase, amount);
        FileHandler.updateCarFile("resources/car_data.csv", cars, carHeaderIndexMap); 
    }

    public void updateUserData(User user, boolean isPurchase, double moneyAmount, int carAmount){
        user.updateCarsPurchased(isPurchase, carAmount);
        user.updateMoneyAvailable(isPurchase, moneyAmount);
        FileHandler.updateUserFile("resources/user_data.csv", users, userHeaderIndexMap); 
    }

    public double getMembershipCost(Car car){
        //10% off means cost is 90% of the original aka 0.9
        return 0.9*(car.getPrice());
    }

    public double getCostAfterTaxes(double price){
        // state taxes are 6.25% so an aditional 0.0625 out of 1 so 1.0625 times price
        return 1.0625 * price;
    }
    
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
                    System.out.println("\t Would you like to purchase for the cost of: " + price + "(The member discout of %15 off the asking price?)");
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

    // User functionality
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
        Ticket returnTicket = new Ticket(tickets.size(), user.getFirstName(), user.getLastName(), user.getUsername(), car.getId(), car.getModel(), car.getCarType(), car.getYear(), car.getPrice(), price, new Date(), false);
        tickets.put(tickets.size(), returnTicket);
        logsLinkedList.add(new Log("User " + user.getUsername() + " ","Purchased a car."));
    }

    public void returnCarUi(User user) {    
        HashMap<Integer, Ticket> userTickets = getUserTransactions(user.getUsername());
        if(userTickets.size()==0){
            System.out.println("User does not have any Cars to return.");
            return;
        }
        System.out.println("Which car would you like to return?");
        viewUserTransactions(user.getUsername());
        System.out.println("Please enter a ticket Id from the above list:");

        Scanner scanner = new Scanner(System.in);
        if(scanner.hasNextInt()){
            int input = scanner.nextInt();
            if(userTickets.get(input)!= null){
                returnCar(user, userTickets.get(input));
            }
            else{
                System.out.println("Invalid Ticket ID for return.");
            }
        }
    }

    public void returnCar(User user, Ticket ticket) {
        Car car = cars.get(Integer.parseInt(ticket.getCarId()));
        double price = car.getPrice();
        // Process car return
        // Update user's cars purchased
        updateUserData(user, false, price, 1);
        // Update cars available
        updateStock(car, false, 1);
        // Record transaction
        Ticket returnTicket = new Ticket(tickets.size(), ticket.getUserFirstName(), ticket.getUserLastName(), ticket.getUsername(), ticket.getCarId(), ticket.getPurchasedCarModel(), ticket.getCarType(), ticket.getCarYear(), ticket.getOriginalPrice(), ticket.getFinalPrice(), new Date(), true);
        tickets.put(tickets.size(), returnTicket);
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

    public HashMap<Integer, Ticket> getUserTransactions(String username) {
        HashMap<Integer, Ticket> userTickets = new HashMap<>();
        for (int i = 0; i < tickets.size(); i++){
            if (tickets.get(i).getUsername().equals(username)) {
                userTickets.put(i, tickets.get(i));
            }
        }
        return userTickets;
    }

}
