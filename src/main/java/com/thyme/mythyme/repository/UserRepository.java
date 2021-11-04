package com.thyme.mythyme.repository;

import com.thyme.mythyme.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;


public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);

    @Transactional
    @Modifying
    @Query("update User a set a.isLocked = :isLocked where a.id = :id")
    void unlockUser(@Param(value = "id") Long id, @Param(value = "isLocked") boolean isLocked);



}
