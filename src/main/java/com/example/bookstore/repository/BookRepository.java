package com.example.bookstore.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import com.example.bookstore.model.Book;

public interface BookRepository extends CrudRepository<Book, Long> {
	List<Book> findByTitle(String title);
	
	List<Book> findByAuthor(String author);
	
	Optional<Book> findById(Long id);
	
	void deleteById(Long id);
}
