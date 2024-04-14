package main.java.models;

import java.util.Date;

public class Ticket {
    private final User buyer;
    private final Car purchasedCar;
    private final Date purchaseDate;
    private double finalPrice;
    private boolean returned;

    public Ticket(User buyer, Car purchasedCar, double finalPrice) {
        this.buyer = buyer;
        this.purchasedCar = purchasedCar;
        this.purchaseDate = new Date(); // captures the current date and time
        this.finalPrice = finalPrice;
        this.returned = false;
    }

    // Getters and Setters
    public User getBuyer() {
        return buyer;
    }

    public Car getPurchasedCar() {
        return purchasedCar;
    }

    public Date getPurchaseDate() {
        return purchaseDate;
    }

    public double getFinalPrice() {
        return finalPrice;
    }

    public boolean isReturned() {
        return returned;
    }

    public void setReturned(boolean returned) {
        this.returned = returned;
    }

    // To be used when printing the ticket or writing to a CSV
    @Override
    public String toString() {
        return buyer.getUsername() + "," +
               purchasedCar.getId() + "," +
               purchaseDate + "," +
               finalPrice + "," +
               (returned ? "Returned" : "Not Returned");
    }
    
    // Method to generate CSV line for storing tickets
    public String toCSV() {
        return buyer.getId() + "," +
               purchasedCar.getId() + "," +
               purchaseDate.getTime() + "," + // Storing as timestamp for consistency in CSV
               finalPrice + "," +
               (returned ? "True" : "False");
    }
}
