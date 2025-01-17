package com.booklink.patterns.proxy;

import com.booklink.models.Book;
import java.util.*;

public class BookServiceImp implements BookService {
    private final List<Book> books = new ArrayList<>();
    private final Map<Integer, Boolean> borrowedBooks = new HashMap<>();

    @Override
    public void addBook(Book book) {
        books.add(book);
        borrowedBooks.put(book.getId(), false); // Initialize availability state
        System.out.println("✅ Added book: " + book.getTitle());
    }

    @Override
    public void removeBook(int bookId) {
        Book bookToRemove = findBookById(bookId);
        if (bookToRemove != null) {
            books.remove(bookToRemove);
            borrowedBooks.remove(bookId); // Ensure it's removed from borrow tracking
            System.out.println("✅ Removed book: " + bookToRemove.getTitle());
        } else {
            System.out.println("⚠️No book found with ID: " + bookId);
        }
    }

    @Override
    public void borrowBook(int bookId) {
        if (!isBookAvailable(bookId)) {
            System.out.println("⚠️Cannot borrow. Book with ID " + bookId + " is unavailable.");
            return;
        }
        borrowedBooks.put(bookId, true); // Mark as borrowed
        System.out.println("📖 Book borrowed: " + bookId);
    }

    @Override
    public void returnBook(int bookId) {
        if (!isBookBorrowed(bookId)) {
            System.out.println("⚠️Cannot return. Book with ID " + bookId + " was not borrowed.");
            return;
        }
        borrowedBooks.put(bookId, false); // Mark as available
        System.out.println("📖 Book returned: " + bookId);
    }

    @Override
    public boolean isBookBorrowed(int bookId) {
        return borrowedBooks.getOrDefault(bookId, false);
    }

    // 🔹 Helper method: Check if book exists
    private Book findBookById(int bookId) {
        for (Book book : books) {
            if (book.getId() == bookId) {
                return book;
            }
        }
        return null; // Return null if no book with the given ID was found
    }

    // 🔹 Helper method: Check if book is available
    private boolean isBookAvailable(int bookId) {
        return findBookById(bookId) != null && !isBookBorrowed(bookId);
    }
}