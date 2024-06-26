package com.example.spring.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.spring.entities.Game;

@Repository
public interface GameRepository extends JpaRepository<Game, Long> {

}
