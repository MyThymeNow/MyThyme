package com.thyme.mythyme.controllers;

import com.thyme.mythyme.models.User;
import com.thyme.mythyme.repository.UserRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class AdminController {

    private final UserRepository userDao;

    public AdminController(UserRepository userDao) {
        this.userDao = userDao;
    }


    // Show list of users upon login
    @GetMapping("/admin/users")
    public String adminHome(Model model) {

        List<User> usersToShow = userDao.findAll();

        model.addAttribute("users", usersToShow);

        return "admin/home";
    }

}
