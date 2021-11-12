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
@Table(name = "ingredients")
public class Ingredient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    @Setter
    private long id;

    @Column(nullable = false, length = 100)
    @Getter
    @Setter
    private String name;


    //RELATIONSHIPS
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "ingredient")
    @Getter
    @Setter
    private List<GroceryListIngredients> GroceryListIngredients;

    @ManyToMany (mappedBy = "ingredients")
    @Getter
    @Setter
    private List<GroceryList> groceryLists;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name="ingredient_category",
            joinColumns={@JoinColumn(name="ingredient_id")},
            inverseJoinColumns={@JoinColumn(name="category_id")}
    )
    @Getter
    @Setter
    private List<Category> categories;
}
