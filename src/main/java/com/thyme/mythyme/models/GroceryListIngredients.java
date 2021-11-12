package com.thyme.mythyme.models;

import lombok.*;

import javax.persistence.*;
import java.util.List;


@Entity
@Table(name = "grocery_list_ingredients")
public class GroceryListIngredients {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    @Setter
    private long id;

    @Column(nullable = false)
    @Getter
    @Setter
    private long quantity;

    @Column
    @Getter
    @Setter
    private String notes;

    @Column(nullable = false)
    @Getter
    @Setter
    private boolean status;


    //RELATIONSHIPS
    @ManyToOne
    @JoinColumn(name = "user_id")
    @Getter
    @Setter
    private User user;

    @ManyToOne
    @JoinColumn(name = "ingredient_id")
    @Getter
    @Setter
    private Ingredient ingredient;

    @ManyToOne
    @JoinColumn(name = "groceryList_id")
    @Getter
    @Setter
    private GroceryList groceryList;


}
