package com.assigment.springboot.bookmanagement.service.interfaces;

import com.assigment.springboot.bookmanagement.entity.Review;

import java.util.List;

public interface ReviewService {
    public List<Review> findAll();
    public Review findById(int theId);
    public void save(Review theReview);
    public void deleteById(int theId);
}
