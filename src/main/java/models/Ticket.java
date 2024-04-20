package main.java.models;

import java.util.Date;

public class Ticket {
    private final User buyer;
    private final Car purchasedCar;
    private final Date purchaseDate;
    private final double originalPrice;
    private double finalPrice;
    private boolean returned;
    private String ticketStatus; // "Purchase" or "Return"

    public Ticket(User buyer, Car purchasedCar, Date purchaseDate, double finalPrice, boolean isReturn) {
        this.buyer = buyer;
        this.purchasedCar = purchasedCar;
        this.purchaseDate = purchaseDate;
        this.originalPrice = purchasedCar.getPrice();  // Assuming this is the original price of the car
        this.finalPrice = finalPrice;
        this.returned = isReturn;
        this.ticketStatus = isReturn ? "Return" : "Purchase";  // Set ticket status based on the boolean flag
    }

    // Getters
    public User getBuyer() {
        return buyer;
    }

    public Car getPurchasedCar() {
        return purchasedCar;
    }

    public Date getPurchaseDate() {
        return purchaseDate;
    }

    public double getOriginalPrice() {
        return originalPrice;
    }

    public double getFinalPrice() {
        return finalPrice;
    }

    public boolean isReturned() {
        return returned;
    }

    public String getTicketStatus() {
        return ticketStatus;
    }

    // Setters
    public void setReturned(boolean returned) {
        this.returned = returned;
        this.ticketStatus = returned ? "Return" : "Purchase";  // Update the status accordingly
    }

    // Methods for output
    @Override
    public String toString() {
        // Calculate spacing and alignment
        String receiptFormat = "*******************************************\n"
                               + "*                 TICKET                 *\n"
                               + "*******************************************\n"
                               + "Buyer: %-10s (%s %s)\n"
                               + "Car ID: %-15s Model: %-10s Year: %d\n"
                               + "Original Price: $%-10.2f\n"
                               + "Final Price: $%-10.2f\n"
                               + "Purchase Date: %tD\n"
                               + "Status: %-10s\n"
                               + "Returned: %-10s\n"
                               + "*******************************************";
    
        return String.format(receiptFormat,
                             buyer.getUsername(), buyer.getFirstName(), buyer.getLastName(),
                             purchasedCar.getId(), purchasedCar.getModel(), purchasedCar.getYear(),
                             originalPrice,
                             finalPrice,
                             purchaseDate,
                             ticketStatus,
                             returned ? "Yes" : "No");
    }
    

    // public String toCSV() {
    //     return buyer.getId() + "," +
    //            purchasedCar.getId() + "," +
    //            purchaseDate.getTime() + "," +
    //            originalPrice + "," +
    //            finalPrice + "," +
    //            (returned ? "True" : "False") + "," +
    //            ticketStatus;
    // }
}
