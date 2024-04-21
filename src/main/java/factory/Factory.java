package main.java.factory;

import java.util.HashMap;

/**
 * Represents a factory interface for creating objects of type {@code T}.
 * This interface defines a generic method for object creation, which takes a map of string attributes
 * and constructs an instance of {@code T}.
 *
 * @param <T> the type of object this factory creates
 */
public interface Factory<T> {

    /**
     * Creates an instance of type {@code T} using the provided attributes.
     * This method takes a {@code HashMap} where keys and values are strings representing
     * object properties and their values. Implementations are responsible for parsing and converting
     * these string values into the appropriate types needed for object construction.
     *
     * @param attributes A {@code HashMap} containing the attributes needed to construct an object of type {@code T}.
     *                   The keys are the names of the attributes, and the values are the string representations of those attributes.
     * @return An instance of {@code T} constructed from the provided attributes, or {@code null} if creation is unsuccessful.
     */
    T create(HashMap<String, String> attributes);
}
