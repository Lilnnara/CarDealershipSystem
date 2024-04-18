package main.java.factory;

import java.util.HashMap;

public interface Factory<T> {
    T create(HashMap<String, String> attributes);
}
