package com.thyme.mythyme.controllers;


import com.thyme.mythyme.models.GroceryList;
import com.thyme.mythyme.models.GroceryListIngredients;
import com.thyme.mythyme.models.Ingredient;
import com.thyme.mythyme.models.User;
import com.thyme.mythyme.repository.GroceryListRepository;
import com.thyme.mythyme.repository.UserRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;
import java.util.Optional;

@Controller
public class ProfileController {

    private final UserRepository userDao;
    private final GroceryListRepository groceryDao;

    public ProfileController(UserRepository userDao, GroceryListRepository groceryDao){
        this.userDao = userDao;
        this.groceryDao = groceryDao;
    }


   @GetMapping("/profile/edit")
    public String editProfile(Model model) {
      User loggedInUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
      User user = userDao.getById(loggedInUser.getId());

       model.addAttribute("user",user);

       return "user/edit";
   }

    @PostMapping("/profile/edit")
    public String editedProfile(@ModelAttribute User user){

        User loggedInUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//        user  = userDao.findByUsername(loggedInUser.getUsername());

        user.setId(loggedInUser.getId());
        user.setAdmin(loggedInUser.isAdmin());
        user.setPassword(loggedInUser.getPassword());


        userDao.save(user);
        return "redirect:/profile";
    }


}

