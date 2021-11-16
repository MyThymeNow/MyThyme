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
import org.springframework.web.bind.annotation.*;

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


   @GetMapping("/profile/edit/{id}")
    public String editProfile(@PathVariable Long id, Model model) {
      User loggedInUser = userDao.getById(id);

       model.addAttribute("loggedInUser",loggedInUser);
       model.addAttribute("firstName",loggedInUser.getFirstName());
       model.addAttribute("lastName",loggedInUser.getLastName());
       model.addAttribute("username",loggedInUser.getUsername());
       model.addAttribute("email",loggedInUser.getEmail());
       model.addAttribute("bio",loggedInUser.getBio());


       return "user/edit";
   }

    @PostMapping("/profile/edit/{id}")
    public String editedProfile(@PathVariable Long id,
                                Model model,
                                @RequestParam(name = "firstName") String firstName,
                                @RequestParam(name = "lastName") String lastName,
                                @RequestParam(name = "username") String username,
                                @RequestParam(name = "email") String email,
                                @RequestParam(name = "bio") String bio){
        User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User userToDisplay = userDao.getById(currentUser.getId());
        User userToUpdate = userDao.getById(id);

        userToUpdate.setId(userToUpdate.getId());
        userToUpdate.setFirstName(firstName);
        userToUpdate.setLastName(lastName);
        userToUpdate.setUsername(username);
        userToUpdate.setEmail(email);
        userToUpdate.setBio(bio);
        userDao.save(userToUpdate);

        model.addAttribute("user", userToDisplay);
        return "redirect:/profile";
    }



}

