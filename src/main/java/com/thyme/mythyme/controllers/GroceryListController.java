package com.thyme.mythyme.controllers;

import com.thyme.mythyme.models.*;
import com.thyme.mythyme.repository.*;
import org.hibernate.cache.spi.support.AbstractReadWriteAccess;
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
    @GetMapping("/groceryLists/create")
    public String showCreateListForm(Model model) {
        model.addAttribute("grocery_list", new GroceryList());
        return "groceryList/create";
    }

    @PostMapping("/groceryLists/create")
    public String saveUserGroceryList(
            @ModelAttribute GroceryList listToCreate,
            @ModelAttribute UserGroceryList newList,
            @RequestParam String name,
            @RequestParam(name="name[]") String[] names,
            @RequestParam(name="quantity[]") String[] quantities,
            @RequestParam String notes
            //@RequestParam boolean status //todo might be API dependent
    ) {
//        GroceryList groceryList = groceryDao.getByShareURL(listToCreate.toString());

        for(int i = 0; i < names.length; i++) {
            Ingredient ingredient = new Ingredient();
            ingredient.setName(names[i]);
            ingredientDao.save(ingredient);

            GroceryListIngredients groceryListIngredients = new GroceryListIngredients();
            groceryListIngredients.setQuantity(Long.valueOf(quantities[i]));
            groceryListIngredients.setNotes(notes);
//            groceryListIngredients.setStatus(status); //todo may be API dependent
            listIngredientsDao.save(groceryListIngredients);
        }

        User loggedInUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        UUID uuid = UUID.randomUUID();

        newList.setUser(loggedInUser);
        newList.setGroceryList(listToCreate);
        userListDao.save(newList);
        listToCreate.setOwner(loggedInUser);
        listToCreate.setShareURL(uuid.toString());
        groceryDao.save(listToCreate);
        return"redirect:/groceryLists";

    }


//////// Editing

    @GetMapping("/groceryLists/edit/{id}")
    public String showEditGroceryListForm(@PathVariable long id,Model model) {
        GroceryList groceryList = groceryDao.getById(id);
        List<GroceryListIngredients> groceryListIngredients = listIngredientsDao.getByGroceryList(groceryList);
        Ingredient ingredients = ingredientDao.findAllByGroceryLists(groceryList);

//        for(int i=0; i < ingredients.size(); i++) {
//            ingredients.get(i).
//        }
        System.out.println(groceryList.getName());
        System.out.println(groceryListIngredients);
        System.out.println(ingredients);

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
