package com.thyme.mythyme.controllers;

import com.thyme.mythyme.models.Messages;
import com.thyme.mythyme.models.User;
import com.thyme.mythyme.repository.MessagesRepository;
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
public class MessageController {

    private final MessagesRepository messageDao;

    private final UserRepository userDao;

    public MessageController(MessagesRepository messageDao, UserRepository userDao) {
        this.messageDao = messageDao;

        this.userDao = userDao;
    }

    @GetMapping("/messages/{id}")
    public String messages(Model model, @PathVariable long id) {
        User loggedInUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = userDao.getById(loggedInUser.getId());
        model.addAttribute("loggedinuser", user);

        model.addAttribute("message", new Messages());

        List<Messages> messageList = user.getReceivedMessages();
        List<Messages> sentMessages = user.getSentMessages();

        sentMessages.addAll(messageList);

        User otherUser = userDao.getById(id);

        model.addAttribute("otheruser", otherUser);
        model.addAttribute("messages", messageList);

        return "user/message";

    }

    @PostMapping("/message/{id}")
    public String sendMessage(Model model, @PathVariable long id, @ModelAttribute Messages message) {
        User loggedInUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = userDao.findByUsername(loggedInUser.getUsername());
        User receivingUser = userDao.getById(id);

        Messages newMessage = new Messages();
        newMessage.setSender(user);
        newMessage.setReceiver(receivingUser);
        newMessage.setContent(message.getContent());
        messageDao.save(newMessage);


        return "redirect:/message" + id;
    }

}
