package com.example.spring.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.spring.entities.Chance;

@Repository
public interface ChanceRepository extends JpaRepository<Chance, Long> {

}
