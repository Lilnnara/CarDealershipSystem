package main.java.models;

public class User extends Person {
    // Attributes specific to User
    private int id;
    private double moneyAvailable;
    private int carsPurchased;
    private boolean minecarsMembership;
    private String username;
    private String password;

    /**
     * Constructor for User class.
     * @param id The unique identifier for the user.
     * @param firstName The first name of the user.
     * @param lastName The last name of the user.
     * @param moneyAvailable The amount of money available to the user.
     * @param carsPurchased The number of cars purchased by the user.
     * @param minecarsMembership Indicates whether the user has a Minecars membership.
     * @param username The username of the user.
     * @param password The password of the user.
     */
    public User(int id, String firstName, String lastName, double moneyAvailable, int carsPurchased, boolean minecarsMembership, String username, String password) {
        super(firstName, lastName); // Call to the superclass (Person) constructor
        this.id = id;
        this.moneyAvailable = moneyAvailable;
        this.carsPurchased = carsPurchased;
        this.minecarsMembership = minecarsMembership;
        this.username = username;
        this.password = password;
    }
     
    /** 
     * Getter for id
     * @return The unique identifier of the user.
     */
    public int getId() {
        return id;
    }
   
    /** 
     * Setter for id
     * @param id The unique identifier to set for the user.
     */
    protected void setId(int id) {
        this.id = id;
    }
    
    /**
     * Getter for moneyAvailable
     * @return The amount of money available to the user.
     */
    protected double getMoneyAvailable() {
        return moneyAvailable;
    }

    /**
     * Setter for moneyAvailable
     * @param moneyAvailable The amount of money available to set for the user.
     */
    protected void setMoneyAvailable(double moneyAvailable) {
        this.moneyAvailable = moneyAvailable;
    }

    /**
     * Getter for carsPurchased
     * @return The number of cars purchased by the user.
     */
    protected int getCarsPurchased() {
        return carsPurchased;
    }

    /**
     * Setter for carsPurchased
     * @param carsPurchased The number of cars purchased to set for the user.
     */
    protected void setCarsPurchased(int carsPurchased) {
        this.carsPurchased = carsPurchased;
    }

    /**
     * Getter for minecarsMembership
     * @return True if the user has a Minecars membership, false otherwise.
     */
    protected boolean isMinecarsMembership() {
        return minecarsMembership;
    }

    /**
     * Setter for minecarsMembership
     * @param minecarsMembership The Minecars membership status to set for the user.
     */
    protected void setMinecarsMembership(boolean minecarsMembership) {
        this.minecarsMembership = minecarsMembership;
    }

    /**
     * Getter for username
     * @return The username of the user.
     */
    public String getUsername() {
        return username;
    }

    /**
     * Setter for username
     * @param username The username to set for the user.
     */
    protected void setUsername(String username) {
        this.username = username;
    }

    /**
     * Getter for password
     * @return The password of the user.
     */
    public String getPassword() {
        return password;
    }

    /**
     * Setter for password
     * @param password The password to set for the user.
     */
    protected void setPassword(String password) {
        this.password = password;
    }

    /** 
     * Method to generate a clean String value for User for printing and comparisons.
     * @return A String representation of the User object. 
     */
    public String toString(){
        return "" + id + "," + super.getFirstName() + "," + super.getLastName() + "," + moneyAvailable + "," + carsPurchased + "," + (minecarsMembership ? "True" : "False") + "," + username + "," + password;
    }

    public String ToStringCSV() {
        return id +
                "," + super.getFirstName()+
                "," + super.getLastName() +
                "," + moneyAvailable +
                "," + carsPurchased +
                "," + minecarsMembership +
                "," + username +
                "," + password;
    }
    
    /**
     * Updates the moneyAvailable.  Adds the "amount" value if isIncrease is True, 
     * and subtracts the "amount" value if isIncrease is false as it would then be a withdrawl.
     * @param isIncrease boolean value, true if the money is being deposited in the account.
     * @param amount the amount the money available goes up or down
     */
    public void updateMoneyAvailable(boolean isIncrease, double amount){
        //add the amount to the balance
        if(isIncrease){
            setMoneyAvailable(getMoneyAvailable() + amount);
        }
        //if deposit is false then the transaction is a withdrawl and the amount should be subtracted
        else{
            setMoneyAvailable(getMoneyAvailable() - amount);
        }
    }

    /**
     * Updates the carsPurchased.  Adds the "amount" value if isIncrease is True, 
     * and subtracts the "amount" value if isIncrease is false as it would then be a decrease.
     * @param isIncrease boolean value, true if the number of carsPurchased is increasing.
     * @param amount the amount the carsPurchased is being increased or decreased by.
     */
    public void updateCarsPurchased(boolean isIncrease, int amount){
        //add the amount to the balance
        if(isIncrease){
            setCarsPurchased(getCarsPurchased() + amount);;
        }
        //if deposit is false then the transaction is a withdrawl and the amount should be subtracted
        else{
            setCarsPurchased(getCarsPurchased() - amount);
        }
    }
}

