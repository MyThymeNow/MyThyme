package com.thyme.mythyme.controllers;

import com.thyme.mythyme.models.Messages;
import com.thyme.mythyme.models.User;
import com.thyme.mythyme.repository.MessagesRepository;
import com.thyme.mythyme.repository.UserRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.Collections;
import java.util.List;

@Controller
public class MessageController {


    private final MessagesRepository messageDao;


    private final UserRepository userDao;

    public MessageController(MessagesRepository messageDao, UserRepository userDao) {
        this.messageDao = messageDao;

        this.userDao = userDao;
    }

    @GetMapping("/messages/{otherUserId}")
    public String newMessage(Model model,  @PathVariable long otherUserId) {
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

}
