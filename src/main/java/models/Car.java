package main.java.models;
import java.util.HashMap;

/**
 * Represents a car in a dealership system.
 * This class stores information about a specific car model, including its specifications and availability.
 */
public class Car {
    private int capacity;
    private String carType;
    private int carsAvailable;
    private String condition;
    private String color;
    private String id;
    private int year;
    private double price;
    private String transmission;
    private String vin;
    private String fuelType;
    private String model;
    private boolean hasTurbo;

    /**
     * Constructs a new Car instance with all necessary details including turbo information.
     * 
     * @param capacity the seating capacity of the car
     * @param carType the type of the car (e.g., SUV, sedan)
     * @param carsAvailable the number of units available
     * @param condition the condition of the car (new or used)
     * @param color the color of the car
     * @param id a unique identifier for the car
     * @param year the manufacture year of the car
     * @param price the price of the car
     * @param transmission the type of transmission (automatic or manual)
     * @param vin the Vehicle Identification Number
     * @param fuelType the type of fuel the car uses (e.g., gasoline, diesel)
     * @param model the model name of the car
     * @param hasTurbo whether the car has a turbo feature
     */
    public Car(int capacity, String carType, int carsAvailable, String condition, String color, String id, int year,
               double price, String transmission, String vin, String fuelType, String model, boolean hasTurbo) {
        this.capacity = capacity;
        this.carType = carType;
        this.carsAvailable = carsAvailable;
        this.condition = condition;
        this.color = color;
        this.id = id;
        this.year = year;
        this.price = price;
        this.transmission = transmission;
        this.vin = vin;
        this.fuelType = fuelType;
        this.model = model;
        this.hasTurbo = hasTurbo;
    }


    // Getters and setters
    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public String getCarType() {
        return carType;
    }

    public void setCarType(String carType) {
        this.carType = carType;
    }

    public int getCarsAvailable() {
        return carsAvailable;
    }

    public void setCarsAvailable(int carsAvailable) {
        this.carsAvailable = carsAvailable;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getTransmission() {
        return transmission;
    }

    public void setTransmission(String transmission) {
        this.transmission = transmission;
    }

    public String getVin() {
        return vin;
    }

    public void setVin(String vin) {
        this.vin = vin;
    }

    public String getFuelType() {
        return fuelType;
    }

    public void setFuelType(String fuelType) {
        this.fuelType = fuelType;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public boolean getHasTurbo() { return hasTurbo; }
    public void setHasTurbo(boolean hasTurbo) { this.hasTurbo = hasTurbo; }


    @Override
    public String toString() {
        return "Car ID: " + id +
               "\nCar Type: " + carType +
               "\nCapacity: " + capacity +
               "\nAvailable Units: " + carsAvailable +
               "\nCondition: " + condition +
               "\nColor: " + color +
               "\nYear: " + year +
               "\nPrice: $" + price +
               "\nTransmission: " + transmission +
               "\nVIN: " + vin +
               "\nFuel Type: " + fuelType +
               "\nModel: " + model +
               "\nHas Turbo: " + (hasTurbo ? "Yes" : "No") +
               "\n-----------------------------------------";
               
    }
    
    public String toStringCSV() {
        return capacity +
                "," + carType +
                "," + carsAvailable +
                "," + condition +
                "," + color +
                "," + id +
                "," + year +
                "," + price +
                "," + transmission +
                "," + vin +
                "," + fuelType +
                "," + model +
                "," + hasTurbo;
    }
    /**
     * Outputs the Car object as a String of it's attributes in the order provided 
     * by a HashMap containing attribute headers.
     *
     * @param attributes A HashMap containing the headerIndexMap
     * @return A String of the attribute values in order
     */
    public String toStringDynamic(HashMap<String, Integer> headerIndexMap) {
	    String[] attributes = new String[headerIndexMap.size()];
        //use getOrDefault to order the attributes for printing as well and have in a separate method??
        attributes[headerIndexMap.get("Capacity")] = "" + getCapacity();
        attributes[headerIndexMap.get("Car Type")] = getCarType();
        attributes[headerIndexMap.get("Cars Available")] = "" + getCarsAvailable();
        attributes[headerIndexMap.get("Condition")] = getCondition();
        attributes[headerIndexMap.get("Color")] = getColor();
        attributes[headerIndexMap.get("ID")] = getId();
        attributes[headerIndexMap.get("Year")] = "" + getYear();
        attributes[headerIndexMap.get("Price")] = "" + getPrice();
        attributes[headerIndexMap.get("Transmission")] = getTransmission();
        attributes[headerIndexMap.get("VIN")] = getVin();
        attributes[headerIndexMap.get("Fuel Type")] = getFuelType();
        attributes[headerIndexMap.get("Model")] = getModel();
        attributes[headerIndexMap.get("hasTurbo")] = "" + getHasTurbo();
        String result = attributes[0];
        for(int i = 1; i < attributes.length; i++){
            result = result + "," + attributes[i];
        }
        return result;
    }
    /**
     * Updates the carsAvailable.  Adds the "amount" value if isIncrease is True, 
     * and subtracts the "amount" value if isIncrease is false as it would then be a decrease.
     * @param isIncrease boolean value, true if the number of carsAvailable is increasing.
     * @param amount the amount the carsAvailable is being increased or decreased by.
     */
    public void updateCarsAvailable(boolean isIncrease, int amount){
        //add the amount to the balance
        if(isIncrease){
            setCarsAvailable(getCarsAvailable() + amount);;
        }
        //if deposit is false then the transaction is a withdrawl and the amount should be subtracted
        else{
            setCarsAvailable(getCarsAvailable() - amount);
        }
    }
}
