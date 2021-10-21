package com.thyme.mythyme.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ListController {

    @GetMapping("/coupons")
    public String groceryCoupons(){
        return "user/coupons";
    }
    }
//}

class HelloController {

    @GetMapping("/hello")
    @ResponseBody
    public String hello() {
        return "Hello from Spring!";
    }
}
