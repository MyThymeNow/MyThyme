package com.thyme.mythyme.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "user_groceryList")
public class UserGroceryList {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    @Setter
    private long id;

    @Column(nullable = false)
    @Getter
    @Setter
    private boolean favorited;

    @ManyToOne
    @Getter
    @Setter
    @JoinColumn(name="user_id")
    private User user;

    @ManyToOne
    @Getter
    @Setter
    @JoinColumn(name="groceryList_id")
    private GroceryList groceryList;
}
