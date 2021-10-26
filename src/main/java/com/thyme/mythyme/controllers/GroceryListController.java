package com.thyme.mythyme.controllers;

import com.thyme.mythyme.models.GroceryList;
import com.thyme.mythyme.models.User;
import com.thyme.mythyme.models.UserGroceryList;
import com.thyme.mythyme.repository.GroceryListRepository;
import com.thyme.mythyme.repository.UserGroceryListRepository;
import com.thyme.mythyme.repository.UserRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;
import java.util.UUID;

@Controller
public class GroceryListController {

    private final GroceryListRepository groceryDao;
    private final UserRepository userDao;
    private final UserGroceryListRepository listDao;

    public GroceryListController(GroceryListRepository groceryDao, UserGroceryListRepository listDao, UserRepository userDao) {
        this.groceryDao = groceryDao;
        this.userDao = userDao;
        this.listDao = listDao;
    }

    @GetMapping("/groceryLists")
    public String showGroceryLists(Model model) {
        List<GroceryList> allLists = groceryDao.findAll();
        model.addAttribute("groceryLists", allLists);
        return "groceryList/index";
    }

    @GetMapping("/groceryLists/{shareURL}")
    public String showOneGroceryList(@PathVariable String shareURL, Model model) {
        GroceryList groceryList = groceryDao.getByShareURL(shareURL);
        model.addAttribute("groceryListShareURL", shareURL);
        model.addAttribute("groceryList", groceryList);
        return "groceryList/show";
    }

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
    public String saveUserGroceryList(@ModelAttribute GroceryList listToAdd) {

        User loggedInUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        UUID uuid = UUID.randomUUID();
        listToAdd.setOwner(loggedInUser);
        listToAdd.setShareURL(uuid.toString());
        groceryDao.save(listToAdd);
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
