package main.java.models;

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


    /**
 * Returns the capacity of the car.
 * @return The capacity of the car.
 */
public int getCapacity() {
    return capacity;
}

/**
 * Sets the capacity of the car.
 * @param capacity The capacity of the car to set.
 */
public void setCapacity(int capacity) {
    this.capacity = capacity;
}

/**
 * Returns the type of the car.
 * @return The type of the car.
 */
public String getCarType() {
    return carType;
}

/**
 * Sets the type of the car.
 * @param carType The type of the car to set.
 */
public void setCarType(String carType) {
    this.carType = carType;
}

/**
 * Returns the number of available cars.
 * @return The number of available cars.
 */
public int getCarsAvailable() {
    return carsAvailable;
}

/**
 * Sets the number of available cars.
 * @param carsAvailable The number of available cars to set.
 */
public void setCarsAvailable(int carsAvailable) {
    this.carsAvailable = carsAvailable;
}

/**
 * Returns the condition of the car.
 * @return The condition of the car.
 */
public String getCondition() {
    return condition;
}

/**
 * Sets the condition of the car.
 * @param condition The condition of the car to set.
 */
public void setCondition(String condition) {
    this.condition = condition;
}

/**
 * Returns the color of the car.
 * @return The color of the car.
 */
public String getColor() {
    return color;
}

/**
 * Sets the color of the car.
 * @param color The color of the car to set.
 */
public void setColor(String color) {
    this.color = color;
}

/**
 * Returns the ID of the car.
 * @return The ID of the car.
 */
public String getId() {
    return id;
}

/**
 * Sets the ID of the car.
 * @param id The ID of the car to set.
 */
public void setId(String id) {
    this.id = id;
}

/**
 * Returns the year of the car.
 * @return The year of the car.
 */
public int getYear() {
    return year;
}

/**
 * Sets the year of the car.
 * @param year The year of the car to set.
 */
public void setYear(int year) {
    this.year = year;
}

/**
 * Returns the price of the car.
 * @return The price of the car.
 */
public double getPrice() {
    return price;
}

/**
 * Sets the price of the car.
 * @param price The price of the car to set.
 */
public void setPrice(double price) {
    this.price = price;
}

/**
 * Returns the transmission type of the car.
 * @return The transmission type of the car.
 */
public String getTransmission() {
    return transmission;
}

/**
 * Sets the transmission type of the car.
 * @param transmission The transmission type of the car to set.
 */
public void setTransmission(String transmission) {
    this.transmission = transmission;
}

/**
 * Returns the VIN (Vehicle Identification Number) of the car.
 * @return The VIN of the car.
 */
public String getVin() {
    return vin;
}

/**
 * Sets the VIN (Vehicle Identification Number) of the car.
 * @param vin The VIN of the car to set.
 */
public void setVin(String vin) {
    this.vin = vin;
}

/**
 * Returns the fuel type of the car.
 * @return The fuel type of the car.
 */
public String getFuelType() {
    return fuelType;
}

/**
 * Sets the fuel type of the car.
 * @param fuelType The fuel type of the car to set.
 */
public void setFuelType(String fuelType) {
    this.fuelType = fuelType;
}

/**
 * Returns the model of the car.
 * @return The model of the car.
 */
public String getModel() {
    return model;
}

/**
 * Sets the model of the car.
 * @param model The model of the car to set.
 */
public void setModel(String model) {
    this.model = model;
}

/**
 * Returns whether the car has a turbocharger.
 * @return True if the car has a turbocharger, otherwise false.
 */
public boolean getHasTurbo() {
    return hasTurbo;
}

/**
 * Sets whether the car has a turbocharger.
 * @param hasTurbo True if the car has a turbocharger, otherwise false.
 */
public void setHasTurbo(boolean hasTurbo) {
    this.hasTurbo = hasTurbo;
}


    
    /** 
     * Method to return String value of Car object
     * @return String
     */
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
    
    /** 
     * String to print to CSV
     * @return String
     */
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
