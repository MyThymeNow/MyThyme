package com.thyme.mythyme.models;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Setter @Getter
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name = "grocery_lists")
@ToString
public class GroceryList {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    @Setter
    private long id;

    @Column(nullable = false, length = 75)
    @Getter
    @Setter
    private String name;

    @Column(nullable = false, length = 250)
    @Getter
    @Setter
    private String shareURL;


    //RELATIONSHIPS
    @OneToOne
    @Getter
    @Setter
    private User owner;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "groceryList")
    @Getter
    @Setter
    private List<GroceryListIngredients> GroceryListIngredients;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "groceryList")
    @Getter
    @Setter
    private List<UserGroceryList> userGroceryLists;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name="grocery_list_categories",
            joinColumns={@JoinColumn(name="ingredient_id")},
            inverseJoinColumns={@JoinColumn(name="category_id")}
    )
    @Getter
    @Setter
    private List<Category> categories;


    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name="grocery_list_ingredients",
            joinColumns={@JoinColumn(name="ingredient_id")},
            inverseJoinColumns={@JoinColumn(name="groceryList_id")}
    )
    @Getter
    @Setter
    private List<Ingredient> ingredients;


}
