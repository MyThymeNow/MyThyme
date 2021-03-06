package com.thyme.mythyme.repository;

import com.thyme.mythyme.models.GroceryList;
import com.thyme.mythyme.models.GroceryListIngredients;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GroceryListIngredientsRepository extends JpaRepository<GroceryListIngredients, Long> {

        List <GroceryListIngredients> getByGroceryList(GroceryList groceryList);

        List <GroceryListIngredients> getByGroceryListId(Long id);

        GroceryListIngredients findByQuantity(Long quantity);

        GroceryListIngredients findByNotes(String notes);

        GroceryListIngredients getAllByGroceryList_Id(Long id);

        GroceryListIngredients getAllByGroceryList(GroceryList groceryList);
}
