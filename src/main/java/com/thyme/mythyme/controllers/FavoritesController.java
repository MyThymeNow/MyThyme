package com.thyme.mythyme.controllers;

import com.thyme.mythyme.models.*;
import com.thyme.mythyme.repository.*;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;

@Controller
public class FavoritesController {

    private final GroceryListRepository groceryDao;
    private final UserRepository userDao;
    private final UserGroceryListRepository listDao;
    private final GroceryListIngredientsRepository listIngredientsDao;
    private final IngredientRepository ingredientDao;

    public FavoritesController(GroceryListRepository groceryDao, UserGroceryListRepository listDao, UserRepository userDao, GroceryListIngredientsRepository listIngredientsDao , IngredientRepository ingredientDao) {
        this.groceryDao = groceryDao;
        this.userDao = userDao;
        this.listDao = listDao;
        this.listIngredientsDao = listIngredientsDao;
        this.ingredientDao = ingredientDao;
    }

    @GetMapping("/user/favorites")
    public String showFavorites (Model model) {
        User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        List<GroceryList> userLists = groceryDao.findByOwner_Id(currentUser.getId());
        List<UserGroceryList> favoriteLists = listDao.getByFavorited(true);


        model.addAttribute("favorites", favoriteLists);

        return "user/favorites";
    }

    @GetMapping("/groceryLists/{shareURL}")
    public String showOneGroceryList(@PathVariable String shareURL, Model model) {
        GroceryList groceryList = groceryDao.getByShareURL(shareURL);
        List<GroceryListIngredients> groceryListIngredients = listIngredientsDao.getByGroceryList(groceryList);
        for(GroceryListIngredients item : groceryListIngredients) {
            Long groceryListIngredients_id = item.getId();

            Optional<Ingredient> currentIngredient = ingredientDao.findById(groceryListIngredients_id);


            model.addAttribute("groceryList", groceryList);
            model.addAttribute("groceryListIngredients", groceryListIngredients);
            model.addAttribute("currentIngredient", currentIngredient);

        }
        return "groceryList/show";
    }
}
