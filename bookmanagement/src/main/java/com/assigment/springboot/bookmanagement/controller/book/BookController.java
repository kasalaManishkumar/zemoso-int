package com.assigment.springboot.bookmanagement.controller.book;

import com.assigment.springboot.bookmanagement.entity.Book;
import com.assigment.springboot.bookmanagement.entity.User;
import com.assigment.springboot.bookmanagement.service.book.BookService;
import com.assigment.springboot.bookmanagement.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/book")
public class BookController {
    @Autowired
    private BookService bookService;

    @Autowired
    private UserService userService;

    @InitBinder
    public void initBinder(WebDataBinder dataBinder){

        StringTrimmerEditor stringTrimmerEditor=new StringTrimmerEditor(true);

        dataBinder.registerCustomEditor(String.class, stringTrimmerEditor);
    }

    @Autowired
    public BookController(BookService theBookService){
        bookService=theBookService;
    }

    @PostMapping("/save")
    public String saveBooks(@Valid @ModelAttribute("book") Book theBook, BindingResult theBindingResult){
        if(theBindingResult.hasErrors()){
            return "book/book-form";
        }
        else{
        bookService.save(theBook);
        return "redirect:/book/list";
    }}

    @GetMapping("/list")
    public String listBooks(Model theModel){
        //getting books from database
        List<Book> theBooks=bookService.findAll();
        theModel.addAttribute("book",theBooks);
        return "book/list-books";
    }

    @GetMapping("/showFormForAdd")
    public String showFormForAdd(Model theModel){
        Book theBook=new Book();
        theModel.addAttribute("book",theBook);
        return "book/book-form";
    }

    @GetMapping("/showFormForUpdate")
    public String showFormForUpdate(@RequestParam("bookId") int theId, Model theModel){
        Book theBook=bookService.findById(theId);
        theModel.addAttribute("book",theBook);
        return "book/book-form";
    }

    @GetMapping("/delete")
    public String delete(@RequestParam("bookId") int theId){
        bookService.deleteById(theId);
        return "redirect:/book/list";
    }

    @GetMapping("/buy")
    public String showFormForQuantityUpdate(@RequestParam("bookId") int theId, Model theModel){
        Book theBook=bookService.findById(theId);
        theModel.addAttribute("book",theBook);
        return "book/quantity-form";
    }






}
