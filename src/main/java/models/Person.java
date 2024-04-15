package main.java.models;

public abstract class Person {
    // Attributes
    private String firstName;
    private String lastName;

    // Constructors
    protected Person(){}

    /**
     * Constructor for Person class.
     * @param firstName The first name of the person.
     * @param lastName The last name of the person.
     */
    protected Person(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    /** 
     * Getter for firstName
     * @return The first name of the person.
     */
    protected String getFirstName() {
        return firstName;
    }

    /** 
     * Setter for firstName
     * @param firstName The first name to set for the person.
     */
    protected void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /** 
     * Getter for lastName
     * @return The last name of the person.
     */
    protected String getLastName() {
        return lastName;
    }

    /** 
     * Setter for lastName
     * @param lastName The last name to set for the person.
     */
    protected void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
