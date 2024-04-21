package main.java.models;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Represents a transaction ticket for car purchases or returns in a dealership system.
 * This class holds detailed information about the transaction including the car involved and the financial details.
 */
public class Ticket {
    private int ticketId;
    private String userFirstName;
    private String userLastName;
    private String username;
    private String carId; // Changed from int to String
    private String purchasedCarModel;
    private String carType; // New field to represent the type of car
    private int carYear;
    private double originalPrice;
    private double finalPrice;
    private Date purchaseDate;
    private String ticketStatus; // "Purchase" or "Return"

    /**
     * Constructs a new Ticket instance with all required details.
     *
     * @param ticketId The unique identifier for the ticket.
     * @param userFirstName The first name of the user associated with the ticket.
     * @param userLastName The last name of the user associated with the ticket.
     * @param username The username of the user associated with the ticket.
     * @param carId The unique identifier for the car involved in the transaction.
     * @param purchasedCarModel The model of the car purchased or returned.
     * @param carType The type of the car involved in the transaction.
     * @param carYear The manufacture year of the car.
     * @param originalPrice The original price of the car before any adjustments.
     * @param finalPrice The final price of the car after adjustments.
     * @param purchaseDate The date when the transaction occurred.
     * @param isReturn Indicates whether the ticket represents a return (true) or a purchase (false).
     */
    public Ticket(int ticketId, String userFirstName, String userLastName, String username, String carId,
                  String purchasedCarModel, String carType, int carYear, double originalPrice, double finalPrice,
                  Date purchaseDate, boolean isReturn) {
        this.ticketId = ticketId;
        this.userFirstName = userFirstName;
        this.userLastName = userLastName;
        this.username = username;
        this.carId = carId;
        this.purchasedCarModel = purchasedCarModel;
        this.carType = carType;
        this.carYear = carYear;
        this.originalPrice = originalPrice;
        this.finalPrice = finalPrice;
        this.purchaseDate = purchaseDate;
        this.ticketStatus = isReturn ? "Return" : "Purchase";
    }

    /**
     * Gets the ticket ID.
     * @return the unique identifier for this ticket.
     */
    public int getTicketId() { return ticketId; }

    /**
     * Sets the ticket ID.
     * @param ticketId the unique identifier for this ticket.
     */
    public void setTicketId(int ticketId) { this.ticketId = ticketId; }

    /**
     * Gets the first name of the user associated with this ticket.
     * @return the user's first name.
     */
    public String getUserFirstName() { return userFirstName; }

    /**
     * Sets the first name of the user associated with this ticket.
     * @param userFirstName the user's first name.
     */
    public void setUserFirstName(String userFirstName) { this.userFirstName = userFirstName; }

    /**
     * Gets the last name of the user associated with this ticket.
     * @return the user's last name.
     */
    public String getUserLastName() { return userLastName; }

    /**
     * Sets the last name of the user associated with this ticket.
     * @param userLastName the user's last name.
     */
    public void setUserLastName(String userLastName) { this.userLastName = userLastName; }

    /**
     * Gets the username of the user associated with this ticket.
     * @return the username.
     */
    public String getUsername() { return username; }

    /**
     * Sets the username of the user associated with this ticket.
     * @param username the username.
     */
    public void setUsername(String username) { this.username = username; }

    /**
     * Gets the car ID associated with this ticket.
     * @return the car's unique identifier.
     */
    public String getCarId() { return carId; }

    /**
     * Sets the car ID associated with this ticket.
     * @param carId the car's unique identifier.
     */
    public void setCarId(String carId) { this.carId = carId; }

    /**
     * Gets the model of the car associated with this ticket.
     * @return the car's model.
     */
    public String getPurchasedCarModel() { return purchasedCarModel; }

    /**
     * Sets the model of the car associated with this ticket.
     * @param purchasedCarModel the car's model.
     */
    public void setPurchasedCarModel(String purchasedCarModel) { this.purchasedCarModel = purchasedCarModel; }

