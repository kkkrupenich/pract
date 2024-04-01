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

@Entity
@Table(name = "\"Game\"")
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

    @OneToMany(mappedBy = "game")
    @JsonIgnoreProperties(value = { "game", "handler", "hibernateLazyInitializer" }, allowSetters = true)
    private Set<Review> reviews;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isPremiumStatus() {
        return premiumStatus;
    }

    public void setPremiumStatus(boolean premiumStatus) {
        this.premiumStatus = premiumStatus;
    }

    public Chance getChance() {
        return chance;
    }

    public void setChance(Chance chance) {
        this.chance = chance;
    }

    public double getMinimalBet() {
        return minimalBet;
    }

    public void setMinimalBet(double minimalBet) {
        this.minimalBet = minimalBet;
    }

    public double getMaximumBet() {
        return maximumBet;
    }

    public void setMaximumBet(double maximumBet) {
        this.maximumBet = maximumBet;
    }

    public Set<Review> getReviews() {
        return reviews;
    }

    public void setReviews(Set<Review> reviews) {
        this.reviews = reviews;
    }

}
