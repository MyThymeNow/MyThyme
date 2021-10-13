package com.thyme.mythyme.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Setter @Getter
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name = "grocery_lists")
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

    @Column(nullable = false, length = 100)
    @Getter
    @Setter
    private String shareURL;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "groceryList")
    @Getter
    @Setter
    private List<GroceryListIngredients> GroceryListIngredient;

    @ManyToMany (mappedBy = "groceryLists")
    @Getter
    @Setter
    private List<User> user;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name="groceryList_categories",
            joinColumns={@JoinColumn(name="ingredient_id")},
            inverseJoinColumns={@JoinColumn(name="category_id")}
    )
    @Getter
    @Setter
    private List<Category> categories;


//    @ManyToMany(cascade = CascadeType.ALL)
//    @JoinTable(
//            name="groceryList_Ingredients",
//            joinColumns={@JoinColumn(name="ingredient_id")},
//            inverseJoinColumns={@JoinColumn(name="groceryList_id")}
//    )
//    @Getter
//    @Setter
//    private List<Ingredient> ingredients;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name="userFavorite",
            joinColumns={@JoinColumn(name="user_id")},
            inverseJoinColumns={@JoinColumn(name="groceryList_id")}
    )
    @Getter
    @Setter
    private List<User> users;

}
