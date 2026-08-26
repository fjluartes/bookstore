package com.example.bookstore.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.bookstore.model.Book;
import com.example.bookstore.repository.BookRepository;
import com.example.bookstore.utils.BookNotFoundException;

@RestController
@RequestMapping("/api/books")
public class BookController {
	@Autowired
	private BookRepository bookRepository;
	
	@GetMapping
	public Iterable<Book> findAll() {
		return bookRepository.findAll();
	}
	
	@GetMapping("/title/{bookTitle}")
	public List<Book> findByTitle(@PathVariable String bookTitle) {
		return bookRepository.findByTitle(bookTitle);
	}
	
	@GetMapping("/{id}")
	public Book findOne(@PathVariable Long id) {
		return bookRepository.findById(id)
				.orElseThrow(() -> new BookNotFoundException("Book not found"));
	}
}
