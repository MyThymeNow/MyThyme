package com.thyme.mythyme.controllers;

import com.thyme.mythyme.models.*;
import com.thyme.mythyme.repository.*;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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

    //////// VIEWING
    @GetMapping("/groceryLists")
    public String showGroceryLists(Model model) {
        User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        List<GroceryList> allLists = groceryDao.findAll();
        model.addAttribute("groceryLists", allLists);
        model.addAttribute("currentUser", currentUser);
        return "groceryList/index";
    }

    @GetMapping("/groceryLists/{shareURL}")
    public String showOneGroceryList(@PathVariable String shareURL, Model model) {
        GroceryList groceryList = groceryDao.getByShareURL(shareURL);
        List<GroceryListIngredients> groceryListIngredients = listIngredientsDao.getByGroceryListId(groceryList.getId());
        UserGroceryList listToFavorite = listDao.getByGroceryList_ShareURL(shareURL);

        for (GroceryListIngredients item : groceryListIngredients) {
            Long groceryListIngredients_id = item.getId();

            Optional<Ingredient> currentIngredient = ingredientDao.findById(groceryListIngredients_id);

            model.addAttribute("groceryList", groceryList);
            model.addAttribute("groceryListIngredients", groceryListIngredients);
            model.addAttribute("currentIngredient", currentIngredient);
            model.addAttribute("isFavorited", listToFavorite.isFavorited());

        }
        return "groceryList/show";
    }

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
            @RequestParam(name = "name[]") String[] names,
            @RequestParam(name = "quantity[]") String[] quantities,
            @RequestParam(name = "notes[]") String[] notes
            //@RequestParam (name="status[]") String[] status //todo might be API dependent
    ) {
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

            ingredientInDB.setName(names[i]);
            ingredientInDB.setId(ingredientInDB.getId());
            ingredientDao.save(ingredientInDB);

        }
        return "redirect:/groceryLists";
    }


//////// Editing

    @GetMapping("/groceryLists/edit/{id}")
    public String showEditGroceryListForm(@PathVariable long id, Model model) {
        GroceryList groceryList = groceryDao.getById(id);
        List<GroceryListIngredients> groceryListIngredients = listIngredientsDao.getByGroceryList(groceryList);

        for (GroceryListIngredients item : groceryListIngredients) {
            Long groceryListIngredients_id = item.getId();

            Optional<Ingredient> currentIngredient = ingredientDao.findById(groceryListIngredients_id);


            model.addAttribute("grocery_list", groceryList);
            model.addAttribute("groceryListIngredients", groceryListIngredients);
            model.addAttribute("currentIngredient", currentIngredient);

        }
        return "groceryList/edit";
    }

    @PostMapping("/groceryLists/edit/{id}")
    public String editGroceryList(
            @PathVariable Long id,
            @RequestParam String name,
            @RequestParam(name = "id[]") String[] ids,
            @RequestParam(name = "name[]") String[] names,
            @RequestParam(name = "quantity[]") String[] quantities,
            @RequestParam(name = "notes[]") String[] notes
            //@RequestParam (name="status[]") String[] status //todo might be API dependent
    ) {
        User loggedInUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        GroceryList listToUpdate = groceryDao.getById(id);
        listToUpdate.setOwner(loggedInUser);
        listToUpdate.setName(name);
        GroceryList updatedList = groceryDao.save(listToUpdate);
        List<GroceryListIngredients> groceryListIngredients = listToUpdate.getGroceryListIngredients();
//        System.out.println(groceryListIngredients); does sout correct number of ingredients

//        //Loop for editing current items
//        for (int i = 0; i < groceryListIngredients.size(); i++) {
//            GroceryListIngredients ListItemsToUpdate = groceryListIngredients.get(i);
////            ListItemsToUpdate.setId(ListItemsToUpdate.getId());
//            ListItemsToUpdate.setQuantity(Long.parseLong(quantities[i]));
//            ListItemsToUpdate.setNotes(notes[i]);
//            Ingredient ingredient = ListItemsToUpdate.getIngredient();
//            ingredient.setName(names[i]);
//            ingredientDao.save(ingredient);
//
//            ListItemsToUpdate.setGroceryList(updatedList);
//            ListItemsToUpdate.setUser(loggedInUser);
//
//            System.out.println(ListItemsToUpdate);
//
//            listIngredientsDao.save(ListItemsToUpdate);
//        }

        //Loop for creating new ingredients
        for (int i = 0; i < names.length; i++) {
            Ingredient ingredientInDB = ingredientDao.getByName(names[i]);

            if (ingredientInDB == null) {
                Ingredient ingredient = new Ingredient();
                ingredient.setName(names[i]);
                ingredientInDB = ingredientDao.save(ingredient);
            }
            GroceryListIngredients newGroceryListIngredients = new GroceryListIngredients();
            if (ids[i].length() > 0) {
                newGroceryListIngredients.setId(Integer.parseInt(ids[i]));
            }
            newGroceryListIngredients.setQuantity(Long.parseLong(quantities[i]));
            newGroceryListIngredients.setNotes(notes[i]);
//            groceryListIngredients.setStatus(status[i]); //todo may be API dependent
            newGroceryListIngredients.setGroceryList(listToUpdate);
            newGroceryListIngredients.setIngredient(ingredientInDB);
            newGroceryListIngredients.setUser(loggedInUser);
            listIngredientsDao.save(newGroceryListIngredients);

            ingredientInDB.setName(names[i]);
            ingredientInDB.setId(ingredientInDB.getId());
            ingredientDao.save(ingredientInDB);
        }
        return "redirect:/groceryLists";
    }


