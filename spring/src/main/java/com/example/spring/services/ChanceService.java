package com.example.spring.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.spring.entities.Chance;
import com.example.spring.repositories.ChanceRepository;

@Service
public class ChanceService {

    @Autowired
    ChanceRepository chanceRepository;

    public ChanceService() {

    }

    public List<Chance> getRoles() {
        return chanceRepository.findAll();
    }

    public Chance saveRole(Chance role) {
        return chanceRepository.save(role);
    }

    public ResponseEntity<String> deleteRole(Long id) {
        chanceRepository.deleteById(id);
        return ResponseEntity.ok("Todo deleted successfully!.");
    }
}
