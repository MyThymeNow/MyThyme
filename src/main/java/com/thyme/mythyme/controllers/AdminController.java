package com.thyme.mythyme.controllers;

import com.thyme.mythyme.models.User;
import com.thyme.mythyme.repository.UserRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.thymeleaf.util.StringUtils;

import java.util.List;

@Controller
public class AdminController {

    private final UserRepository userDao;


    public AdminController(UserRepository userDao) {
        this.userDao = userDao;
    }

    // Show list of users upon login
    @GetMapping("/admin/home")
    public String adminDashboard(Model model) {

        List<User> usersToShow = userDao.findAll();

        model.addAttribute("users", usersToShow);

        return redirectUser("admin/home");
    }

    public String redirectUser(String originalPath) {
        User loggedInUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User userInDB = userDao.getById(loggedInUser.getId());

        if (!userInDB.isAdmin()) {
            return "redirect:/";
        } else {
            return originalPath;
        }
    }


    @PostMapping("/admin/enable/{id}")
    public String enableUser(
            @PathVariable Long id,
            boolean isLocked){

            userDao.unlockUser(id, isLocked);

        return "redirect:/admin/home";
    }



}
