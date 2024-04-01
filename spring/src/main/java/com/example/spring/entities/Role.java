package com.example.spring.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;
import java.util.Set;

@Entity
@Table(name = "\"Role\"")
@Data
public class Role {

    @Id
    @Column(name = "\"ID\"")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "\"RoleName\"")
    private String roleName;

    @OneToMany(mappedBy = "role")
    @JsonIgnoreProperties(value = { "role", "handler", "hibernateLazyInitializer" }, allowSetters = true)
    private Set<User> users;
}
