package com.example.spring.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "\"Chance\"")
@Data
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
}
