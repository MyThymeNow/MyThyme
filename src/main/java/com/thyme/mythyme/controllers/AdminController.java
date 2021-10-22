package com.thyme.mythyme.controllers;

import com.thyme.mythyme.models.User;
import com.thyme.mythyme.repository.UserRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class AdminController {

    private final UserRepository userDao;
    private final PasswordEncoder passwordEncoder;


    public AdminController(UserRepository userDao, PasswordEncoder passwordEncoder) {
        this.userDao = userDao;
        this.passwordEncoder = passwordEncoder;
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

    @GetMapping("/admin/profile/edit/{id}")
    public String updateUserForm(@PathVariable Long id, Model model) {
        User updatedUser = userDao.getById(id);


        model.addAttribute("user", updatedUser);
        return "admin/edit";
    }

    @PostMapping("/admin/profile/edit/{id}")
    public String updateUser(@ModelAttribute User user) {
        String hash = passwordEncoder.encode(user.getPassword());
        user.setPassword(hash);
            userDao.save(user);

        return "redirect:/admin/home";
    }


}
