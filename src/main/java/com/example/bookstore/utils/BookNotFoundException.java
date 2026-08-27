package com.example.bookstore.utils;

@SuppressWarnings("serial")
public class BookNotFoundException extends RuntimeException {
    public BookNotFoundException(String message) {
        super(message);
    }
}
