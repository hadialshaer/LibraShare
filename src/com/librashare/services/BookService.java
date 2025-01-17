package com.librashare.services;

import com.librashare.models.Book;

public interface BookService {
    void addBook(Book book);
    void removeBook(int bookId);
    void borrowBook(int bookId);
    void returnBook(int bookId);

    //New method to check if a book is borrowed
    boolean isBookBorrowed(int bookId);

}
