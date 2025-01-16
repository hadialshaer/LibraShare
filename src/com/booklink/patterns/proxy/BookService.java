package com.booklink.patterns.proxy;

import com.booklink.models.Book;

public interface BookService {
    void addBook(Book book);
    void removeBook(int bookId);
    void borrowBook(int bookId);
    void returnBook(int bookId);

}
