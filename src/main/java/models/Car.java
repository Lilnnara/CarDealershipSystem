package main.java.models;

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

    // Constructor
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

    public boolean isHasTurbo() {
        return hasTurbo;
    }

    public void setHasTurbo(boolean hasTurbo) {
        this.hasTurbo = hasTurbo;
    }
}

