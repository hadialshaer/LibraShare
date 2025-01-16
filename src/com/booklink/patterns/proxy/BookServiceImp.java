package com.booklink.patterns.proxy;

import com.booklink.models.Book;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BookServiceImp implements BookService {
    private final List<Book> books = new ArrayList<>();
    private final Map<Integer, Boolean> borrowedBooks = new HashMap<>();

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
        if (borrowedBooks.getOrDefault(bookId, false)) {
            System.out.println("Book is already borrowed: " + bookId);
        } else {
            borrowedBooks.put(bookId, true);
            System.out.println("Book borrowed: " + bookId);
        }
    }

    @Override
    public void returnBook(int bookId) {
        if (borrowedBooks.getOrDefault(bookId, false)) {
            borrowedBooks.put(bookId, false);
            System.out.println("Book returned: " + bookId);
        } else {
            System.out.println("Book was not borrowed: " + bookId);
        }
    }
}

