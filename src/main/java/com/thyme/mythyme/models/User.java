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
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    @Setter
    private long id;

    @Column(nullable = false)
    @Getter
    @Setter
    private String username;

    @Column(nullable = false)
    @Getter
    @Setter
    private String password;

    @Column(nullable = false)
    @Getter
    @Setter
    private String email;

    @Column(nullable = false)
    @Getter
    @Setter
    private String firstName;

    @Column(nullable = false)
    @Getter
    @Setter
    private String lastName;

    @Column(nullable = false)
    @Getter
    @Setter
    private boolean isAdmin;

    @OneToOne
    private Location location;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    private List<Messages> messages;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name="user_groceryList",
            joinColumns={@JoinColumn(name="user_id")},
            inverseJoinColumns={@JoinColumn(name="groceryList_id")}
    )
    private List<GroceryList> groceryLists;

    @ManyToMany (mappedBy = "users")
    private List<GroceryList> groceryList;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    private List<GroceryListIngredients> GroceryListIngredient;



//   @JoinColumn(name = "location_id")
//    private Location location;

}
