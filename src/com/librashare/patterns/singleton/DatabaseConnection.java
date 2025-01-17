package com.librashare.patterns.singleton;

public class DatabaseConnection {
    private static DatabaseConnection instance;

    // Private constructor to prevent instantiation
    private DatabaseConnection() {
        System.out.println("Database connection established.");
    }

    // Public method to provide global access to the instance
    public static DatabaseConnection getInstance() {
        if (instance == null) {
            synchronized (DatabaseConnection.class) {
                if (instance == null) {
                    instance = new DatabaseConnection();
                }
            }
        }
        return instance;
    }

    public void executeQuery(String query) {
        System.out.println("Executing query: " + query);
    }


}
