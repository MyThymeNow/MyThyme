package com.thyme.mythyme.repository;

import com.thyme.mythyme.models.Ingredient;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IngredientRepository extends JpaRepository<Ingredient, Long> {

    List <Ingredient> findAllById(Long id);

//    List <Ingredient> getIngredientsByGroceryListIngredientsId(Long id);




}
