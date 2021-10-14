package com.thyme.mythyme.models.controllers;


import com.thyme.mythyme.models.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserController {

    @GetMapping("/signup")
    public String signupForm(Model model){
        model.addAttribute("user", new User());
    return "users/register";
    }
}
