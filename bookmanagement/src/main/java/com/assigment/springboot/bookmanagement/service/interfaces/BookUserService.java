package com.assigment.springboot.bookmanagement.service.interfaces;

import com.assigment.springboot.bookmanagement.entity.BookUser;
import java.util.List;

public interface BookUserService{
    public List<BookUser> findAll();
    public BookUser findById(int theId);
    public void save(BookUser theBookUser);
    public void deleteById(int theId);
}
