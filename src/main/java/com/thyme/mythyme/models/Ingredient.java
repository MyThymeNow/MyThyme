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

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name="ingredient_category",
            joinColumns={@JoinColumn(name="ingredient_id")},
            inverseJoinColumns={@JoinColumn(name="category_id")}
    )
    private List<Category> categories;

    @ManyToMany (mappedBy = "ingredients")
    private List<GroceryList> groceryLists;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "ingredient")
    private List<GroceryListIngredients> GroceryListIngredient;

}
