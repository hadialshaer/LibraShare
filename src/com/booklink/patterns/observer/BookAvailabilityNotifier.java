package com.booklink.patterns.observer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BookAvailabilityNotifier {
    private final Map<Integer, List<Observer>> waitingUsers = new HashMap<>();

    public void subscribe(int bookId, Observer user) {
        if (!waitingUsers.containsKey(bookId)) {
            waitingUsers.put(bookId, new ArrayList<>()); // Ensure key exists
        }
        List<Observer> observers = waitingUsers.get(bookId);
        if (!observers.contains(user)) {
            observers.add(user);
            System.out.println("📢 " + user + " subscribed to notifications on book ID: " + bookId);
        } else {
            System.out.println("⚠️" + user + " is already subscribed to book ID: " + bookId);
        }
    }

    public void unsubscribe(int bookId, Observer user) {
        List<Observer> observers = waitingUsers.get(bookId);
        if (observers != null && observers.remove(user)) {
            System.out.println("📢 " + user + " unsubscribed from book ID: " + bookId);
        }
    }

    public void notifyUsers(int bookId) {
        List<Observer> observers = waitingUsers.get(bookId);
        if (observers == null || observers.isEmpty()) {
            System.out.println("⚠️No subscribers to notify for book ID: " + bookId);
            return;
        }

        System.out.println("🔔 Notifying subscribers: Book ID " + bookId + " is now available!");

        for (Observer observer : observers) {
            observer.update("📖 The book '" + bookId + "' is now available!");
        }
        waitingUsers.remove(bookId);
    }
}