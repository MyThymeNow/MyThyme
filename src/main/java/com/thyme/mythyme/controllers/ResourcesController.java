//package com.thyme.mythyme.controllers;
//
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.ResponseBody;
//
//@Controller
//public class ResourcesController {
//
//    @Value("${MAPBOXAPIKEY}")
//    private String mapboxKey;
//
//    @Value("${COCKTAILDBAPIKEY}")
//    private String cocktailDBKey;
//
//    @Value("${SPOONACULARAPIKEY}")
//    private String spoonacularKey;
//
//    @GetMapping(path = "/keys.js", produces = "application/javascript")
//    @ResponseBody
//    public String getAPIKeys() {
//        return "const mapboxKey='"+mapboxKey+"'\nconst cocktailDBKey='"+cocktailDBKey+"'\nconst spoonacularKey='"+spoonacularKey+"'";
//    }
//}
