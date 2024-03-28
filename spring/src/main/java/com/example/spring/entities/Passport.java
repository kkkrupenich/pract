package com.example.spring.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import java.util.Date;

@Entity
@Table(name = "\"Passport\"")
@Data
public class Passport {

    @Id
    @Column(name = "\"ID\"")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "\"SerialNumber\"")
    private String serialNumber;

    @Column(name = "\"IdentificationNumber\"")
    private String identificationNumber;

    @Column(name = "\"Registration\"")
    private String registration;

    @Column(name = "\"IssueDate\"")
    private Date issueDate;

    @Column(name = "\"ExpirationDate\"")
    private Date expirationDate;
}
