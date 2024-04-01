package com.example.spring.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;
import java.util.Date;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


@Entity
@Table(name = "\"Subscription\"")
@Data
public class Subscription {

    @Id
    @Column(name = "\"ID\"")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "\"Status\"")
    private boolean status;

    @Column(name = "\"ExpirationDate\"")
    private Date expirationDate;

    @OneToMany(mappedBy = "subscription")
    @JsonIgnoreProperties(value = { "subscription", "handler", "hibernateLazyInitializer" }, allowSetters = true)
    private Set<User> users;
}
