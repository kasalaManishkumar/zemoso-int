package com.assigment.springboot.bookmanagement.dao;

import com.assigment.springboot.bookmanagement.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ReviewRepository  extends JpaRepository<Review,Integer> {

}
