package com.thyme.mythyme.controllers;

import com.thyme.mythyme.models.User;
import com.thyme.mythyme.repository.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class SearchController {

    private final UserRepository userDao;
    private final IngredientRepository ingredientDao;
    private final UserGroceryListRepository userGroceryDao;
    private final GroceryListRepository groceryListDao;
    private final GroceryListIngredientsRepository groceryListIngredientsDao;


    public SearchController(UserRepository userDao, IngredientRepository ingredientDao, UserGroceryListRepository userGroceryDao, GroceryListRepository groceryListDao, GroceryListIngredientsRepository groceryListIngredientsDao) {
        this.userDao = userDao;
        this.ingredientDao = ingredientDao;
        this.userGroceryDao = userGroceryDao;
        this.groceryListDao = groceryListDao;
        this.groceryListIngredientsDao = groceryListIngredientsDao;
    }

    @GetMapping("/search")
    public String searchApp() {
//            @RequestParam(name="search") String search, Model model) {
//        model.addAttribute("search", search);
        return "user/search";
    }



}
