package com.thyme.mythyme.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CategoriesController {

    @GetMapping("/categories/sidedishes")
    public String showSideDishes() {
        return "categories/sidedishes";
    }

    @GetMapping("/categories/appetizers")
    public String showAppetizers() {
        return "categories/appetizers";
    }

    @GetMapping("/categories/maincourse")
    public String showMainCourses() {
        return "categories/maincourse";
    }

    @GetMapping("/categories/bread")
    public String showBreadItems() {
        return "categories/bread";
    }

    @GetMapping("/categories/beverages")
    public String showBeverages() {
        return "categories/beverages";
    }

    @GetMapping("/categories/desserts")
    public String showDesserts() {
        return "categories/desserts";
    }



}
