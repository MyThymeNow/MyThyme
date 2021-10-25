package com.thyme.mythyme.controllers;

import com.thyme.mythyme.models.GroceryList;
import com.thyme.mythyme.models.User;
import com.thyme.mythyme.repository.GroceryListRepository;
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

    public GroceryListController(GroceryListRepository groceryDao, UserRepository userDao) {
        this.groceryDao = groceryDao;
        this.userDao = userDao;
    }
//////// Index
    @GetMapping("/groceryLists")
    public String showGroceryLists(Model model) {
        User loggedInUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        List<GroceryList> userLists = groceryDao.findByOwner_Id(loggedInUser.getId());
        model.addAttribute("groceryLists", userLists);
        return "groceryList/index";
    }

    @GetMapping("/groceryLists/{id}")
    public String viewSingleGroceryList(@PathVariable long id, Model model) {
        GroceryList groceryList = groceryDao.getById(id);
        model.addAttribute("groceryListId", id);
        model.addAttribute("groceryList", groceryList);
        return "groceryList/show";
    }


///////// Sharing
    @GetMapping("/groceryLists/share/{shareURL}")
    public String viewShareGroceryList(@PathVariable String shareURL, Model model) {
        GroceryList listToShare = groceryDao.getByShareURL(shareURL);
        model.addAttribute("groceryListShareURL", shareURL);
        model.addAttribute("listToShare", listToShare);
        return "groceryList/share";
    }

    @PostMapping("/groceryLists/share/{shareURL}")
    public String shareGroceryList(@PathVariable String shareURL, @ModelAttribute GroceryList SharedList) {


        return "redirect:/groceryLists";
    }


//////// Creating
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


//////// Editing
    @GetMapping("/groceryLists/edit/{id}")
    public String showEditPostForm(@PathVariable long id, Model model) {
        GroceryList listToEdit = groceryDao.getById(id);
        model.addAttribute("listToEdit",listToEdit);
        return "groceryList/edit";
    }

    @PostMapping("/groceryLists/edit/{id}")
    public String editPost(
            @PathVariable long id,
            @ModelAttribute GroceryList updatedList
    ) {
        User loggedInUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        updatedList.setId(id);
        updatedList.setOwner(loggedInUser);
        groceryDao.save(updatedList);

        return "redirect:/groceryLists";

    }


//////// Deleting
    @PostMapping("/groceryLists/delete/{id}")
    public String deleteGroceryList(@PathVariable long id) {
        GroceryList listToDelete = groceryDao.getById(id);
        groceryDao.delete(listToDelete);

        return "redirect:/groceryLists";
    }




}
