package com.thyme.mythyme.controllers;

import com.thyme.mythyme.models.GroceryList;
import com.thyme.mythyme.models.GroceryListIngredients;
import com.thyme.mythyme.models.SpoonacularRequest;
import com.thyme.mythyme.repository.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class PostController {

    private final GroceryListRepository groceryDao;
    private final UserRepository userDao;
    private final UserGroceryListRepository listDao;
    private final GroceryListIngredientsRepository listIngredientsDao;
    private final IngredientRepository ingredientDao;

    public PostController(GroceryListRepository groceryDao, UserGroceryListRepository listDao, UserRepository userDao, GroceryListIngredientsRepository listIngredientsDao, IngredientRepository ingredientDao) {
        this.groceryDao = groceryDao;
        this.userDao = userDao;
        this.listDao = listDao;
        this.listIngredientsDao = listIngredientsDao;
        this.ingredientDao = ingredientDao;
    }

    @PostMapping("/saveIngredients")
    public String saveRecipe(@RequestBody SpoonacularRequest data) {
        System.out.println(data);

//        GroceryList groceryList = groceryDao.getById(recipeId);
//        groceryDao.save(groceryList);
        return "/saveIngredients";
    }

}
