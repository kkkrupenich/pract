package com.example.spring.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.spring.entities.Passport;

@Repository
public interface PassportRepository extends JpaRepository<Passport, Long> {

}
