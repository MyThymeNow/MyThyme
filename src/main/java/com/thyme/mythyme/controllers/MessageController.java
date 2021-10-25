package com.thyme.mythyme.controllers;

import com.thyme.mythyme.models.User;
import com.thyme.mythyme.repository.Messages;
import com.thyme.mythyme.repository.UserRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Collections;

@Controller
public class MessageController {

    private MessagesRepository messageDao;

    private final UserRepository userDao;

    public MessageController(UserRepository userDao) {

        this.userDao = userDao;
    }

    @GetMapping("/messages/{id}")
    public String messages(Model model, @PathVariable long id) {
        User loggedInUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = userDao.getById(loggedInUser.getId());
        model.addAttribute("loggedinuser", user);

        model.addAttribute("message", new Message());

        List<Messages> messageList = user.getReceivedMessages();
        List<Messages> sentMessages = user.getSentMessages();

        for (Messages message : messageList) {
            sentMessages.add(message);
        }

        Collections.sort(messageList);

        User otherUser = userDao.getById(id);

        model.addAttribute("otheruser", otherUser);
        model.addAttribute("messages", listOfMessages);

        return "user/message";

    }

    @PostMapping("/message/{id}")
    public String sendMessage(Model model, @PathVariable long id, @ModelAttribute com.thyme.mythyme.models.Messages message) {
        User loggedInUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = userDao.findByUsername(loggedInUser.getUsername());
        User receivingUser = userDao.getById(id);

        com.thyme.mythyme.models.Messages newMessage = new com.thyme.mythyme.models.Messages();
        newMessage.setSender(loggedInUser);
        newMessage.setReceiver(receivingUser);
        newMessage.setContent(message.getContent());
        messageDao.save(newMessage);




        return "redirect:/message" + id;
    }

}
