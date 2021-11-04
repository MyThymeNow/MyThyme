package com.thyme.mythyme.controllers;

import com.thyme.mythyme.models.*;
import com.thyme.mythyme.repository.*;
import org.hibernate.cache.spi.support.AbstractReadWriteAccess;
import org.hibernate.sql.Select;
import org.springframework.data.jpa.repository.Query;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
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
        return"redirect:/groceryLists/index";

    }


//////// Editing

    @GetMapping("/groceryLists/edit/{id}")
    public String showEditGroceryListForm(@PathVariable long id,Model model) {
        GroceryList groceryList = groceryDao.getById(id);
        List<GroceryListIngredients> groceryListIngredients = listIngredientsDao.getByGroceryList(groceryList);

        for(GroceryListIngredients item : groceryListIngredients) {
            Long groceryListIngredients_id = item.getId();
            Long groceryListsIngredients_quantity = item.getQuantity();
            String groceryListIngredients_notes = item.getNotes();

//            boolean groceryListIngredients_status = item.isStatus();

            Optional<Ingredient> currentIngredient = ingredientDao.findById(groceryListIngredients_id);
            GroceryListIngredients currentQuantity = listIngredientsDao.findByQuantity(groceryListsIngredients_quantity);
            GroceryListIngredients currentNotes = listIngredientsDao.findByNotes(groceryListIngredients_notes);

//            System.out.println(currentIngredient);
//            System.out.println(currentQuantity);
//            System.out.println(currentNotes);
//        System.out.println(groceryList.getName());
//        System.out.println(groceryListIngredients);

        model.addAttribute("grocery_list", groceryList);
        model.addAttribute("groceryListIngredients", groceryListIngredients);
        model.addAttribute("currentIngredient", currentIngredient);
        model.addAttribute("currentQuantity", currentQuantity);
        model.addAttribute("currentNotes", currentNotes);
        }
        return "groceryList/edit";
    }

    @PostMapping("/groceryLists/edit/{id}")
    public String editGroceryList(
            @PathVariable Long id,
            @ModelAttribute GroceryList updatedList,
            @RequestParam String name,
            @RequestParam(name="name[]") String[] names,
            @RequestParam(name="quantity[]") String[] quantities,
            @RequestParam (name="notes[]") String[] notes
            //@RequestParam (name="status[]") String[] status //todo might be API dependent
    ) {
        User loggedInUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        updatedList.setId(id);
        updatedList.setOwner(loggedInUser);
        GroceryList updatedListInDB = groceryDao.save(updatedList);

        for (int i = 0; i < names.length; i++) {
            Ingredient ingredientInDB = ingredientDao.findAllByGroceryLists(updatedListInDB);
            if (ingredientInDB == null) {
                Ingredient newIngredient = new Ingredient();
                newIngredient.setName(names[i]);
                ingredientInDB = ingredientDao.save(newIngredient);
            }

            GroceryListIngredients groceryListIngredientsToUpdate = listIngredientsDao.getById(id);
            groceryListIngredientsToUpdate.setQuantity(Long.valueOf(quantities[i]));
            groceryListIngredientsToUpdate.setNotes(notes[i]);
//            groceryListIngredients.setStatus(status[i]); //todo may be API dependent
            groceryListIngredientsToUpdate.setGroceryList(updatedListInDB);
            groceryListIngredientsToUpdate.setIngredient(ingredientInDB);
            groceryListIngredientsToUpdate.setUser(loggedInUser);
            listIngredientsDao.save(groceryListIngredientsToUpdate);
        }
        return "redirect:/groceryLists";
    }


//////// Deletion
    @PostMapping("/groceryLists/delete/{id}")
    public String deleteGroceryList(@PathVariable Long id) {
        GroceryList listToDelete = groceryDao.getById(id);
        groceryDao.delete(listToDelete);

        return "redirect:/groceryLists";
    }

}
