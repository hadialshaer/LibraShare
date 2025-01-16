package com.booklink.patterns.proxy;

import com.booklink.models.Book;
import com.booklink.models.User;
import com.booklink.models.UserRole;

public class BookServiceProxy implements BookService {

    private final BookService realService;
    private final User currentUser;

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
        }
        else System.out.println("Access Denied: Only members or admin can borrow books.");
    }

    @Override
    public void returnBook(int bookId) {
        if(currentUser.getRole().equals(UserRole.ADMIN) || currentUser.getRole().equals(UserRole.MEMBER)){
            realService.returnBook(bookId);
        }
        else System.out.println("Access Denied: Only members or admin can return books.");

    }
}
