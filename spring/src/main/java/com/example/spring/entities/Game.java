package com.example.spring.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "\"Game\"")
@Data
public class Game {

    @Id
    @Column(name = "\"ID\"")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "\"Name\"")
    private String name;

    @Column(name = "\"PremiumStatus\"")
    private boolean premiumStatus;

    @Column(name = "\"ChanceID\"")
    private int chanceID;

    @Column(name = "\"MinimalBet\"")
    private double minimalBet;

    @Column(name = "\"MaximumBet\"")
    private double maximumBet;
}
