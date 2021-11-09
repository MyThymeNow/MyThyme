package com.thyme.mythyme.repository;

import com.thyme.mythyme.models.GroceryList;
import com.thyme.mythyme.models.UserGroceryList;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserGroceryListRepository extends JpaRepository<UserGroceryList, Long> {

    UserGroceryList getByUser_Id(long id);

    UserGroceryList getByGroceryList_Id(Long id);

    UserGroceryList getByGroceryList(GroceryList groceryList);
}
