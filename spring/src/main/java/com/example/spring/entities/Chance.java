package com.example.spring.entities;

import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "\"Chance\"")
public class Chance {

    @Id
    @Column(name = "\"ID\"")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "\"LoseChance\"")
    private double loseChance;

    @Column(name = "\"ReturnChance\"")
    private double returnChance;

    @Column(name = "\"WinChance\"")
    private double winChance;

    @OneToMany(mappedBy = "chance")
    @JsonIgnoreProperties(value = { "chance", "handler", "hibernateLazyInitializer" }, allowSetters = true)
    private Set<Game> games;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public double getLoseChance() {
        return loseChance;
    }

    public void setLoseChance(double loseChance) {
        this.loseChance = loseChance;
    }

    public double getReturnChance() {
        return returnChance;
    }

    public void setReturnChance(double returnChance) {
        this.returnChance = returnChance;
    }

    public double getWinChance() {
        return winChance;
    }

    public void setWinChance(double winChance) {
        this.winChance = winChance;
    }

    public Set<Game> getGames() {
        return games;
    }

    public void setGames(Set<Game> games) {
        this.games = games;
    }

    
}
