package com.thyme.mythyme.repository;

import com.thyme.mythyme.models.GroceryList;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GroceryListRepository extends JpaRepository<GroceryList, Long> {

    GroceryList getByShareURL(String shareURL);

    List<GroceryList> findByOwner_Id(long id);
}
