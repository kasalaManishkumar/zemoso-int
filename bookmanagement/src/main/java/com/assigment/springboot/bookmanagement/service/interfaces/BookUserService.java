package com.assigment.springboot.bookmanagement.service.interfaces;

import com.assigment.springboot.bookmanagement.entity.BookUser;
import com.assigment.springboot.bookmanagement.exceptions.MyRuntimeException;

import java.util.List;

public interface BookUserService{
    public List<BookUser> findAll();
    public BookUser findById(int theId) throws MyRuntimeException;
    public void save(BookUser theBookUser);
    public void deleteById(int theId);
}
