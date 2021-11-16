package com.thyme.mythyme.controllers;

import com.thyme.mythyme.models.GroceryList;
import com.thyme.mythyme.models.Messages;
import com.thyme.mythyme.models.User;
import com.thyme.mythyme.repository.GroceryListRepository;
import com.thyme.mythyme.repository.MessagesRepository;
import com.thyme.mythyme.repository.UserRepository;
import com.thyme.mythyme.utils.SortByTimestamp;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
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

//    @GetMapping("/messages/create")
//    public String createNewMessageThread(Model model)

    @GetMapping("/messages/{otherUserId}")
    public String newMessageInThread(Model model, @PathVariable long otherUserId) {
        User loggedInUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = userDao.getById(loggedInUser.getId());
        model.addAttribute("loggedinuser", user);

        model.addAttribute("userMessage", new Messages());

//        List<Messages> messageList = user.getReceivedMessages();
//        List<Messages> sentMessages = user.getSentMessages();

//        sentMessages.addAll(messageList);

//        Collections.sort(messageList);

        User otherUser = userDao.getById(otherUserId);

        List<Messages> sentMessages = messageDao.getAllBySenderAndReceiver(loggedInUser, otherUser);
        List<Messages> receivedMessages = messageDao.getAllBySenderAndReceiver(otherUser, loggedInUser);

        List<Messages> allMessages = new ArrayList<>();
        allMessages.addAll(sentMessages);
        allMessages.addAll(receivedMessages);
        Collections.sort(allMessages, new SortByTimestamp());

        for (Messages message : allMessages){
            System.out.println(message.getTimestamp());
        }

        model.addAttribute("messages",allMessages);
        model.addAttribute("otheruser", otherUser);
        model.addAttribute("sentmessages", sentMessages);
        model.addAttribute("receivedmessages", receivedMessages);



        return "user/message";
    }

    @PostMapping("/messages/{id}")
    public String sendMessage(Model model, @PathVariable long id, @ModelAttribute Messages userMessage) {
        User loggedInUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = userDao.findByUsername(loggedInUser.getUsername());
        User receivingUser = userDao.getById(id);

        Messages newMessage = new Messages();
        newMessage.setSender(loggedInUser);
        newMessage.setReceiver(receivingUser);
        newMessage.setContent(userMessage.getContent());
        newMessage.setTimestamp(new Timestamp(System.currentTimeMillis()));
        messageDao.save(newMessage);

        return "redirect:/messages/" + id;


    }

    @GetMapping("messages")
    public String messageLog(Model model) {
        User loggedInUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = userDao.findByUsername(loggedInUser.getUsername());

        List<Messages> messageHistory = new ArrayList<>();
        List<User> listOfUsers = new ArrayList<>();

        messageHistory.addAll(user.getReceivedMessages());
        messageHistory.addAll(user.getSentMessages());




        for (Messages messages : messageHistory) {
            if (!listOfUsers.contains(messages.receiver) && user.getId() != messages.receiver.getId()) {
                listOfUsers.add(messages.receiver);
            } else if (!listOfUsers.contains(messages.sender) && user.getId() != messages.sender.getId()) {
                listOfUsers.add(messages.sender);
            }
        }

        model.addAttribute("users", listOfUsers);

        return "user/message-index";
    }


//    ///////// Sharing
//    @PostMapping("/groceryLists/{shareURL}")
//    public String shareGroceryList(@PathVariable String shareURL, @ModelAttribute GroceryList SharedList) {
//
//
//        return "redirect:/groceryLists";
//    }
}

