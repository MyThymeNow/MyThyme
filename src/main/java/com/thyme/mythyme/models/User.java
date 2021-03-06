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

    @Column //(nullable = false)
    @Getter
    @Setter
    private String bio;

    @Column
    @Getter
    @Setter
    private boolean isAdmin;

    @Column
    @Getter
    @Setter
    private boolean isLocked;


    public User(User copy) {
        id = copy.id;
        email = copy.email;
        username = copy.username;
        password = copy.password;
        firstName = copy.firstName;
        lastName = copy.lastName;
        bio = copy.bio;
    }



    // RELATIONSHIPS

    @OneToOne
    @Getter
    @Setter
    private Location location;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "sender")
    @Getter
    @Setter
    private List<Messages> sentMessages;


    @OneToMany(cascade = CascadeType.ALL, mappedBy = "receiver")
    @Getter
    @Setter
    private List<Messages> receivedMessages;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    @Getter
    @Setter
    private List<GroceryListIngredients> GroceryListIngredients;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    @Getter
    @Setter
    private List<UserGroceryList> userGroceryLists;




    //   @JoinColumn(name = "location_id")
//    private Location location;

}
