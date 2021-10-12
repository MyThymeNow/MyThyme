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
    private long id;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;

    @Column(nullable = false)
    private boolean favorite;

    @Column(nullable = false)
    private boolean isAdmin;

    @OneToOne
    private Location location;

    @OneToOne
    private Messages messages;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name="user_groceryList",
            joinColumns={@JoinColumn(name="user_id")},
            inverseJoinColumns={@JoinColumn(name="groceryList_id")}
    )
    private List<GroceryList> groceryLists;

//   @JoinColumn(name = "location_id")
//    private Location location;

}
