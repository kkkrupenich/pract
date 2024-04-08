package com.example.spring.services;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.spring.entities.Game;
import com.example.spring.repositories.GameRepository;

@Service
public class GameService {

    GameRepository gameRepository;

    public GameService(GameRepository gameRepository) {
        this.gameRepository = gameRepository;
    }

    public List<Game> getGames() {
        return gameRepository.findAll();
    }

    public Game getGameById(Long id) {
        return gameRepository.findById(id).get();
    }

    public Game addGame(Game game) {
        return gameRepository.save(game);
    }

    public ResponseEntity<String> deleteGame(Long id) {
        gameRepository.deleteById(id);
        return ResponseEntity.ok("Todo deleted successfully!.");
    }
}
