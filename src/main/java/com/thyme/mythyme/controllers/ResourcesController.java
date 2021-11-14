package com.thyme.mythyme.controllers;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ResourcesController {

    @Value("{pk.eyJ1IjoibmljLWd1dDIyIiwiYSI6ImNrcjUxdWI2djMxbmUzMXFoeTUweXE4aDgifQ.Clqy7tvpH-B5ma2v43GXUw}")
    private String mapboxKey;

    @Value("{1}")
    private String cocktailDBKey;

    @Value("{fe4d846e30994b3fb65b1adfbfbf342c}")
    private String spoonacularKey;

    @GetMapping(path = "/keys.js", produces = "application/javascript")
    @ResponseBody
    public String getAPIKeys() {
        return "const mapboxKey='" + mapboxKey + "'\nconst cocktailDBKey='" + cocktailDBKey + "'\nconst spoonacularKey='" + spoonacularKey + "'";
    }
}

