package com.thyme.mythyme.repository;

import com.thyme.mythyme.models.User;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);


}
