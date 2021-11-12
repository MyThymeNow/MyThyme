package com.thyme.mythyme.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CategoriesController {

    @GetMapping("/categories/meat")
    public String showMeatAndSeafood() {
        return "categories/meat";
    }

    @GetMapping("/categories/bread")
    public String showBread() {
        return "categories/bread";
    }

    @GetMapping("/categories/produce")
    public String showProduce() {
        return "categories/produce";
    }

    @GetMapping("/categories/frozen")
    public String showFrozen() {
        return "categories/frozen";
    }

    @GetMapping("/categories/beverages")
    public String showBeverages() {
        return "categories/beverages";
    }

    @GetMapping("/categories/pet")
    public String showPetSupplies() {
        return "categories/pet";
    }




}
