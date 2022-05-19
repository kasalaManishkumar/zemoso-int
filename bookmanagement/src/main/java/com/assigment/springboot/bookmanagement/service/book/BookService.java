package com.assigment.springboot.bookmanagement.service.book;

import com.assigment.springboot.bookmanagement.entity.Book;

import java.util.List;

public interface BookService {
    public List<Book> findAll();
    public Book findById(int theId);
    public void save(Book theBook);
    public void deleteById(int theId);



}
