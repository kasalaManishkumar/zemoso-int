package com.assigment.springboot.bookmanagement.controller.review;

import com.assigment.springboot.bookmanagement.entity.Book;
import com.assigment.springboot.bookmanagement.entity.Review;
import com.assigment.springboot.bookmanagement.service.book.BookService;
import com.assigment.springboot.bookmanagement.service.review.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/review")
public class ReviewController {
    @Autowired
    private ReviewService reviewService;

    @Autowired
    private BookService bookService;

    @InitBinder
    public void initBinder(WebDataBinder dataBinder){

        StringTrimmerEditor stringTrimmerEditor=new StringTrimmerEditor(true);

        dataBinder.registerCustomEditor(String.class, stringTrimmerEditor);
    }

    @Autowired
    public ReviewController(ReviewService theReviewService,BookService theBookService){
        reviewService=theReviewService;
        bookService=theBookService;

    }


    @PostMapping("/save")
    public String saveReviews(@Valid @ModelAttribute("review") Review theReview, BindingResult theBindingResult){
        if(theBindingResult.hasErrors()){
            return "review/review-form";
        }
        else{
        reviewService.save(theReview);
        return "redirect:/review/review-list";
    }}

    @GetMapping("/review-list")
    public String listReviews(Model theModel){
        //getting review a from
        List<Book> theBooks=bookService.findAll();
        theModel.addAttribute("book",theBooks);
        List<Review> theReviews=reviewService.findAll();
        theModel.addAttribute("review",theReviews);
        return "review/list-reviews";
    }

    @GetMapping("/showFormForAdd")
    public String showFormForAdd(Model theModel){
        List<Book> theBooks=bookService.findAll();
        theModel.addAttribute("book",theBooks);
        Review theReview=new Review();
        theModel.addAttribute("review",theReview);
        return "review/review-form";
    }

    @GetMapping("/showFormForUpdate")
    public String showFormForUpdate(@RequestParam("reviewId") int theId, Model theModel){
        List<Book> theBooks=bookService.findAll();
        theModel.addAttribute("book",theBooks);
        Review theReview=reviewService.findById(theId);
        theModel.addAttribute("review",theReview);
        return "review/review-form";
    }

    @GetMapping("/delete")
    public String delete(@RequestParam("reviewId") int theId){
        reviewService.deleteById(theId);
        return "redirect:/review/review-list";
    }



}
