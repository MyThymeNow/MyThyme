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
    @Getter
    @Setter
    private Location location;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "sender_id")
    @Getter
    @Setter
    private List<Messages> sentMessages;


    @OneToMany(cascade = CascadeType.ALL, mappedBy = "receiver_id")
    @Getter
    @Setter
    private List<Messages> receivedMessages;


    @ManyToMany (mappedBy = "users")
    @Getter
    @Setter
    private List<GroceryList> groceryList;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    @Getter
    @Setter
    private List<GroceryListIngredients> GroceryListIngredient;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name="user_groceryList",
            joinColumns={@JoinColumn(name="user_id")},
            inverseJoinColumns={@JoinColumn(name="groceryList_id")}
    )
    @Getter
    @Setter
    private List<GroceryList> groceryLists;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "favorited_grocerylist",
            joinColumns = {@JoinColumn(name="user_id")},
            inverseJoinColumns = {@JoinColumn(name="groceryList_id")}
    )
    @Getter
    @Setter
    private List<GroceryList> favorites;

//   @JoinColumn(name = "location_id")
//    private Location location;

}
