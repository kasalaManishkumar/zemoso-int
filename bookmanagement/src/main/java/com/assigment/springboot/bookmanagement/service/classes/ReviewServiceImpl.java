package com.assigment.springboot.bookmanagement.service.classes;

import com.assigment.springboot.bookmanagement.dao.ReviewRepository;
import com.assigment.springboot.bookmanagement.entity.Review;
import com.assigment.springboot.bookmanagement.exceptions.MyRuntimeException;
import com.assigment.springboot.bookmanagement.service.interfaces.ReviewService;
import com.assigment.springboot.bookmanagement.userdetails.CustomUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReviewServiceImpl implements ReviewService {
    private ReviewRepository reviewRepository;


    @Autowired
    public ReviewServiceImpl(ReviewRepository theReviewRepository){
        reviewRepository=theReviewRepository;
    }


    @Override
    public List<Review> findAll() {
        return reviewRepository.findAll();
    }

    @Override
    public Review findById(int theId) throws MyRuntimeException {
        Optional<Review> result = reviewRepository.findById(theId);
        Review theReview=null;
        if(result.isPresent()){
            theReview=result.get();
        }
        else{
            throw new MyRuntimeException("did not find review id "+ theId);
        }
        return theReview;
    }

    @Override
    public void save(Review theReview) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            CustomUserDetails principal = (CustomUserDetails) auth.getPrincipal();
            theReview.setUsername(principal.getUsername());

        reviewRepository.save(theReview);

    }

    @Override
    public void deleteById(int theId) {
        reviewRepository.deleteById(theId);
    }
}
