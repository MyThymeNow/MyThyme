package com.thyme.mythyme.models.controllers;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/about")
    public String aboutPage() {
        return "about";
    }
}
