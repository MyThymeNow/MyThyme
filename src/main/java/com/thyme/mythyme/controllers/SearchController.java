package com.thyme.mythyme.controllers;

import com.thyme.mythyme.models.User;
import com.thyme.mythyme.repository.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

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

    }

    @GetMapping("/sign-up")
    public String showSignupForm(Model model){
        model.addAttribute("user", new User());
        return "user/sign-up";
    }

    @PostMapping("/sign-up")
    public String saveUser(@ModelAttribute User user){
        String hash = passwordEncoder.encode(user.getPassword());
        user.setPassword(hash);
        users.save(user);
        return "redirect:/login";
    }
}
