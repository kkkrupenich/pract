package com.example.spring.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "\"Review\"")
@Data
public class Review {

    @Id
    @Column(name = "\"ID\"")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="\"UserID\"")
    @JsonIgnoreProperties(value = {"reviews", "handler", "hibernateLazyInitializer"}, allowSetters=true)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="\"GameID\"")
    @JsonIgnoreProperties(value = {"reviews", "handler", "hibernateLazyInitializer"}, allowSetters=true)
    private Game game;

    @Column(name = "\"Message\"")
    private String message;

    @Column(name = "\"Rating\"")
    private String rating;

    @Column(name = "\"Date\"")
    private Date date;


}
