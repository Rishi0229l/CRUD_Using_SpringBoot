package com.springboot.jpa.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.jpa.entities.Book;
import com.springboot.jpa.services.BookService;

@RestController
public class BookController {
	
	@Autowired
	private BookService bookService;
    
	
	// Get all books
	@GetMapping("/books")
	public ResponseEntity<List<Book>> getBooks() {
		List<Book> li=this.bookService.getAllBooks(); // automatically convert object to JSON
	    if(li.size()<=0) 
	    	return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		return ResponseEntity.of(Optional.of(li));
	}
	
	
	@GetMapping("/books/{id}")
	public ResponseEntity<Book> getBooksById(@PathVariable("id") int id) {
		Book b=this.bookService.getBookById(id);
		if(b==null)
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
	    return ResponseEntity.of(Optional.of(b));
	}
	
	
	@PostMapping("/books")
	public ResponseEntity<Book> addBook(@RequestBody Book b) {
		Book book=null;
		try {
		    book=this.bookService.addBook(b);
		    return ResponseEntity.status(HttpStatus.CREATED).build();
		}catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}
	
	
	@DeleteMapping("/books")
	public ResponseEntity<Void> deleteAllBooks() {
		this.bookService.deleteAllBooks();
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}
	
	
	@DeleteMapping("/books/{id}")
	public ResponseEntity<Void> deleteBookById(@PathVariable("id") int id) {
		try {
			this.bookService.deleteBookById(id);
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		}catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}		
	}
	
	
	@PutMapping("/books/{id}")
	public ResponseEntity<Book> updateBook(@RequestBody Book book,@PathVariable("id") int id) {
		try{
		    this.bookService.updateBook(book , id);
		    return ResponseEntity.ok().build();
		}catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}
	
}
