package com.thyme.mythyme.repository;

import com.thyme.mythyme.models.UserGroceryList;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserGroceryListRepository extends JpaRepository<UserGroceryList, Long> {
}
