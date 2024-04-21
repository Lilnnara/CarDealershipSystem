package main.java.ui;

/**
 * Represents a generic menu interface for user interaction in the application.
 * This interface ensures that all menu types provide a standard approach for displaying options,
 * handling user selections, and managing login processes.
 */
public interface Menu {

    /**
     * Displays the menu options to the user.
     * This method should print all available choices specific to the menu implementation,
     * allowing the user to understand what actions can be taken.
     */
    void displayOptions();

    /**
     * Handles the user's selection from the menu options.
     * This method should include logic to interpret and respond to user input,
     * triggering actions or further navigation within the application.
     */
    void handleSelection();

    /**
     * Manages the login process for the menu.
     * This method should prompt the user for authentication credentials,
     * handle their input, and authenticate the user according to the menu's specific requirements.
     */
    void login();
}
