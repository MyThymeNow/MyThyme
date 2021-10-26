package com.thyme.mythyme.repository;

import com.thyme.mythyme.models.Messages;
import org.springframework.data.jpa.repository.JpaRepository;


public interface MessagesRepository extends JpaRepository<Messages, Long> {

}