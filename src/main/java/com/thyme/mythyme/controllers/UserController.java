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

@Controller
public class UserController {
    private final UserRepository users;
    private final PasswordEncoder passwordEncoder;

    public UserController(UserRepository users, PasswordEncoder passwordEncoder) {
        this.users = users;
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
        users.save(user);
        return "redirect:/login";
    }

    @GetMapping("/profile/{id}")
    public String viewProfile(@PathVariable long id, Model model) {
        User currentUser = users.getById(id);

        model.addAttribute("user", currentUser);

        return "user/view-profile"; // user/view-profile
        //redirects do not target profile

    }

    @GetMapping("/profile")
    public String viewProfile(Model model) {
//        User currentUser = users;
        User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        User UserNDB = users.getById(currentUser.getId());
        model.addAttribute("user", UserNDB);

        return "user/view-profile"; //user/view-profile
    }

    //TODO working to link other buttons in navbar... remove at later time
    @GetMapping("/favorites")
    public String viewFavorites(Model model) {
//        User currentUser = users;
        User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        User UserNDB = users.getById(currentUser.getId());
        model.addAttribute("user", UserNDB);

        return "user/favorites"; //user/view-profile
    }

}
