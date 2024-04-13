package com.example.spring.services;

import java.util.List;
import java.util.Optional;

import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
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

    public Game getGameById(Long id) throws NotFoundException {
        Optional<Game> game = gameRepository.findById(id);
        if (game.isEmpty())
            throw new NotFoundException();
        return game.get();
    }

    public Game addGame(Game game) {
        return gameRepository.save(game);
    }

    public Game updateGame(Long id, Game game) {
        game.setId(id);
        return gameRepository.save(game);
    }

    public ResponseEntity<String> deleteGame(Long id) {
        gameRepository.deleteById(id);
        return ResponseEntity.ok("Todo deleted successfully!.");
    }
}
