package test.java;

import main.java.handlers.FileHandler;
import main.java.handlers.ShopManager;
import main.java.models.Car;
import main.java.models.Ticket;
import main.java.models.User;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedHashMap;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class CarShopTest{
    
    //Test Fixture Variables
    private static HashMap<Integer, Car> cars;
    private static HashMap<String, User> users;
    private static HashMap<Integer, Ticket> tickets;
    private static LinkedHashMap<String, Integer> carHeaderIndexMap;
    private static LinkedHashMap<String, Integer> userHeaderIndexMap;
    private static LinkedHashMap<String, Integer> ticketHeaderIndexMap;
    private static ShopManager shopManager;

    @BeforeAll
    static void setUp(){
        try{
            String carFile = "C:\\Users\\jstar\\Desktop\\OOP Code\\Semester_Project\\Part3\\CarDealershipSystem\\src\\resources\\car_data.csv";
            String userFile = "C:\\Users\\jstar\\Desktop\\OOP Code\\Semester_Project\\Part3\\CarDealershipSystem\\src\\resources\\user_data.csv";
            String ticketFile = "C:\\Users\\jstar\\Desktop\\OOP Code\\Semester_Project\\Part3\\CarDealershipSystem\\src\\test\\java\\testTicket_data.csv"; // Path to the ticket data file

            // Load headers and data
            carHeaderIndexMap = FileHandler.fileHeaderIndex(carFile);
            userHeaderIndexMap = FileHandler.fileHeaderIndex(userFile);
            ticketHeaderIndexMap = FileHandler.fileHeaderIndex(ticketFile); // For tickets

            // Create maps for each type of data
            users = FileHandler.createUserMap(userFile, userHeaderIndexMap);
            cars = FileHandler.createCarMap(carFile, carHeaderIndexMap);
            tickets = FileHandler.createTicketMap(ticketFile, ticketHeaderIndexMap); // For tickets

            // Initialize ShopManager with all data
            shopManager = new ShopManager(cars, users, tickets, carHeaderIndexMap, userHeaderIndexMap, ticketHeaderIndexMap);
        }
        catch(IOException e){
            System.out.println("Error in Test Suite Set Up");
        }
    }

    @Test
    void testPurchaseCar(){
        setUp();
        //assertEquals(carHeaderIndexMap, null);
        //Assumes car and user have enough funds before purchasing
        String[] testUsers = {"wolverine","hansolo","captainjacksparrow"};
        int[] testCarIds = {34,30,25};
        for(int i = 0; i < testUsers.length; i++){
            int carsPurchased = users.get(testUsers[i]).getCarsPurchased();
            double moneyAvailable = users.get(testUsers[i]).getMoneyAvailable();
            int carsAvailable = cars.get(testCarIds[i]).getCarsAvailable();
            Ticket testTicket = shopManager.purchaseCar(users.get(testUsers[i]), cars.get(testCarIds[i]));
            //Check user values updated corectly
            assertEquals(carsPurchased + 1, users.get(testUsers[i]).getCarsPurchased());
            assertEquals(moneyAvailable - testTicket.getFinalPrice(), users.get(testUsers[i]).getMoneyAvailable());
            //Check Car Values updated correctly
            assertEquals(carsAvailable - 1, cars.get(testCarIds[i]).getCarsAvailable());
            System.out.println(testTicket.toCSV());
        }
    }
    
    @Test
    void testReturnCar(){
        setUp();
        //Assumes user and ticket exist before running
        String[] testUsers = {"wolverine","hansolo","captainjacksparrow"};
        // the last 3 tickets should be the tickets to be returned respectively because the tickets are initialized 
        int[] testTicketIDs = {tickets.size()-2,tickets.size()-1,tickets.size()};
        for(int i = 0; i < testUsers.length; i++){
            int carsPurchased = users.get(testUsers[i]).getCarsPurchased();
            double moneyAvailable = users.get(testUsers[i]).getMoneyAvailable();
            int carsAvailable = cars.get(Integer.parseInt(tickets.get(testTicketIDs[i]).getCarId())).getCarsAvailable();
            Ticket testTicket = shopManager.returnCar(users.get(testUsers[i]), tickets.get(testTicketIDs[i]));
            //Check user values updated corectly
            assertEquals(carsPurchased - 1, users.get(testUsers[i]).getCarsPurchased());
            assertEquals(moneyAvailable + testTicket.getFinalPrice(), users.get(testUsers[i]).getMoneyAvailable());
            //Check Car Values updated correctly
            assertEquals(carsAvailable + 1, cars.get(Integer.parseInt(tickets.get(testTicketIDs[i]).getCarId())).getCarsAvailable());
        }
    }

    @Test
    void testRemoveCar(){
        setUp();
        String[] validTestIds = {"34","23"};
        //test valid carIds
        for(int i = 0; i < validTestIds.length; i++){
            //test value exists in cars
            assertNotEquals(cars.get(Integer.parseInt(validTestIds[i])), null);
            //collect initial size
            int initialSize = cars.size();
            //remove
            shopManager.removeCar(validTestIds[i]);
            //test size decreased by 1 after removal
            assertEquals(initialSize-1, cars.size());
            //test value no longer exists in cars
            assertEquals(cars.get(Integer.parseInt(validTestIds[i])), null);
        }
        //test invalid carIds
        String[] invalidTestIds = {"40","khgavs"};
        for(int i = 0; i < invalidTestIds.length; i++){
            //collect initial size
            int initialSize = cars.size();
            //remove car
            shopManager.removeCar(invalidTestIds[i]);
            //test size did not change after removal
            assertEquals(initialSize, cars.size());
        }
    }
}