package com.example.spring.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.Data;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "\"Card\"")
@Data
public class Card {

    @Id
    @Column(name = "\"ID\"")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "\"Number\"")
    private String number;

    @Column(name = "\"ExpirationDate\"")
    private Date expirationDate;

    @Column(name = "\"HoldersName\"")
    private String holdersName;

    @Column(name = "\"CVV\"")
    private int cvv;

    @ManyToMany(mappedBy="cards")
    @JsonIgnoreProperties(value = {"cards", "handler", "hibernateLazyInitializer"}, allowSetters=true)
    private List<User> users;
}
