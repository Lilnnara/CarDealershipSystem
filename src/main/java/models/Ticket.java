package main.java.models;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Ticket {
    private int ticketId;
    private String userFirstName;
    private String userLastName;
    private String username;
    private int carId;
    private String purchasedCarModel;
    private int carYear;
    private double originalPrice;
    private double finalPrice;
    private Date purchaseDate;
    private String ticketStatus; // "Purchase" or "Return"

    public Ticket(int ticketId, String userFirstName, String userLastName, String username, int carId,
                  String purchasedCarModel, int carYear, double originalPrice, double finalPrice,
                  Date purchaseDate, boolean isReturn) {
        this.ticketId = ticketId;
        this.userFirstName = userFirstName;
        this.userLastName = userLastName;
        this.username = username;
        this.carId = carId;
        this.purchasedCarModel = purchasedCarModel;
        this.carYear = carYear;
        this.originalPrice = originalPrice;
        this.finalPrice = finalPrice;
        this.purchaseDate = purchaseDate;
        this.ticketStatus = isReturn ? "Return" : "Purchase";
    }

    // Getters
    public int getTicketId() { return ticketId; }
    public String getUserFirstName() { return userFirstName; }
    public String getUserLastName() { return userLastName; }
    public String getUsername() { return username; }
    public int getCarId() { return carId; }
    public String getPurchasedCarModel() { return purchasedCarModel; }
    public int getCarYear() { return carYear; }
    public double getOriginalPrice() { return originalPrice; }
    public double getFinalPrice() { return finalPrice; }
    public Date getPurchaseDate() { return purchaseDate; }
    public String getTicketStatus() { return ticketStatus; }

    // Setters
    public void setTicketId(int ticketId) { this.ticketId = ticketId; }
    public void setUserFirstName(String userFirstName) { this.userFirstName = userFirstName; }
    public void setUserLastName(String userLastName) { this.userLastName = userLastName; }
    public void setUsername(String username) { this.username = username; }
    public void setCarId(int carId) { this.carId = carId; }
    public void setPurchasedCarModel(String purchasedCarModel) { this.purchasedCarModel = purchasedCarModel; }
    public void setCarYear(int carYear) { this.carYear = carYear; }
    public void setOriginalPrice(double originalPrice) { this.originalPrice = originalPrice; }
    public void setFinalPrice(double finalPrice) { this.finalPrice = finalPrice; }
    public void setPurchaseDate(Date purchaseDate) { this.purchaseDate = purchaseDate; }
    public void setTicketStatus(String ticketStatus) { this.ticketStatus = ticketStatus; }

    // toCSV method to output data as a CSV row
    public String toCSV() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd"); // Date format for CSV
        return String.format("\"%d\",\"%s\",\"%s\",\"%s\",%d,\"%s\",%d,%.2f,%.2f,\"%s\",\"%s\"",
                             ticketId, userFirstName, userLastName, username, carId, purchasedCarModel, carYear,
                             originalPrice, finalPrice, sdf.format(purchaseDate), ticketStatus);
    }

    @Override
    public String toString() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd"); // Date formatter for better readability
        String receiptFormat =
            "********************************************\n" +
            "*                  TICKET                  *\n" +
            "********************************************\n" +
            "* Ticket ID: %-29d *\n" +
            "* User Name: %-27s *\n" +
            "* First Name: %-25s *\n" +
            "* Last Name: %-26s *\n" +
            "* Car ID: %-31d *\n" +
            "* Car Model: %-26s *\n" +
            "* Car Year: %-29d *\n" +
            "* Original Price: $%-19.2f *\n" +
            "* Final Price: $%-20.2f *\n" +
            "* Purchase Date: %-23s *\n" +
            "* Ticket Type: %-26s *\n" +
            "********************************************";

        return String.format(receiptFormat,
                             ticketId, username, userFirstName, userLastName, carId, purchasedCarModel, carYear,
                             originalPrice, finalPrice, sdf.format(purchaseDate), ticketStatus);
    }
}
