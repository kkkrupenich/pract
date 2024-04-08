package com.example.spring.services;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.spring.entities.Chance;
import com.example.spring.repositories.ChanceRepository;

@Service
public class ChanceService {

    ChanceRepository chanceRepository;

    public ChanceService(ChanceRepository chanceRepository) {
        this.chanceRepository = chanceRepository;
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
