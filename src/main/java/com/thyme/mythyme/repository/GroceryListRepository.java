package com.thyme.mythyme.repository;

import com.thyme.mythyme.models.GroceryList;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GroceryListRepository extends JpaRepository<GroceryList, Long> {
}
