package com.thyme.mythyme.repository;

import com.thyme.mythyme.models.Messages;
import com.thyme.mythyme.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface MessagesRepository extends JpaRepository<Messages, Long> {

    List<Messages> getAllBySenderAndReceiver(User sender, User receiver);
}