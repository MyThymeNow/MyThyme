package com.thyme.mythyme.controllers;

import com.thyme.mythyme.models.*;
import com.thyme.mythyme.repository.*;
import org.hibernate.cache.spi.support.AbstractReadWriteAccess;
import org.hibernate.sql.Select;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Controller
public class GroceryListController {

    private final GroceryListRepository groceryDao;
    private final UserRepository userDao;
    private final UserGroceryListRepository userListDao;
    private final GroceryListIngredientsRepository listIngredientsDao;
    private final IngredientRepository ingredientDao;

    public GroceryListController(GroceryListRepository groceryDao, UserGroceryListRepository listDao, UserRepository userDao, GroceryListIngredientsRepository listIngredientsDao ,IngredientRepository ingredientDao) {
        this.groceryDao = groceryDao;
        this.userDao = userDao;
        this.userListDao = listDao;
        this.listIngredientsDao = listIngredientsDao;
        this.ingredientDao = ingredientDao;
    }
////////
    @GetMapping("/groceryLists")
    public String showGroceryLists(Model model) {
        List<GroceryList> allLists = groceryDao.findAll();
        model.addAttribute("groceryLists", allLists);
        return "groceryList/index";
    }


////////
//    @GetMapping("/groceryLists/{shareURL}")
//    public String showOneGroceryList(@PathVariable String shareURL, Model model) {
//        GroceryList groceryList = groceryDao.getByShareURL(shareURL);
//        model.addAttribute("groceryListShareURL", shareURL);
//        model.addAttribute("groceryList", groceryList);
//        return "groceryList/show";
//    }

//    @PostMapping("/groceryLists/favorite/{id}")
//    public String saveFavoriteList(Model model) {
//        User loggedInUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//
//
//        model.addAttribute("user_grocery_list", new UserGroceryList());
//        return "groceryList/index";
//    }



//////// Creation

    // previously had groceryList/create in parenthesis
    @GetMapping("/create")
    public String showCreateListForm(Model model) {
        model.addAttribute("grocery_list", new GroceryList());
        return "groceryList/create";
    }

    @PostMapping("/create") // previously had groceryList/create in parenthesis
    public String saveUserGroceryList(
            @ModelAttribute GroceryList listToCreate,
//            @ModelAttribute UserGroceryList newList,
            @RequestParam String name,
            @RequestParam(name="name[]") String[] names,
            @RequestParam(name="quantity[]") String[] quantities,
            @RequestParam (name="notes[]") String[] notes
            //@RequestParam (name="status[]") String[] status //todo might be API dependent
    ) {
//        GroceryList groceryList = groceryDao.getByShareURL(listToCreate.toString());
        User loggedInUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        UUID uuid = UUID.randomUUID();
        listToCreate.setOwner(loggedInUser);
        listToCreate.setShareURL(uuid.toString());
        GroceryList groceryListInDB = groceryDao.save(listToCreate);

        for(int i = 0; i < names.length; i++) {
            Ingredient ingredientInDB = ingredientDao.getByName(names[i]);
            if (ingredientInDB == null) {

            Ingredient ingredient = new Ingredient();
            ingredient.setName(names[i]);
            ingredientInDB = ingredientDao.save(ingredient);
            }


            GroceryListIngredients groceryListIngredients = new GroceryListIngredients();
            groceryListIngredients.setQuantity(Long.valueOf(quantities[i]));
            groceryListIngredients.setNotes(notes[i]);
//            groceryListIngredients.setStatus(status[i]); //todo may be API dependent
            groceryListIngredients.setGroceryList(groceryListInDB);
            groceryListIngredients.setIngredient(ingredientInDB);
            groceryListIngredients.setUser(loggedInUser);
            listIngredientsDao.save(groceryListIngredients);

        }

//        newList.setUser(loggedInUser);
//        newList.setGroceryList(listToCreate);
//        userListDao.save(newList);
        return"redirect:/groceryLists";

    }


//////// Editing

    @GetMapping("/groceryLists/edit/{id}")
    public String showEditGroceryListForm(@PathVariable long id,Model model) {
        GroceryList groceryList = groceryDao.getById(id);
        List<GroceryListIngredients> groceryListIngredients = listIngredientsDao.getByGroceryList(groceryList);
//        List<Ingredient> ingredients = ingredientDao.findIngredientsByGroceryListIngredient(groceryListIngredients);
//        Ingredient[] ingredients = groceryList.getGroceryListIngredient().get(0).getIngredient();

        for(GroceryListIngredients item: groceryListIngredients) {


        }

        System.out.println(groceryList.getName());
        System.out.println(groceryListIngredients);
        System.out.println(groceryList.getGroceryListIngredient().get(0).getIngredient());
//        System.out.println(ingredients);

        model.addAttribute("groceryList", groceryList);
//        model.addAttribute("groceryListIngredients", groceryListIngredients);
//        model.addAttribute("ingredients", ingredients);

        return "groceryList/edit";
    }

    @PostMapping("/groceryLists/edit/{shareURL}")
    public String editGroceryList(
            @PathVariable String shareURL,
            @ModelAttribute GroceryList updatedList
    ) {
        User loggedInUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        updatedList.setShareURL(shareURL);
        updatedList.setOwner(loggedInUser);
        groceryDao.save(updatedList);

        return "redirect:/groceryLists";

    }



//////// Deletion
    @PostMapping("/groceryLists/delete/{shareURL}")
    public String deleteGroceryList(@PathVariable String shareURL) {
        GroceryList listToDelete = groceryDao.getByShareURL(shareURL);
        groceryDao.delete(listToDelete);

        return "redirect:/groceryLists";
    }

}