    /**
     * Gets the type of the car associated with this ticket.
     * @return the car's type.
     */
    public String getCarType() { return carType; }

    /**
     * Sets the type of the car associated with this ticket.
     * @param carType the car's type.
     */
    public void setCarType(String carType) { this.carType = carType; }

    /**
     * Gets the manufacture year of the car associated with this ticket.
     * @return the car's manufacture year.
     */
    public int getCarYear() { return carYear; }

    /**
     * Sets the manufacture year of the car associated with this ticket.
     * @param carYear the car's manufacture year.
     */
    public void setCarYear(int carYear) { this.carYear = carYear; }

    /**
     * Gets the original price of the car before any adjustments.
     * @return the original price.
     */
    public double getOriginalPrice() { return originalPrice; }

    /**
     * Sets the original price of the car before any adjustments.
     * @param originalPrice the original price.
     */
    public void setOriginalPrice(double originalPrice) { this.originalPrice = originalPrice; }

    /**
     * Gets the final price of the car after adjustments.
     * @return the final price.
     */
    public double getFinalPrice() { return finalPrice; }

    /**
     * Sets the final price of the car after adjustments.
     * @param finalPrice the final price.
     */
    public void setFinalPrice(double finalPrice) { this.finalPrice = finalPrice; }

    /**
     * Gets the date when the transaction was made.
     * @return the purchase date.
     */
    public Date getPurchaseDate() { return purchaseDate; }

    /**
     * Sets the date when the transaction was made.
     * @param purchaseDate the purchase date.
     */
    public void setPurchaseDate(Date purchaseDate) { this.purchaseDate = purchaseDate; }

    /**
     * Gets the status of the ticket (purchase or return).
     * @return the ticket status.
     */
    public String getTicketStatus() { return ticketStatus; }

    /**
     * Sets the status of the ticket (purchase or return).
     * @param ticketStatus the ticket status.
     */
    public void setTicketStatus(String ticketStatus) { this.ticketStatus = ticketStatus; }

    /**
     * Converts the ticket data to a formatted CSV string for storage or reporting purposes.
     * @return A string in CSV format representing the ticket details.
     */
    public String toCSV() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd"); // Date format for CSV
        return (ticketId + "," + userFirstName + "," + userLastName + "," + username + "," + carId + "," + purchasedCarModel + "," + carType + "," + carYear +  "," + 
        originalPrice + "," + finalPrice + "," + sdf.format(purchaseDate) + "," + ticketStatus);
        // return String.format("\"%d\",\"%s\",\"%s\",\"%s\",\"%s\",\"%s\",\"%s\",%d,%.2f,%.2f,\"%s\",\"%s\"",
        //                      ticketId, userFirstName, userLastName, username, carId, purchasedCarModel, carType, carYear,
        //                      originalPrice, finalPrice, sdf.format(purchaseDate), ticketStatus);
    }

    
    /** 
     * Method to return String value of Ticket Object
     * @return String
     */
    @Override
    public String toString() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return String.format(
            "********************************************\n" +
            "*                  TICKET                  *\n" +
            "********************************************\n" +
            "* Ticket ID: %-29d *\n" +
            "* User Name: %-27s *\n" +
            "* First Name: %-25s *\n" +
            "* Last Name: %-26s *\n" +
            "* Car ID: %-31s *\n" + // Updated to handle String ID
            "* Car Model: %-26s *\n" +
            "* Car Type: %-26s *\n" + 
            "* Car Year: %-29d *\n" +
            "* Original Price: $%-19.2f *\n" +
            "* Final Price: $%-20.2f *\n" +
            "* Purchase Date: %-23s *\n" +
            "* Ticket Type: %-26s *\n" +
            "********************************************",
            ticketId, username, userFirstName, userLastName, carId, purchasedCarModel, carType, carYear,
            originalPrice, finalPrice, sdf.format(purchaseDate), ticketStatus);
    }
}
