package com.example.spring.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.spring.entities.Card;

@Repository
public interface CardRepository extends JpaRepository<Card, Long> {

}
