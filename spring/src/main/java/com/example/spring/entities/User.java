package com.example.spring.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
    private String password;

    @Column(name = "\"FIO\"")
    private String fio;

    @Column(name = "\"PassportID\"")
    private Long passportId;

    @Column(name = "\"RoleID\"")
    private Long roleId;

    @Column(name = "\"Balance\"")
    private double balance;

    @Column(name = "\"SubscriptionID\"")
    private Long subscriptionId;
}
