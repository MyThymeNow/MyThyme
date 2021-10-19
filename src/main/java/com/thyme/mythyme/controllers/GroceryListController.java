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

@Controller
public class GroceryListController {

    private final GroceryListRepository groceryDao;
    private final UserRepository userDao;

    public GroceryListController(GroceryListRepository groceryDao, UserRepository userDao) {
        this.groceryDao = groceryDao;
        this.userDao = userDao;
    }

    @GetMapping("/groceryLists")
    public String showGroceryLists(Model model) {
        List<GroceryList> allLists = groceryDao.findAll();
        model.addAttribute("groceryLists", allLists);
        return "groceryList/index";
    }

    @GetMapping("/groceryLists/{id}")
    public String showOneGroceryList(@PathVariable long id, Model model) {
        GroceryList groceryList = groceryDao.getById(id);
        model.addAttribute("groceryListId", id);
        model.addAttribute("groceryList", groceryList);
        return "groceryList/show";
    }

    @GetMapping("/groceryLists/create")
    public String showCreateListForm(Model model) {
        model.addAttribute("groceryList", new GroceryList());
        return "groceryList/create";
    }

    @PostMapping("/groceryLists/create")
    public String createGroceryList(@ModelAttribute GroceryList listToAdd) {

        User loggedInUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        listToAdd.setOwner(loggedInUser);

        groceryDao.save(listToAdd);
        return"redirect:/groceryLists";
    }

    @PostMapping("/posts/delete/{id}")
    public String deletePost(@PathVariable long id) {
        GroceryList listToDelete = groceryDao.getById(id);
        groceryDao.delete(listToDelete);

        return "redirect:/groceryLists";
    }



}
