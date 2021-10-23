package com.thyme.mythyme.repository;

import com.thyme.mythyme.models.GroceryListIngredients;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GroceryListIngredientRepository extends JpaRepository<GroceryListIngredients, Long> {
}
