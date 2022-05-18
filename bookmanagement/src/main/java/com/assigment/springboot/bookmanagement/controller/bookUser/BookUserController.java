package com.assigment.springboot.bookmanagement.controller.bookUser;

import com.assigment.springboot.bookmanagement.entity.Book;
import com.assigment.springboot.bookmanagement.entity.BookUser;
import com.assigment.springboot.bookmanagement.entity.User;
import com.assigment.springboot.bookmanagement.service.book.BookService;
import com.assigment.springboot.bookmanagement.service.bookUser.BookUserService;
import com.assigment.springboot.bookmanagement.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.*;

@Controller
@RequestMapping("/bookUser")
public class BookUserController {
    @Autowired
    private BookUserService bookUserService;
    private BookService bookService;
    private UserService userService;

    @InitBinder
    public void initBinder(WebDataBinder dataBinder){

        StringTrimmerEditor stringTrimmerEditor=new StringTrimmerEditor(true);

        dataBinder.registerCustomEditor(String.class, stringTrimmerEditor);
    }
    @Autowired
    public BookUserController(BookUserService theBookUserService,BookService theBookService,UserService theUserService){
        bookUserService=theBookUserService;
        bookService=theBookService;
        userService=theUserService;
    }

    @PostMapping("/save")
    public String saveUserBooks(@Valid @ModelAttribute("book_user") BookUser theBookUser, BindingResult theBindingResult){
        if(theBindingResult.hasErrors()){
            return "bookuser/bookuser-form";
        }
        else{
            Book theBooks=bookService.findById(theBookUser.getBookId());
        if(theBookUser.getQuantity()<theBooks.getQuantity()){
        theBooks.setQuantity(theBooks.getQuantity()-theBookUser.getQuantity());
        bookUserService.save(theBookUser);
        bookService.save(theBooks);}
        return "redirect:/bookUser/list";
    }}

    @GetMapping("/list")
    public String listBookUser(Model theModel){
        //getting employees from database
        List<BookUser> theBookUsers=bookUserService.findAll();
        List<String> a=new ArrayList<>();
        List<String> b=new ArrayList<>();
        List<Integer> c=new ArrayList<>();
        for (int i=0;i<theBookUsers.size();i++){
            a.add(userService.findById(theBookUsers.get(i).getUserId()).getUsername());
            b.add(bookService.findById(theBookUsers.get(i).getBookId()).getBookName());
            c.add(theBookUsers.get(i).getQuantity());
        }
        theModel.addAttribute("a",a);
        theModel.addAttribute("b",b);
        theModel.addAttribute("c",c);
        theModel.addAttribute("book_user",theBookUsers);
        return "bookuser/list-bookuser";
    }
    @GetMapping("/showFormForAdd")
    public String showFormForAdd(Model theModel){
        List<Book> theBooks=bookService.findAll();
        theModel.addAttribute("book",theBooks);
        List<User> theUsers=userService.findAll();
        theModel.addAttribute("user",theUsers);
        BookUser theBookUser=new BookUser();
        theModel.addAttribute("book_user",theBookUser);
        return "bookuser/bookuser-form";
    }

    @GetMapping("/showFormForUpdate")
    public String showFormForUpdate(@RequestParam("book_userId") int theId, Model theModel){
        List<Book> theBooks=bookService.findAll();
        BookUser theBookUser=bookUserService.findById(theId);
        theModel.addAttribute("book_user",theBookUser);
        return "bookuser/bookuser-form";
    }

    @GetMapping("/delete")
    public String delete(@RequestParam("book_userId") int theId){
        bookUserService.deleteById(theId);
        return "redirect:/bookuser/list";
    }



}
