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

        List<Message> messageList = user.getReceivedMessages();
        List<Message> sentMessages = user.getSentMessages();

        for (Message message : listOfMessagesSent) {
            listOfMessages.add(message);
        }

        Collections.sort(listOfMessages);

        User receivingUser = userDao.getById(id);

        model.addAttribute("otheruser", receivingUserUser);
        model.addAttribute("messages", listOfMessages);

        return "/messages/message";

    }
}
