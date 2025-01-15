package com.booklink.main;
import com.booklink.patterns.singleton.DatabaseConnection;

public class Main {
    public static void main(String[] args) {
        DatabaseConnection db1 = DatabaseConnection.getInstance();
        DatabaseConnection db2 = DatabaseConnection.getInstance();

        // verify both references point to the same instance
        System.out.println("Are both references pointing to the same instance? " + (db1 == db2));

        db1.executeQuery("SELECT * FROM books");
        db2.executeQuery("SELECT * FROM authors");
    }
}