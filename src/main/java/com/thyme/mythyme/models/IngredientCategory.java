package com.thyme.mythyme.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;


@Entity
@Table(name = "Ingredient_category")
public class IngredientCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long ingredient_id;

    @Column(nullable = false)
    private long category_id;

}
