package com.thyme.mythyme.controllers;

import com.thyme.mythyme.models.*;
import com.thyme.mythyme.repository.*;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.UUID;

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
        GroceryList groceryList = new GroceryList();
        groceryList.setName(data.getName());
        User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        UUID uuid = UUID.randomUUID();
        groceryList.setOwner(currentUser);
        groceryList.setShareURL(uuid.toString());
        GroceryList groceryListInDB = groceryDao.save(groceryList);
        UserGroceryList newList = new UserGroceryList();
        newList.setUser(currentUser);
        newList.setGroceryList(groceryList);
        listDao.save(newList);

        for (int i = 0; i < data.getIngredients().size(); i++) {
            Ingredient ingredientInDB = ingredientDao.getByName(data.getIngredients().get(i).getName());
            if (ingredientInDB == null) {

                Ingredient ingredient = new Ingredient();
                ingredient.setName(data.getIngredients().get(i).getName());
                ingredientInDB = ingredientDao.save(ingredient);
            }

            GroceryListIngredients groceryListIngredients = new GroceryListIngredients();
            groceryListIngredients.setQuantity(data.getIngredients().get(i).getQuantity().longValue());
            groceryListIngredients.setNotes(data.getIngredients().get(i).getNotes());
            groceryListIngredients.setGroceryList(groceryListInDB);
            groceryListIngredients.setIngredient(ingredientInDB);
            groceryListIngredients.setUser(currentUser);
            listIngredientsDao.save(groceryListIngredients);


            ingredientInDB.setName(data.getIngredients().get(i).getName());
            ingredientInDB.setId(ingredientInDB.getId());
            ingredientDao.save(ingredientInDB);
        }
        return "redirect:/profile";

    }
}
