package com.booklink.patterns.proxy;

import com.booklink.models.Book;

import java.util.ArrayList;
import java.util.List;

public class BookServiceImp implements BookService {
    private final List<Book> books = new ArrayList<>();

    @Override
    public void addBook(Book book) {
        books.add(book);
        System.out.println("Added book: " + book.getTitle());
    }

    @Override
    public void removeBook(int bookId) {
        Book removedBook = null;
        var iterator = books.iterator();
        while (iterator.hasNext()) {
            Book b = iterator.next();
            if (b.getId() == bookId) {
                removedBook = b;
                iterator.remove();
                break;
            }
        }

        if (removedBook != null) {
            System.out.println("Removed book: " + removedBook.getTitle());
        } else {
            System.out.println("No book found with ID: " + bookId);
        }
    }


    @Override
    public void borrowBook(int bookId) {
        Book foundBook = null;

        // Search for the book by its ID
        for (Book b : books) {
            if (b.getId() == bookId) {
                foundBook = b;
                break;
            }
        }

        // Check the outcome of the search
        if (foundBook == null) {
            System.out.println("Book not found: " + bookId);
        } else if (!foundBook.isAvailable()) {
            System.out.println("Book found but not available: " + foundBook.getTitle());
        } else {
            foundBook.setAvailable(false);
            System.out.println("Borrowed book: " + foundBook.getTitle());
        }
    }


    @Override
    public void returnBook(int bookId) {
        Book foundBook = null;

        // Search for book by its ID
        for (Book b: books){
            if(b.getId() == bookId){
                foundBook = b;
                break;
            }
        }

        if (foundBook == null){
            System.out.println("Book not found: " + bookId);
        }
        else if (foundBook.isAvailable()){
            System.out.println("Book found but not borrowed: " + foundBook.getTitle());
        }
        else {
            foundBook.setAvailable(true);
            System.out.println("Returned book: " + foundBook.getTitle());
        }
    }
}
