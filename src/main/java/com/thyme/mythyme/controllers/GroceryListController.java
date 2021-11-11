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
    private final UserGroceryListRepository listDao;
    private final GroceryListIngredientsRepository listIngredientsDao;
    private final IngredientRepository ingredientDao;

    public GroceryListController(GroceryListRepository groceryDao, UserGroceryListRepository listDao, UserRepository userDao, GroceryListIngredientsRepository listIngredientsDao, IngredientRepository ingredientDao) {
        this.groceryDao = groceryDao;
        this.userDao = userDao;
        this.listDao = listDao;
        this.listIngredientsDao = listIngredientsDao;
        this.ingredientDao = ingredientDao;
    }

    //////// Index
    @GetMapping("/groceryLists")
    public String showGroceryLists(Model model) {
        User loggedInUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();


//////// VIEWING
        @GetMapping("/groceryLists")
        public String showGroceryLists (Model model){
            User loggedInUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            List<GroceryList> userLists = groceryDao.findByOwner_Id(loggedInUser.getId());
            model.addAttribute("groceryLists", userLists);
            return "groceryList/index";
        }


        @GetMapping("/groceryLists/{id}")
        public String viewSingleGroceryList ( @PathVariable long id, Model model){
            GroceryList groceryList = groceryDao.getById(id);
            model.addAttribute("groceryListId", id);
            model.addAttribute("groceryList", groceryList);
            return "groceryList/show";
        }


///////// Sharing
        @GetMapping("/groceryLists/share/{shareURL}")
        public String viewShareGroceryList (@PathVariable String shareURL, Model model){
            GroceryList listToShare = groceryDao.getByShareURL(shareURL);
            model.addAttribute("groceryListShareURL", shareURL);
            model.addAttribute("listToShare", listToShare);
            return "groceryList/share";
        }

        @PostMapping("/groceryLists/share/{shareURL}")
        public String shareGroceryList (@PathVariable String shareURL, @ModelAttribute GroceryList SharedList){


            return "redirect:/groceryLists";
        }


//////// Creation

        // previously had groceryList/create in parenthesis
        @GetMapping("/create")
        public String showCreateListForm (Model model){
            model.addAttribute("grocery_list", new GroceryList());
            return "groceryList/create";
        }

        @PostMapping("/create") // previously had groceryList/create in parenthesis
        public String saveUserGroceryList (
                @ModelAttribute GroceryList listToCreate,
                @RequestParam(name = "name[]") String[]names,
                @RequestParam(name = "quantity[]") String[]quantities,
                @RequestParam(name = "notes[]") String[]notes
        //@RequestParam (name="status[]") String[] status //todo might be API dependent
    ){
//        GroceryList groceryList = groceryDao.getByShareURL(listToCreate.toString());
            User loggedInUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            UUID uuid = UUID.randomUUID();
            listToCreate.setOwner(loggedInUser);
            listToCreate.setShareURL(uuid.toString());
            GroceryList groceryListInDB = groceryDao.save(listToCreate);
            UserGroceryList newList = new UserGroceryList();
            newList.setUser(loggedInUser);
            newList.setGroceryList(listToCreate);
            listDao.save(newList);


            for (int i = 0; i < names.length; i++) {
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


            return "redirect:/groceryLists";

        }


//////// Editing

        @GetMapping("/groceryLists/edit/{id}")
        public String showEditGroceryListForm ( @PathVariable long id, Model model){
            GroceryList groceryList = groceryDao.getById(id);
            List<GroceryListIngredients> groceryListIngredients = listIngredientsDao.getByGroceryList(groceryList);
            UserGroceryList listToFavorite = listDao.getByGroceryList_Id(id);

            for (GroceryListIngredients item : groceryListIngredients) {
                Long groceryListIngredients_id = item.getId();

                Optional<Ingredient> currentIngredient = ingredientDao.findById(groceryListIngredients_id);

//            System.out.println(currentIngredient);
//            System.out.println(currentQuantity);
//            System.out.println(currentNotes);
//        System.out.println(groceryList.getName());
//        System.out.println(groceryListIngredients);

                model.addAttribute("grocery_list", groceryList);
                model.addAttribute("groceryListIngredients", groceryListIngredients);
                model.addAttribute("currentIngredient", currentIngredient);
                model.addAttribute("isFavorited", listToFavorite.isFavorited());
            }
            return "groceryList/edit";
        }

        @PostMapping("/groceryLists/edit/{id}")
        public String editGroceryList (
                @PathVariable Long id,
                @RequestParam String name,
                @RequestParam(name = "name[]") String[]names,
                @RequestParam(name = "quantity[]") String[]quantities,
                @RequestParam(name = "notes[]") String[]notes
        //@RequestParam (name="status[]") String[] status //todo might be API dependent
    ){
            User loggedInUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            GroceryList listToUpdate = groceryDao.getById(id);
            listToUpdate.setOwner(loggedInUser);
            listToUpdate.setName(name);
            GroceryList updatedList = groceryDao.save(listToUpdate);
            List<GroceryListIngredients> groceryListIngredients = listToUpdate.getGroceryListIngredient();
//        System.out.println(groceryListIngredients); does sout correct number of ingredients

            for (int i = 0; i < groceryListIngredients.size(); i++) {

                GroceryListIngredients groceryListIngredientsToUpdate = groceryListIngredients.get(i);

                groceryListIngredientsToUpdate.setId(groceryListIngredientsToUpdate.getId());

                groceryListIngredientsToUpdate.setQuantity(Long.parseLong(quantities[i]));

                groceryListIngredientsToUpdate.setNotes(notes[i]);

                Ingredient ingredient = groceryListIngredientsToUpdate.getIngredient();

                ingredient.setName(names[i]);

                ingredientDao.save(ingredient);


////            groceryListIngredients.setStatus(status[i]); //todo may be API dependent
                groceryListIngredientsToUpdate.setGroceryList(updatedList);
//            groceryListIngredientsToUpdate.setIngredient(ingredientToUpdate);
                groceryListIngredientsToUpdate.setUser(loggedInUser);
                listIngredientsDao.save(groceryListIngredientsToUpdate);
            }
            return "redirect:/groceryLists";
        }


        @PostMapping("/groceryLists/edit/{id}/favorite")
        public String favoriteList (@PathVariable Long id, Model model){
            GroceryList currentGroceryList = groceryDao.getById(id);
            UserGroceryList listToFavorite = listDao.getByGroceryList(currentGroceryList);
            listToFavorite.setFavorited(true);
            listDao.save(listToFavorite);

            model.addAttribute("isFavorited", !listToFavorite.isFavorited());

            return "redirect:/groceryLists/edit/" + id;
        }

        @PostMapping("/groceryLists/edit/{id}/unfavorite")
        public String unFavoriteList (@PathVariable Long id, Model model){
            GroceryList currentGroceryList = groceryDao.getById(id);
            UserGroceryList listToUnfavorite = listDao.getByGroceryList(currentGroceryList);
            listToUnfavorite.setFavorited(false);
            listDao.save(listToUnfavorite);

//        model.addAttribute("!isFavorited", !listToUnfavorite.isFavorited());

            return "redirect:/groceryLists/edit/" + id;
        }


//////// Deletion

        @PostMapping("/groceryLists/delete/{id}")
        public String deleteGroceryList (@PathVariable Long id){
            GroceryList groceryList = groceryDao.getById(id);
            GroceryList listToDelete = groceryDao.getById(groceryList.getId());
            UserGroceryList userListDelete = listDao.getByGroceryList(groceryList);
            GroceryListIngredients listIngredientsToDelete = listIngredientsDao.getAllByGroceryList_Id(listToDelete.getId());


            listDao.delete(userListDelete);
            listIngredientsDao.delete(listIngredientsToDelete);
            groceryDao.delete(listToDelete);
            return "redirect:/groceryLists";
        }

    }
}

