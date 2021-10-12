package com.thyme.mythyme.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
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

    @Column(nullable = false, length = 100)
    @Getter
    @Setter
    private long sender_id;

    @Column(nullable = false, length = 100)
    @Getter
    @Setter

    private long receiver_id;

    @Column(nullable = false, columnDefinition = "text")
    @Getter
    @Setter
    private String content;

    @ManyToOne
    @JoinColumn (name = "user_id")
    private User user;


}
