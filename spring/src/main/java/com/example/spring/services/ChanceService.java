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
        // Constructor is empty because any specific initialization logic is not needed
    }

    public List<Chance> getChance() {
        return chanceRepository.findAll();
    }

    public Chance getChanceById(Long id) {
        return chanceRepository.findById(id).get();
    }

    public Chance addChance(Chance chance) {
        return chanceRepository.save(chance);
    }

    public ResponseEntity<String> deleteChance(Long id) {
        chanceRepository.deleteById(id);
        return ResponseEntity.ok("Todo deleted successfully!.");
    }
}
