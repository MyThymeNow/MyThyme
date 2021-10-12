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

    @ManyToMany (mappedBy = "groceryLists")
    private List<User> users;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name="groceryList_category",
            joinColumns={@JoinColumn(name="groceryList_id")},
            inverseJoinColumns={@JoinColumn(name="category_id")}
    )
    private List<Category> categories;


    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name="groceryList_Ingredients",
            joinColumns={@JoinColumn(name="ingredient_id")},
            inverseJoinColumns={@JoinColumn(name="groceryList_id")}
    )
    private List<Ingredient> ingredients;
}
