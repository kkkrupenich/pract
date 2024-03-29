package com.example.spring.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.spring.entities.Game;
import com.example.spring.repositories.GameRepository;

@Service
public class GameService {

    @Autowired
    GameRepository gameRepository;

    public GameService() {
        // Constructor is empty because any specific initialization logic is not needed
    }

    public List<Game> getRoles() {
        return gameRepository.findAll();
    }

    public Game saveRole(Game role) {
        return gameRepository.save(role);
    }

    public ResponseEntity<String> deleteRole(Long id) {
        gameRepository.deleteById(id);
        return ResponseEntity.ok("Todo deleted successfully!.");
    }
}
