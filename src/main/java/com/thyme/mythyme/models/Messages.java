package com.thyme.mythyme.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name = "messages")

public class Messages implements  Comparable <Messages>{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    @Setter
    private long id;

    @Column(nullable = false, columnDefinition = "text")
    @Getter
    @Setter
    private String content;

    @Column(nullable = false)
    @Getter
    @Setter
    private Timestamp timestamp;


    //RELATIONSHIPS
    @ManyToOne
    @JoinColumn(name = "sender_id")
    @Getter
    @Setter
    public User sender;

    @ManyToOne
    @JoinColumn(name = "receiver_id")
    @Getter
    @Setter
    public User receiver;


    public int compareTo(Messages o){
        return this.timestamp.compareTo(o.getTimestamp());
    }

    public String getReadableDate() {
        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy HH:mm");

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy HH:mm");
        return this.timestamp.toLocalDateTime().format(formatter);
    }

}
