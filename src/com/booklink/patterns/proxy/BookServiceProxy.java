package com.booklink.patterns.proxy;

import com.booklink.models.Book;
import com.booklink.models.User;
import com.booklink.models.UserRole;
import com.booklink.patterns.observer.BookAvailabilityNotifier;

public class BookServiceProxy implements BookService {

    private final BookService realService;
    private final User currentUser;
    private static final BookAvailabilityNotifier notifier = new BookAvailabilityNotifier();

    public BookServiceProxy(BookService realService, User currentUSer) {
        this.realService = realService;
        this.currentUser = currentUSer;
    }
    @Override
    public void addBook(Book book) {
        if (currentUser.getRole().equals(UserRole.ADMIN)) {
            realService.addBook(book);
        } else {
            System.out.println("Access Denied: You are not authorized to add books");
        }
    }

    @Override
    public void removeBook(int bookId) {
        if (currentUser.getRole().equals(UserRole.ADMIN)) {
            realService.removeBook(bookId);
        } else {
            System.out.println("Access Denied: You are not authorized to remove books");
        }
    }

    @Override
    public void borrowBook(int bookId) {
        if(currentUser.getRole().equals(UserRole.ADMIN) || currentUser.getRole().equals(UserRole.MEMBER)){
            realService.borrowBook(bookId);
            System.out.println(currentUser.getFirstName() + " borrowed book: " + bookId);
        }
        else System.out.println("Access Denied: Only members or admin can borrow books.");
    }

    @Override
    public void returnBook(int bookId) {
        if(currentUser.getRole().equals(UserRole.ADMIN) || currentUser.getRole().equals(UserRole.MEMBER)){
            realService.returnBook(bookId);
            System.out.println(currentUser.getFirstName() + " returned book: " + bookId);
            notifier.notifyUsers(bookId); // Notify waiting users
        }
        else System.out.println("Access Denied: Only members or admin can return books.");

    }

    public void subscribeToBook(int bookId) {
        if (currentUser.getRole().equals(UserRole.MEMBER) || currentUser.getRole().equals(UserRole.ADMIN)) {
            notifier.subscribe(bookId, currentUser);
        } else {
            System.out.println("Access Denied: Guests cannot subscribe for book notifications");
        }
    }
}
