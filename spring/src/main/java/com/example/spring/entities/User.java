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

@Entity
@Table(name = "\"User\"")
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

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "\"RoleID\"")
    @JsonIgnoreProperties(value = { "users", "handler", "hibernateLazyInitializer" }, allowSetters = true)
    private Role role;

    @Column(name = "\"Balance\"")
    private double balance;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "\"SubscriptionID\"")
    @JsonIgnoreProperties(value = { "users", "handler", "hibernateLazyInitializer" }, allowSetters = true)
    private Subscription subscription;

    @OneToMany(mappedBy = "user")
    @JsonIgnoreProperties(value = { "user", "handler", "hibernateLazyInitializer" }, allowSetters = true)
    private Set<Review> reviews;

    @ManyToMany()
    @JsonIgnoreProperties(value = { "users", "handler", "hibernateLazyInitializer" }, allowSetters = true)
    @JoinTable(name = "\"Card_User\"", joinColumns = @JoinColumn(name = "\"UserID\"", referencedColumnName = "\"ID\""), inverseJoinColumns = @JoinColumn(name = "\"CardID\"", referencedColumnName = "\"ID\""))
    private List<Card> cards;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFio() {
        return fio;
    }

    public void setFio(String fio) {
        this.fio = fio;
    }

    public Passport getPassport() {
        return passport;
    }

    public void setPassport(Passport passport) {
        this.passport = passport;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public Subscription getSubscription() {
        return subscription;
    }

    public void setSubscription(Subscription subscription) {
        this.subscription = subscription;
    }

    public Set<Review> getReviews() {
        return reviews;
    }

    public void setReviews(Set<Review> reviews) {
        this.reviews = reviews;
    }

    public List<Card> getCards() {
        return cards;
    }

    public void setCards(List<Card> cards) {
        this.cards = cards;
    }

}
