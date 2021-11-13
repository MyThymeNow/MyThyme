package com.thyme.mythyme.controllers;

import com.thyme.mythyme.models.GroceryList;
import com.thyme.mythyme.models.User;
import com.thyme.mythyme.repository.GroceryListRepository;
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
public class UserController {
    private final UserRepository users;
    private final PasswordEncoder passwordEncoder;
    private final GroceryListRepository groceryDao;

    public UserController(UserRepository users, PasswordEncoder passwordEncoder, GroceryListRepository groceryDao) {
        this.users = users;
        this.passwordEncoder = passwordEncoder;
        this.groceryDao = groceryDao;
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
    public String viewUserProfile(@PathVariable long id, Model model) {
        User otherUser = users.getById(id);
        List<GroceryList> otherUserLists = groceryDao.findByOwner_Id(otherUser.getId());

        model.addAttribute("user", otherUser);
        model.addAttribute("groceryLists", otherUserLists);

        return "user/view-profile"; // user/view-profile
        //redirects do not target profile

    }

    @GetMapping("/profile")
    public String viewMyProfile(Model model) {
//        User currentUser = users;
        User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User UserNDB = users.getById(currentUser.getId());
        List<GroceryList> allLists = groceryDao.findByOwner_Id(currentUser.getId());
        model.addAttribute("groceryLists", allLists);
        model.addAttribute("currentUser", currentUser);
        model.addAttribute("user", UserNDB);

        return "user/profile"; //user/view-profile
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

    @GetMapping("/recipes")
    public String viewRecipes(Model model) {
    //        User currentUser = users;
        User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        User UserNDB = users.getById(currentUser.getId());
        model.addAttribute("user", UserNDB);

        return "user/recipes"; //user/view-profile
    }

    @GetMapping("/coupons")
    public String viewCoupon(Model model) {
        //        User currentUser = users;
        User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        User UserNDB = users.getById(currentUser.getId());
        model.addAttribute("user", UserNDB);

        return "user/coupons"; //user/view-profile
    }

}
