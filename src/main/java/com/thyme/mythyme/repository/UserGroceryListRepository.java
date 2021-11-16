package com.thyme.mythyme.repository;

import com.thyme.mythyme.models.GroceryList;
import com.thyme.mythyme.models.User;
import com.thyme.mythyme.models.UserGroceryList;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserGroceryListRepository extends JpaRepository<UserGroceryList, Long> {

    UserGroceryList getByUser_Id(long id);

    UserGroceryList getByGroceryList_ShareURL(String shareURL);

    UserGroceryList getByGroceryList(GroceryList groceryList);

    List <UserGroceryList> getByFavorited(boolean favorited);

    List<UserGroceryList> getByFavoritedAndUser_Id( boolean favorited, Long id);
}
