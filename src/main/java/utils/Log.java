package main.java.utils;
import java.time.*;
import java.time.format.DateTimeFormatter;

public class Log {
    //attributes
    private String timestamp; 
    private String eventDescription; 
    private String Username;

    /**
     * Constructor for Log that takes UsernameIn and eventDescriptionIn as parameters and uses the current timre and date value. 
     * @param UsernameIn
     * @param eventDescriptionIn
     */
    protected Log(String UsernameIn, String eventDescriptionIn){
        //generate current date and time and assign as a string to timestamp
        LocalDateTime myDateObj = LocalDateTime.now();
        DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("HH:mm:ss dd-MM-yyyy");
        
        timestamp = myDateObj.format(myFormatObj);
        eventDescription = eventDescriptionIn;
        Username = UsernameIn;
    }

    
    /**
     * Getter for timestamp
     * @return
     */
    protected String getTimestamp() {
        return timestamp;
    }

    /**
     * Setter for timestamp
     * @param timestamp
     */
    protected void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    /**
     * Getter for eventDescription
     * @return
     */
    protected String getEventDescription() {
        return eventDescription;
    }

    /**
     * Setter for eventDescription
     * @param eventDescription
     */
    protected void setEventDescription(String eventDescription) {
        this.eventDescription = eventDescription;
    }

    /**
     * Getter for username
     * @return
     */
    protected String getUsername() {
        return Username;
    }

    /**
     * Setter for username
     * @param username
     */
    protected void setUsername(String username) {
        Username = username;
    }

    /** 
     * This method generates a String value of all the concatenated attributes of the Log file.
     * @return String value of all Log attributes
     */
    public String toString(){
        return timestamp+" - "+Username+" "+eventDescription;
    }
}