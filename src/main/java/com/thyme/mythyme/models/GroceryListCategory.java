package com.thyme.mythyme.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "groceryList_categories")
public class GroceryListCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long groceryList_id;

    @Column(nullable = false)
    private long Category_id;


}
