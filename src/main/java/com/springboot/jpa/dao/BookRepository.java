package com.springboot.jpa.dao;

import org.springframework.data.repository.CrudRepository;

import com.springboot.jpa.entities.Book;

public interface BookRepository extends CrudRepository<Book, Integer>{
    public Book findById(int id);
}
