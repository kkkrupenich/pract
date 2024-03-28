package com.example.spring.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.spring.entities.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    
}
