package main.java.factory;

import main.java.models.Ticket;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

public class TicketFactory implements Factory<Ticket> {

    @Override
    public Ticket create(HashMap<String, String> attributes) {
        try {
            int ticketId = Integer.parseInt(attributes.getOrDefault("TicketID", "0"));
            String userFirstName = attributes.getOrDefault("FirstName", "Unknown");
            String userLastName = attributes.getOrDefault("LastName", "Unknown");
            String username = attributes.getOrDefault("Username", "Unknown");
            int carId = Integer.parseInt(attributes.getOrDefault("CarID", "0"));
            String purchasedCarModel = attributes.getOrDefault("CarModel", "Unknown");
            int carYear = Integer.parseInt(attributes.getOrDefault("CarYear", "0"));
            double originalPrice = Double.parseDouble(attributes.getOrDefault("OriginalPrice", "0.0"));
            double finalPrice = Double.parseDouble(attributes.getOrDefault("FinalPrice", "0.0"));
            Date purchaseDate = parseDate(attributes.getOrDefault("PurchaseDate", "1970-01-01"));
            boolean isReturn = "Return".equalsIgnoreCase(attributes.getOrDefault("TicketType", "Purchase"));

            return new Ticket(ticketId, userFirstName, userLastName, username, carId, purchasedCarModel, carYear,
                              originalPrice, finalPrice, purchaseDate, isReturn);
        } catch (NumberFormatException | ParseException e) {
            System.err.println("Error creating ticket with attributes: " + attributes);
            System.err.println("Exception: " + e.getMessage());
            return null;  // Return null if there is an error in parsing integers, doubles, or date
        }
    }

    private Date parseDate(String dateStr) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return sdf.parse(dateStr);
    }
}
