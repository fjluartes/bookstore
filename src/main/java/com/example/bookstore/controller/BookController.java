package com.example.bookstore.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;

import com.example.bookstore.model.Book;
import com.example.bookstore.repository.BookRepository;
import com.example.bookstore.utils.BookIdMismatchException;
import com.example.bookstore.utils.BookNotFoundException;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api/books")
public class BookController {
	private final BookRepository bookRepository;

	BookController(BookRepository bookRepository) {
		this.bookRepository = bookRepository;
	}
	
	@GetMapping
	public Iterable<Book> findAll() {
		return bookRepository.findAll();
	}
	
	@GetMapping("/title/{bookTitle}")
	public List<Book> findByTitle(@PathVariable String bookTitle) {
		return bookRepository.findByTitle(bookTitle);
	}
	
	@GetMapping("/author/{bookAuthor}")
	public List<Book> findByAuthor(@PathVariable String bookAuthor) {
		return bookRepository.findByAuthor(bookAuthor);
	}
	
	@GetMapping("/{id}")
	public Book findOne(@PathVariable Long id) {
		return bookRepository.findById(id)
				.orElseThrow(() -> new BookNotFoundException("Book not found"));
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Book create(@RequestBody Book book) {
		return bookRepository.save(book);
	}
	
	@PutMapping("/{id}")
	public Book update(@PathVariable Long id, @RequestBody Book book) {
		if (book.getId() != id) {
			throw new BookIdMismatchException("ID not found");
		}
		bookRepository.findById(id)
			.orElseThrow(() ->  new BookNotFoundException("Book not found"));
		return bookRepository.save(book);
	}
	
	@DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        bookRepository.findById(id);
        bookRepository.deleteById(id);
        log.info("Book deleted successfully, id={}", id);
    }
}
