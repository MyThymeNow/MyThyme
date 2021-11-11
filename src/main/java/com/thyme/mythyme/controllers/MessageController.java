package com.thyme.mythyme.controllers;

import com.thyme.mythyme.models.Messages;
import com.thyme.mythyme.models.User;
import com.thyme.mythyme.repository.GroceryListRepository;
import com.thyme.mythyme.repository.MessagesRepository;
import com.thyme.mythyme.repository.UserRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Controller
public class MessageController {


    private final MessagesRepository messageDao;
    private final GroceryListRepository groceryDao;
    private final UserRepository userDao;

    public MessageController(MessagesRepository messageDao, GroceryListRepository groceryDao, UserRepository userDao) {
        this.messageDao = messageDao;
        this.groceryDao = groceryDao;
        this.userDao = userDao;
    }

    @GetMapping("/messages/{otherUserId}")
    public String newMessage(Model model,  @PathVariable long otherUserId) {
        User loggedInUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = userDao.findByUsername(loggedInUser.getUsername());
        model.addAttribute("loggedinuser", user);

        model.addAttribute("userMessage", new Messages());

        List<Messages> messageList = user.getReceivedMessages();
        List<Messages> sentMessages = user.getSentMessages();

        for (Messages message : sentMessages) {
            messageList.add(message);
        }

        Collections.sort(messageList);

       User otherUser = userDao.getById(otherUserId);

        model.addAttribute("otheruser", otherUser);
        model.addAttribute("messages", messageList);

        return "messages/message";
    }

    @PostMapping("/messages/{id}")
    public String sendMessage(Model model, @PathVariable long id, @ModelAttribute Messages message) {
        User loggedInUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = userDao.findByUsername(loggedInUser.getUsername());
        User receivingUser = userDao.getById(id);

        Messages newMessage = new Messages();
        newMessage.setSender(loggedInUser);
        newMessage.setReceiver(receivingUser);
        newMessage.setContent(message.getContent());
        newMessage.setTimestamp(new Timestamp(System.currentTimeMillis()));
        messageDao.save(newMessage);


        return "redirect:/messages/" + id;


    }

    @GetMapping("messages")
    public String messageLog(Model model){
        User loggedInUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = userDao.findByUsername(loggedInUser.getUsername());

        List<Messages> messageHistory = new ArrayList<>();
        List<User> listOfUsers = new ArrayList<>();

        messageHistory.addAll(user.getReceivedMessages());
        messageHistory.addAll(user.getSentMessages());


        for(int i = 0; i < messageHistory.size(); i++) {
            if(!listOfUsers.contains(messageHistory.get(i).receiver) && user.getId() != messageHistory.get(i).receiver.getId()) {
                listOfUsers.add(messageHistory.get(i).receiver);
            } else if (!listOfUsers.contains(messageHistory.get(i).sender) && user.getId() != messageHistory.get(i).sender.getId()) {
                listOfUsers.add(messageHistory.get(i).sender);
            }
        }

        model.addAttribute("users", listOfUsers);

        return "messages/index";
    }

//    ///////// Sharing
//    @GetMapping("/groceryLists/share/{shareURL}")
//    public String viewShareGroceryList (@PathVariable String shareURL, Model model){
//        GroceryList listToShare = groceryDao.getByShareURL(shareURL);
//        model.addAttribute("groceryListShareURL", shareURL);
//        model.addAttribute("listToShare", listToShare);
//        return "groceryList/share";
//    }

//    @PostMapping("/groceryLists/share/{shareURL}")
//    public String shareGroceryList (@PathVariable String shareURL, @ModelAttribute GroceryList SharedList){
//
//
//        return "redirect:/groceryLists";
//    }




}
