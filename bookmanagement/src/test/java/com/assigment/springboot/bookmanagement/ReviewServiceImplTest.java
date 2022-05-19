package com.assigment.springboot.bookmanagement;


import com.assigment.springboot.bookmanagement.dao.ReviewRepository;
import com.assigment.springboot.bookmanagement.entity.Book;
import com.assigment.springboot.bookmanagement.entity.Review;
import com.assigment.springboot.bookmanagement.exceptions.MyRuntimeException;
import com.assigment.springboot.bookmanagement.service.classes.ReviewServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class ReviewServiceImplTest {
    @Mock
    private ReviewRepository reviewRepository;

    @InjectMocks
    private ReviewServiceImpl reviewService;

    @Test
    void findAll() {
        reviewService = new ReviewServiceImpl(reviewRepository);

        List<Review> reviewList = new ArrayList<>();

        Review review1=new Review("verynice", new Book(1));
        Review review2=new Review("excellent",new Book (2));
        reviewList.add(review1);
        reviewList.add(review2);


        when(reviewRepository.findAll()).thenReturn(reviewList);

        List<Review> expectedList = reviewService.findAll();

        assertEquals(2, expectedList.size());
    }

    @Test
    void findById() throws MyRuntimeException {

        when(reviewRepository.findById(1)).thenReturn(Optional.of(new Review( "excellent", new Book(2))));
        Review review = reviewService.findById(1);

        Assertions.assertThat(review.getComment()).isEqualTo("excellent");

        verify(reviewRepository,times(1)).findById(1);

    }

//    @Test
//    void save() {
//        Review review = new Review("excellent",new Book(2));
//        reviewService.save(review);
//        verify(reviewRepository).save(review);
//    }

    @Test
    void deleteByUsername(){
        reviewService.deleteById(1);
        verify(reviewRepository).deleteById(1);
    }

}
