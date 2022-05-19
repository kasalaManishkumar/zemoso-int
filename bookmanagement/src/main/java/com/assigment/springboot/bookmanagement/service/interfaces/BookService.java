package com.assigment.springboot.bookmanagement.service.interfaces;

import com.assigment.springboot.bookmanagement.entity.Book;
import com.assigment.springboot.bookmanagement.exceptions.MyRuntimeException;

import java.util.List;

public interface BookService {
    public List<Book> findAll();
    public Book findById(int theId) throws MyRuntimeException;
    public void save(Book theBook);
    public void deleteById(int theId);



}
