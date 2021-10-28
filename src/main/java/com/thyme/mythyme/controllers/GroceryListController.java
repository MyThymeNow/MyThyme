package com.thyme.mythyme.controllers;

import com.thyme.mythyme.models.*;
import com.thyme.mythyme.repository.*;
import org.hibernate.cache.spi.support.AbstractReadWriteAccess;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/groceryLists")
    public String showGroceryLists(Model model) {
        List<GroceryList> allLists = groceryDao.findAll();
        model.addAttribute("groceryLists", allLists);
        return "groceryList/index";
    }

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

    @GetMapping("/groceryLists/create")
    public String showCreateListForm(Model model) {
        model.addAttribute("grocery_list", new GroceryList());
        return "groceryList/create";
    }

    @PostMapping("/groceryLists/create")
    public String saveUserGroceryList(
            @ModelAttribute GroceryList listToCreate,
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
        listToCreate.setOwner(loggedInUser);
        listToCreate.setShareURL(uuid.toString());
        groceryDao.save(listToCreate);
        return"redirect:/groceryLists";

    }

    @GetMapping("/groceryLists/edit/{shareURL}")
    public String showEditPostForm(@PathVariable String shareURL, Model model) {
        GroceryList listToEdit = groceryDao.getByShareURL(shareURL);
        model.addAttribute("listToEdit",listToEdit);
        return "groceryList/edit";
    }

    @PostMapping("/groceryLists/edit/{shareURL}")
    public String editPost(
            @PathVariable String shareURL,
            @ModelAttribute GroceryList updatedList
    ) {
        User loggedInUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        updatedList.setShareURL(shareURL);
        updatedList.setOwner(loggedInUser);
        groceryDao.save(updatedList);

        return "redirect:/groceryLists";

    }

    @PostMapping("/groceryLists/delete/{shareURL}")
    public String deleteGroceryList(@PathVariable String shareURL) {
        GroceryList listToDelete = groceryDao.getByShareURL(shareURL);
        groceryDao.delete(listToDelete);

        return "redirect:/groceryLists";
    }

//    //show form for adding partyItems
//    @GetMapping("/parties/items/{urlKey}")
//    public String showItemForm(Model model, @PathVariable String urlKey){
//        Party party = partyDao.getByUrlKey(urlKey); //gets party
//        model.addAttribute("party", party); //sets party
//        return "/party/createItems";
//    }
//
//    //saves party information
//    @PostMapping("/parties/items/{urlKey}")
//    public String addItems(@PathVariable String urlKey, @RequestParam(name="name[]") String[] names,@RequestParam(name="quantity[]") String[] quantities ) {
//        Party party = partyDao.getByUrlKey(urlKey);
//
//        for(int i = 0; i< names.length; i++){
//
//            Item item = new Item(); //create new item instance
//            item.setName(names[i]); //set item name from name[]
//            itemDao.save(item); //save item instance
//
//            //creates & Saves party item
//            PartyItem partyItem = new PartyItem();
//            partyItem.setItem(item);
//            partyItem.setQuantityRequired(Long.valueOf(quantities[i]));
//            partyItem.setParty(party);
//            partyItemDao.save(partyItem);
//        }
//        return "redirect:/parties/success/" + urlKey;
//    }


}