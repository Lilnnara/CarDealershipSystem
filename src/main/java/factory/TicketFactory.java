package main.java.factory;

import main.java.models.Ticket;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

/**
 * Factory class to create instances of Ticket.
 * It handles the conversion of attribute values from a HashMap to construct a Ticket object.
 */
public class TicketFactory implements Factory<Ticket> {

    /**
     * Creates a Ticket object from a map of attributes.
     *
     * @param attributes A HashMap containing String key-value pairs of ticket attributes.
     * @return A Ticket object constructed from the provided attributes or null if an error occurs.
     */
    @Override
    public Ticket create(HashMap<String, String> attributes) {
        try {
            int ticketId = Integer.parseInt(attributes.getOrDefault("TicketID", "0"));
            String userFirstName = attributes.getOrDefault("FirstName", "Unknown");
            String userLastName = attributes.getOrDefault("LastName", "Unknown");
            String username = attributes.getOrDefault("Username", "Unknown");
            String carId = attributes.getOrDefault("CarID", "0"); // Now expecting a String for CarID
            String purchasedCarModel = attributes.getOrDefault("CarModel", "Unknown");
            String carType = attributes.getOrDefault("CarType", "Unknown"); // Added carType attribute
            int carYear = Integer.parseInt(attributes.getOrDefault("CarYear", "0"));
            double originalPrice = Double.parseDouble(attributes.getOrDefault("OriginalPrice", "0.0"));
            double finalPrice = Double.parseDouble(attributes.getOrDefault("FinalPrice", "0.0"));
            Date purchaseDate = parseDate(attributes.getOrDefault("PurchaseDate", "1970-01-01"));
            boolean isReturn = "Return".equalsIgnoreCase(attributes.getOrDefault("TicketType", "Purchase"));

            return new Ticket(ticketId, userFirstName, userLastName, username, carId, purchasedCarModel, carType, carYear,
                              originalPrice, finalPrice, purchaseDate, isReturn);
        } catch (NumberFormatException | ParseException e) {
            System.err.println("Error creating ticket with attributes: " + attributes);
            System.err.println("Exception: " + e.getMessage());
            return null;  // Return null if there is an error in parsing integers, doubles, or date
        }
    }

    /**
     * Parses a date string into a Date object.
     *
     * @param dateStr The date string in "yyyy-MM-dd" format.
     * @return A Date object representing the specified date.
     * @throws ParseException if the date string is not in the expected format.
     */
    private Date parseDate(String dateStr) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return sdf.parse(dateStr);
    }
}
