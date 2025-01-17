package com.booklink.main;

import com.booklink.models.Book;
import com.booklink.models.User;
import com.booklink.patterns.factory.AdminUserFactory;
import com.booklink.patterns.factory.GuestUserFactory;
import com.booklink.patterns.factory.MemberUserFactory;
import com.booklink.patterns.factory.UserFactory;
import com.booklink.services.BookService;
import com.booklink.services.BookServiceImp;
import com.booklink.patterns.proxy.BookServiceProxy;
import com.booklink.patterns.singleton.DatabaseConnection;

public class Main {
    public static void main(String[] args) {

        // ===========================
        // 1️⃣ Test Singleton Pattern
        // ===========================
        System.out.println("\n===== [TEST] Singleton Pattern =====");
        DatabaseConnection db1 = DatabaseConnection.getInstance();
        DatabaseConnection db2 = DatabaseConnection.getInstance();
        System.out.println("Are both references pointing to the same instance? " + (db1 == db2)); // Should print true

        db1.executeQuery("SELECT * FROM books");
        db2.executeQuery("SELECT * FROM authors");

        // ===========================
        // 2️⃣ Test Factory & Builder Pattern
        // ===========================
        System.out.println("\n===== [TEST] Factory & Builder Pattern =====");

        // Create Users
        UserFactory guestFactory = new GuestUserFactory();
        User guest = guestFactory.createUser("Mohammad", "Ammar", "MohammadAmmar@gmail.com", "12345678", "01-01-2000", "Male");
        guest.showPermissions();

        UserFactory memberFactory = new MemberUserFactory();
        User member = memberFactory.createUser("Hussein", "Ammar", "HusseinAmmar@gmail.com", "12312312", "02-02-2000", "Male");
        member.showPermissions();

        UserFactory adminFactory = new AdminUserFactory();
        User admin = adminFactory.createUser("Hadi", "Alshaer", "HadiAlshaer@gmail.com", "99999999", "03-03-2000", "Male");
        admin.showPermissions();

        // ===========================
        // 3️⃣ Test Proxy Pattern
        // ===========================
        System.out.println("\n===== [TEST] Proxy Pattern (Role-Based Access) =====");

        BookService realService = new BookServiceImp();

        // Create Proxies for users
        BookServiceProxy guestProxy = new BookServiceProxy(realService, guest);
        BookServiceProxy memberProxy = new BookServiceProxy(realService, member);
        BookServiceProxy adminProxy = new BookServiceProxy(realService, admin);

        // Admin adds books (✅ Authorized)
        System.out.println("\n--- Admin adds books ---");
        adminProxy.addBook(new Book(2, "Effective Java", "Joshua Bloch", "Addison-Wesley", "Science", "978-0134685991"));
        adminProxy.addBook(new Book(3, "Java Concurrency in Practice", "Brian Goetz", "Addison-Wesley", "Science", "978-0321349606"));
        adminProxy.addBook(new Book(4, "Head First Design Patterns", "Eric Freeman", "O'Reilly Media", "Science", "978-0596007126"));

        // Guest tries to add a book (⛔ Unauthorized)
        System.out.println("\n--- Guest tries to add a book (Should fail) ---");
        guestProxy.addBook(new Book(5, "Clean Code", "Robert C. Martin", "Pearson", "Programming", "978-0132350884"));

        // ===========================
        // 4️⃣ Test Observer Pattern
        // ===========================
        System.out.println("\n===== [TEST] Observer Pattern (Book Return Notifications) =====");

        System.out.println("\n--- Member subscribes for book return notifications on book ID: 3 ---");
        memberProxy.subscribeToBook(3);

        System.out.println("\n--- Another Member subscribes to book ID: 3 ---");
        memberProxy.subscribeToBook(3);

        System.out.println("\n--- Another Member subscribes to book ID: 4 ---");
        memberProxy.subscribeToBook(4);

        // ===========================
        // 5️⃣ Borrow and Return Books
        // ===========================

        // Guest tries to borrow a book (⛔ Unauthorized)
        System.out.println("\n--- Guest tries to borrow a book (Should fail) ---");
        guestProxy.borrowBook(2);

        // Member borrows book (✅ Allowed)
        System.out.println("\n--- Member borrows 'Java Concurrency in Practice' (ID: 3) ---");
        memberProxy.borrowBook(3);

        // Guest tries to subscribe to notifications (⛔ Unauthorized)
        System.out.println("\n--- Guest tries to subscribe for book return notifications (Should fail) ---");
        guestProxy.subscribeToBook(3);

        // Member returns a book (✅ Allowed)
        System.out.println("\n--- Member returns 'Java Concurrency in Practice' (ID: 3) ---");
        memberProxy.returnBook(3); // Notifications should be sent

        // Member unsubscribes from book ID 4 before it is returned
        System.out.println("\n--- Member unsubscribes from book return notifications on book ID: 4 ---");
        memberProxy.unsubscribeFromBook(4);

        // Admin returns book ID 4 (Member should NOT get notified)
        System.out.println("\n--- Admin returns 'Head First Design Patterns' (ID: 4) ---");
        adminProxy.returnBook(4); // Only subscribed users should get notified

        // ===========================
        // 6️⃣ Test Edge Cases
        // ===========================
        System.out.println("\n===== [TEST] Edge Cases =====");

        // Admin tries to remove a book that does not exist (⛔ Should fail)
        System.out.println("\n--- Admin tries to remove a book that does not exist (ID: 10) ---");
        adminProxy.removeBook(10);

        // Member tries to remove a book (⛔ Unauthorized)
        System.out.println("\n--- Member tries to remove a book (Should fail) ---");
        memberProxy.removeBook(3);

        // Member tries to borrow an already borrowed book (⛔ Should fail)
        System.out.println("\n--- Member tries to borrow a book that is already borrowed (Should fail) ---");
        memberProxy.borrowBook(3);

        // Admin removes a book (✅ Authorized)
        System.out.println("\n--- Admin removes 'Effective Java' (ID: 2) ---");
        adminProxy.removeBook(2);
    }
}