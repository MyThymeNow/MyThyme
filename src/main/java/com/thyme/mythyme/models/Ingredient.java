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

    @ManyToOne
    @JoinColumn(name = "categories_id")
    private Category category;

    @ManyToMany (mappedBy = "ingredients")
    private List<GroceryList> groceryLists;


}
