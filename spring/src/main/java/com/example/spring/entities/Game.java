package com.example.spring.entities;

import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "\"ChanceID\"")
    @JsonIgnoreProperties(value = { "games", "handler", "hibernateLazyInitializer" }, allowSetters = true)
    private Chance chance;

    @Column(name = "\"MinimalBet\"")
    private double minimalBet;

    @Column(name = "\"MaximumBet\"")
    private double maximumBet;

    @OneToMany(mappedBy="game")
    @JsonIgnoreProperties(value = {"game", "handler", "hibernateLazyInitializer"}, allowSetters=true)
    private Set<Review> reviews;
}
