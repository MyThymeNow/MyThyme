package com.thyme.mythyme.controllers;


import com.thyme.mythyme.models.User;
import com.thyme.mythyme.repository.UserRepository;
import lombok.Getter;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ProfileController {

    private UserRepository userDao;

    public ProfileController(UserRepository userDao){
        this.userDao = userDao;
    }

    @GetMapping("/profile/create")
    public String createProfile(Model model){

        User loggedInUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = userDao.findByUsername(loggedInUser.getUsername());
        if (user.getUsername() != null) {
            return "redirect:/profile";
        }
        user = new User();
        user.setUser(user);

        model.addAttribute("user", user);

        return "/create-profile";
    }

    @PostMapping("/profile/create")
    public String createdProfile(@ModelAttribute User user) {
        User loggedInUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        user = userDao.findByUsername(loggedInUser.getUsername());

        return "redirect:/profile/" + loggedInUser.getId();
    }


}

