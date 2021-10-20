package com.thyme.mythyme.controllers;

import com.thyme.mythyme.models.Location;
import com.thyme.mythyme.models.User;
import com.thyme.mythyme.repository.LocationRepository;
import com.thyme.mythyme.repository.UserRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class LocationController {

    private final LocationRepository locationDao;
    private final UserRepository userDao;

    public LocationController(LocationRepository locationDao, UserRepository userDao) {
        this.locationDao = locationDao;
        this.userDao = userDao;
    }

    @GetMapping("/location")
    public String showLocationForm(Model model) {
        model.addAttribute("location", new Location());
        return "user/location";
    }

    @PostMapping("/location")
    public String saveUserLocation(@ModelAttribute Location locationToAdd){

        User loggedInUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        locationToAdd.setUser(loggedInUser);

        locationDao.save(locationToAdd);
        return "redirect:profile";
    }
}