//////// FAVORITE

    @PostMapping("/groceryLists/{shareURL}/favorite")
    public String favoriteList(@PathVariable String shareURL, Model model) {
        GroceryList currentGroceryList = groceryDao.getByShareURL(shareURL);
        UserGroceryList listToFavorite = listDao.getByGroceryList(currentGroceryList);
        listToFavorite.setFavorited(true);
        listDao.save(listToFavorite);

        model.addAttribute("isFavorited", !listToFavorite.isFavorited());

        return "redirect:/groceryLists/" + shareURL;
    }

    @PostMapping("/groceryLists/{shareURL}/unfavorite")
    public String unFavoriteList(@PathVariable String shareURL) {
        GroceryList currentGroceryList = groceryDao.getByShareURL(shareURL);
        UserGroceryList listToUnfavorite = listDao.getByGroceryList(currentGroceryList);
        listToUnfavorite.setFavorited(false);
        listDao.save(listToUnfavorite);
        return "redirect:/groceryLists/" + shareURL;
    }




////////// Deletion

//    @PostMapping("/groceryLists/delete/{id}")
//    public String deleteGroceryList(@PathVariable Long id) {
//        User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//
//
//        GroceryList listToDelete = groceryDao.getById(id);
//

//        List<GroceryListIngredients> groceryListIngredients = listToDelete.getGroceryListIngredient();

////        System.out.println(groceryListIngredients);
//
//        for (GroceryListIngredients listItemsToDelete : groceryListIngredients) {
//            listItemsToDelete.setId(listItemsToDelete.getId());
//            listItemsToDelete.setQuantity(listItemsToDelete.getQuantity());
//            listItemsToDelete.setNotes(listItemsToDelete.getNotes());
//            listItemsToDelete.setGroceryList(listToDelete);
//            listItemsToDelete.setUser(currentUser);
//
//
////            System.out.println(listItemsToDelete.getId());
////            System.out.println(listItemsToDelete.getQuantity());
////            System.out.println(listItemsToDelete.getNotes());
//            listIngredientsDao.delete(listItemsToDelete);
//        }
//
//
//
//        return "redirect:/groceryLists";
//    }


}

