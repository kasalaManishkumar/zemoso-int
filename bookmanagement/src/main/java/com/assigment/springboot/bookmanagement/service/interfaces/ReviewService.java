package com.assigment.springboot.bookmanagement.service.interfaces;

import com.assigment.springboot.bookmanagement.entity.Review;
import com.assigment.springboot.bookmanagement.exceptions.MyRuntimeException;

import java.util.List;

public interface ReviewService {
    public List<Review> findAll();
    public Review findById(int theId) throws MyRuntimeException;
    public void save(Review theReview);
    public void deleteById(int theId);
}
