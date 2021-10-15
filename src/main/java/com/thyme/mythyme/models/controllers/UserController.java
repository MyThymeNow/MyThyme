package com.thyme.mythyme.models.controllers;


import com.thyme.mythyme.models.User;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserController {

    private Repository userDao;
//    private PasswordEncoder passwordEncoder;

//    public UserController(UserRepository userDao, PasswordEncoder passwordEncoder) {
//        this.userDao = userDao;
//        this.passwordEncoder = passwordEncoder;
//    }

    @GetMapping("/signup")
    public String signupForm(Model model){
        model.addAttribute("user", new User());
    return "users/register";
    }

//    @PostMapping("signup")
//    public String saveUserInfo(@ModelAttribute User user){
//        String hashedPassword = passwordEncoder.encode(user.getPassword());
//        user.setPassword(hashedPassword);
//        userDao.save(user);
//        return "redirect:/login";
//    }

    @Controller
    public class AuthenticationController {
        @GetMapping("/login")
        public String showLoginForm() {
            return "users/login";
        }
    }
}
