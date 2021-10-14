package com.thyme.mythyme.controllers;

import com.thyme.mythyme.models.User;
import com.thyme.mythyme.repository.Users;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserController {
    private final Users userDao;
    private final PasswordEncoder passwordEncoder;

    public UserController(Users userDao, PasswordEncoder passwordEncoder) {
        this.userDao = userDao;
        this.passwordEncoder = passwordEncoder;
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
        userDao.save(user);
        return "redirect:/login";
    }
}
