package com.thyme.mythyme.repository;

import com.thyme.mythyme.models.Ingredient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IngredientRepository extends JpaRepository<Ingredient, Long> {

    Ingredient getByName (String name);

    Ingredient findById (long id);

}
