package com.thyme.mythyme.controllers;

import com.thyme.mythyme.models.User;
import com.thyme.mythyme.repository.UserRepository;
import lombok.Getter;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ListController {
    private final UserRepository userList;

    public ListController(UserRepository userList){
        this.userList = userList;
    }

    @GetMapping("/coupons")
    public String groceryCoupons(){
        return "user/coupons";
    }
}





