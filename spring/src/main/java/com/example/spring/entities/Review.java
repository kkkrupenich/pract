package com.example.spring.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import java.util.Date;

@Entity
@Table(name = "\"Review\"")
@Data
public class Review {

    @Id
    @Column(name = "\"ID\"")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "\"UserID\"")
    private Long userId;

    @Column(name = "\"GameID\"")
    private Long gameId;

    @Column(name = "\"Message\"")
    private String message;

    @Column(name = "\"Rating\"")
    private String rating;

    @Column(name = "\"Date\"")
    private Date date;
}
