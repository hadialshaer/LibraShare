package com.booklink.patterns.observer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class BookAvailabilityNotifier {
    // Map book ID to list of observers interested in that specific book
    private final Map<Integer, List<Observer>> waitingUsers = new HashMap<>();


    // Users subscribe to a book
    public void subscribe(int bookId, Observer user) {
        List<Observer> observers = waitingUsers.get(bookId);
        if (observers == null) {
            observers = new ArrayList<>();
            waitingUsers.put(bookId, observers);
        }
        observers.add(user);
        System.out.println(user + " subscribed to notifications on book ID: " + bookId);
    }

    // Users unsubscribe from notifications
    public void unsubscribe(int bookId, Observer user) {
        List<Observer> observers = waitingUsers.get(bookId);
        if (observers != null) {
            observers.remove(user);
            System.out.println(user + " unsubscribed from notifications on book ID: " + bookId);
        }
    }

    // Notify users when a book is returned
    public void notifyUsers(int bookId) {
        List<Observer> observers = waitingUsers.get(bookId);
        if (observers != null) {
            for (Observer observer : observers) {
                observer.update("The book '" + bookId + "' is now available!");
            }
            waitingUsers.remove(bookId); // Clear List after notifying
        }
    }
}
