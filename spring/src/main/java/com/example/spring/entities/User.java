package com.example.spring.entities;

import java.util.List;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "\"User\"")
@Data
public class User {

    @Id
    @Column(name = "\"ID\"")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "\"Email\"")
    private String email;

    @Column(name = "\"Password\"")
    @JsonIgnore
    private String password;

    @Column(name = "\"FIO\"")
    private String fio;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "\"PassportID\"")
    @JsonIgnoreProperties(value = { "user", "handler", "hibernateLazyInitializer" }, allowSetters = true)
    private Passport passport;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "\"RoleID\"")
    @JsonIgnoreProperties(value = { "users", "handler", "hibernateLazyInitializer" }, allowSetters = true)
    private Role role;

    @Column(name = "\"Balance\"")
    private double balance;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "\"SubscriptionID\"")
    @JsonIgnoreProperties(value = { "users", "handler", "hibernateLazyInitializer" }, allowSetters = true)
    private Subscription subscription;

    @OneToMany(mappedBy="user")
    @JsonIgnoreProperties(value = {"user", "handler", "hibernateLazyInitializer"}, allowSetters=true)
    private Set<Review> reviews;

    @ManyToMany()
    @JsonIgnoreProperties(value = {"users", "handler", "hibernateLazyInitializer"}, allowSetters=true)
    @JoinTable(name="\"Card_User\"",
            joinColumns=@JoinColumn(name="\"UserID\"",referencedColumnName="\"ID\""),
            inverseJoinColumns=@JoinColumn(name="\"CardID\"", referencedColumnName="\"ID\""))
    private List<Card> cards;
}
