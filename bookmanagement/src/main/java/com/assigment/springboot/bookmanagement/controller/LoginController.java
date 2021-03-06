package com.assigment.springboot.bookmanagement.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {

    @GetMapping("/showMyLoginPage")
    public String showMyLoginPage(){
        return "login";
    }

    @GetMapping("/access-denied")
    public String showAccessDenied(){
        return "access-denied";
    }

    @GetMapping("/")
    public String showHome(){
        return "home";
    }
    @GetMapping("/purchase")
    public String show(){
        return "purchase";
    }

}