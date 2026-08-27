package com.example.bookstore.utils;

@SuppressWarnings("serial")
public class BookIdMismatchException extends RuntimeException {
    public BookIdMismatchException(String message) {
        super(message);
    }
}
