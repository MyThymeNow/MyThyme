package com.thyme.mythyme.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

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

}
