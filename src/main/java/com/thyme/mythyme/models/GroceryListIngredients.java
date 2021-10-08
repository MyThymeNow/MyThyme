package com.thyme.mythyme.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;


@Entity
@Table(name = "groceryList_Ingredients")
public class GroceryListIngredients {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    @Setter
    private long id;

    @Column(nullable = false)
    @Getter
    @Setter
    private long ingredient_id;

    @Column(nullable = false)
    @Getter
    @Setter
    private long groceryList_id;

    @Column(nullable = false)
    @Getter
    @Setter
    private long quantity;

    @Column(nullable = false)
    @Getter
    @Setter
    private String notes;

    @Column(nullable = false)
    @Getter
    @Setter
    private boolean status;

    @Column
    @Getter
    @Setter
    private long user_id;





}
