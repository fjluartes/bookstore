package com.example.bookstore.utils;

public class BookIdMismatchException extends RuntimeException {
    public BookIdMismatchException(String message) {
        super(message);
    }
}
