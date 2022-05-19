package com.assigment.springboot.bookmanagement.service.interfaces;

import com.assigment.springboot.bookmanagement.entity.User;
import com.assigment.springboot.bookmanagement.exceptions.MyRuntimeException;

import java.util.List;

public interface UserService {
    public List<User> findAll();
    public User findById(int theId) throws MyRuntimeException;
    public void save(User theUser);
    public void deleteById(int theId);
}
