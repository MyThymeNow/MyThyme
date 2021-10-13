package com.thyme.mythyme.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name = "messages")
public class Messages {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    @Setter
    private long id;

    @ManyToOne
    @JoinColumn(name = "sender_id")
    @Getter
    @Setter
    private User sender;

    @ManyToOne
    @JoinColumn(name = "receiver_id")
    @Getter
    @Setter
    private User receiver;

    @Column(nullable = false, columnDefinition = "text")
    @Getter
    @Setter
    private String content;

    @Column(nullable = false)
    @Getter
    @Setter
    private Timestamp timestamp;

//    @ManyToOne
//    @JoinColumn (name = "user_id")
//    @Getter
//    @Setter
//    private User user;


}
