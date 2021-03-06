package com.thyme.mythyme.controllers;

import com.thyme.mythyme.models.Location;
import com.thyme.mythyme.models.User;
import com.thyme.mythyme.repository.LocationRepository;
import com.thyme.mythyme.repository.UserRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AuthenticationController {

    private final UserRepository userDao;

    public AuthenticationController(UserRepository userDao) {
        this.userDao = userDao;
    }

    @GetMapping("/login")
    public String showLoginForm() {
        return "user/login";
    }

    @GetMapping("/route-user")
    public String routeUserBasedOnRole() {
        User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        User userInDB = userDao.getById(currentUser.getId());

        if (userInDB.isAdmin()) {
            return "redirect:/admin/home";
        } else {

            if (userInDB.getLocation() == null) {
                return "redirect:/location";
            }
        }
            return "redirect:/profile";

        }
    }