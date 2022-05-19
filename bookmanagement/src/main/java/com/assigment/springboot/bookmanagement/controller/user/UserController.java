package com.assigment.springboot.bookmanagement.controller.user;

import com.assigment.springboot.bookmanagement.entity.User;
import com.assigment.springboot.bookmanagement.exceptions.MyRuntimeException;
import com.assigment.springboot.bookmanagement.service.interfaces.UserService;
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
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;



    @InitBinder
    public void initBinder(WebDataBinder dataBinder){

        StringTrimmerEditor stringTrimmerEditor=new StringTrimmerEditor(true);

        dataBinder.registerCustomEditor(String.class, stringTrimmerEditor);
    }

    @Autowired
    public UserController(UserService theUserService){
        userService=theUserService;
    }

    @PostMapping("/save")
    public String saveUsers(@Valid @ModelAttribute("user") User theUser, BindingResult theBindingResult){
        if(theBindingResult.hasErrors()){
            return "users/user-form";
        }
        else{
        userService.save(theUser);
        return "redirect:/users/list";
    }}

    @GetMapping("/list")
    public String listUsers(Model theModel){
        //getting employees from database
        List<User> theUsers=userService.findAll();
        theModel.addAttribute("users",theUsers);
        return "users/list-users";
    }
    @GetMapping("/showFormForAdd")
    public String showFormForAdd(Model theModel){
        User theUser=new User();
        theModel.addAttribute("user",theUser);
        return "users/user-form";
    }

    @GetMapping("/showFormForUpdate")
    public String showFormForUpdate(@RequestParam("userId") int theId, Model theModel) throws MyRuntimeException {

        User theUser=userService.findById(theId);
        theModel.addAttribute("user",theUser);
        return "users/user-form";
    }

    @GetMapping("/delete")
    public String delete(@RequestParam("userId") int theId){
        userService.deleteById(theId);
        return "redirect:/users/list";
    }



}
