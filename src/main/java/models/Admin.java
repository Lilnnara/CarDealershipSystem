package main.java.models;

public class Admin{
    // Attributes specific to Admin
    private String code;


    /**
     * Constructor for Admin class, which extends the Person class.
     * @param code The code of the Admin privileges that allow to make changes in system.
     */
    protected Admin(String code) {
        this.code = code;
    }

    /**
     * Default constructor for Admin class.
     */
    public Admin() {
        // Default constructor
    }

    /**
     * Sets the admin's code.
     * @param code The admin's code.
     */
    public void setCode(String code) {
        this.code = code;
    }

    /**
     * Gets the admin's code.
     * @return The admin's code.
     */
    public String getCode() {
        return code;
    }

    /**
     * Adds a new car to the dealership inventory.
     * 
     * @param carType The type of the car (e.g., sedan, SUV, truck).
     * @param model The model of the car.
     * @param condition The condition of the car (e.g., new, used).
     * @param capacity The seating capacity of the car.
     * @param year The manufacturing year of the car.
     * @param color The manufacturing year of the car.
     * @param fuelType The type of fuel the car uses (e.g., gasoline, diesel).
     * @param transmission The type of transmission (e.g., automatic, manual).
     * @param VIN The Vehicle Identification Number of the car.
     * @param price The price of the car.
     * @param carsAvailable The number of cars available in the inventory.
     * @param hasTurbo A boolean indicating whether the car has a turbocharger or not.
     */
    public void addCarToDealership(String carType, String model, String condition, int capacity,
                                   int year, String color, String fuelType, String transmission, String VIN,
                                   double price, int carsAvailable, boolean hasTurbo) {
        // add to database
        // update csv
        
    }

    /**
     * Method to generate a clean String representation of the Admin object for printing and comparisons.
     * @return String value representing the Admin object.
     */
    // @Override
    // public String toString() {
    //     return super.getFirstName() + ", " + super.getLastName();
    // }
}