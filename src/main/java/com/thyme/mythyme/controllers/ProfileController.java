package com.thyme.mythyme.controllers;


import com.thyme.mythyme.models.User;
import com.thyme.mythyme.repository.UserRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ProfileController {

    private final UserRepository userDao;

    public ProfileController(UserRepository userDao){
        this.userDao = userDao;
    }

//    @GetMapping("/profile/create")
//    public String createProfile(Model model){
//
//        User loggedInUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//        User user = userDao.findByUsername(loggedInUser.getUsername());
//        if (user.getUsername() != null) {
//            return "redirect:/profile";
//        }
//        user = new User();
//        user.setUser(user);
//
//        model.addAttribute("users", user);
//
//        return "/create-profile";
//    }

//    @PostMapping("/profile/create")
//    public String createdProfile(@ModelAttribute User user) {
//        User loggedInUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//        user = userDao.findByUsername(loggedInUser.getUsername());
//
//        return "redirect:/profile/" + loggedInUser.getId();
//    }

//   @GetMapping("/profile/{id}")
//
//    public String viewProfile(@PathVariable long id, Model model) {
//
//        User currentUser = userDao.getById(id);
//        model.addAttribute("user", currentUser);
//
//        return "users/profile";
//   }

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

