package com.springboot.jpa.services;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.jpa.dao.BookRepository;
import com.springboot.jpa.entities.Book;

@Service
public  class BookService {
    
	@Autowired
	private BookRepository bookRepo;
	
    // get all books
    public List<Book> getAllBooks(){
    	return (List<Book>) this.bookRepo.findAll();
    }
    
    // get book by id
    public Book getBookById(int id){
    	return (Book)this.bookRepo.findById(id);
    }
    
    // add book to list
    public Book addBook( Book book) {
    	return this.bookRepo.save(book);
    }
    
    // delete all books
    public void deleteAllBooks() {
    	this.bookRepo.deleteAll();
    }
    
    // delete book by Id
    public void deleteBookById(int id) {
    	this.bookRepo.deleteById(id);
    }
    
    
    
    // Update by id
    public Book updateBook(Book book , int id) { 
    	return this.bookRepo.save(book);
    }
}
