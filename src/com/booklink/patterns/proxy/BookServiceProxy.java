package com.booklink.patterns.proxy;

import com.booklink.models.Book;
import com.booklink.models.User;
import com.booklink.models.UserRole;
import com.booklink.patterns.observer.BookAvailabilityNotifier;

public class BookServiceProxy implements BookService {

    private final BookService realService;
    private final User currentUser;
    private static final BookAvailabilityNotifier notifier = new BookAvailabilityNotifier();

    public BookServiceProxy(BookService realService, User currentUser) {
        this.realService = realService;
        this.currentUser = currentUser;
    }

    @Override
    public void addBook(Book book) {
        if (isAdmin()) {
            realService.addBook(book);
        } else {
            denyAccess("add books");
        }
    }

    @Override
    public void removeBook(int bookId) {
        if (isAdmin()) {
            realService.removeBook(bookId);
        } else {
            denyAccess("remove books");
        }
    }

    @Override
    public void borrowBook(int bookId) {
        if (isMemberOrAdmin()) {
            realService.borrowBook(bookId);
            System.out.println("📖 " + currentUser.getFirstName() + " borrowed book: " + bookId);
        } else {
            denyAccess("borrow books");
        }
    }

    @Override
    public void returnBook(int bookId) {
        if (!isMemberOrAdmin()) {
            denyAccess("return books");
            return;
        }

        if (!realService.isBookBorrowed(bookId)) {
            System.out.println("⚠️Book with ID " + bookId + " was not borrowed. Cannot return.");
            return;
        }

        realService.returnBook(bookId);
        System.out.println("📖 " + currentUser.getFirstName() + " returned book: " + bookId);
        notifier.notifyUsers(bookId); // Notify waiting users
    }

    @Override
    public boolean isBookBorrowed(int bookId) {
        return realService.isBookBorrowed(bookId);
    }

    public void subscribeToBook(int bookId) {
        if (isMemberOrAdmin()) {
            notifier.subscribe(bookId, currentUser);
        } else {
            denyAccess("subscribe for book notifications");
        }
    }

    public void unsubscribeFromBook(int bookId) {
        if (isMemberOrAdmin()) {
            notifier.unsubscribe(bookId, currentUser);
        } else {
            denyAccess("unsubscribe from book notifications");
        }
    }

    // 🔹 Helper method: Check if user is admin
    private boolean isAdmin() {
        return currentUser.getRole() == UserRole.ADMIN;
    }

    // 🔹 Helper method: Check if user is member or admin
    private boolean isMemberOrAdmin() {
        return currentUser.getRole() == UserRole.ADMIN || currentUser.getRole() == UserRole.MEMBER;
    }

    // 🔹 Helper method: Print access denied message
    private void denyAccess(String action) {
        System.out.println("⛔ Access Denied: You are not authorized to " + action);
    }
}