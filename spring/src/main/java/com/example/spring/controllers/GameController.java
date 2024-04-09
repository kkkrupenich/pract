package com.example.spring.controllers;

import java.util.List;

import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.spring.entities.Game;
import com.example.spring.services.GameService;

@RestController
public class GameController {

    GameService gameService;

    public GameController(GameService gameService) {
        this.gameService = gameService;
    }

    @GetMapping("games")
    public List<Game> getGames() {
        return gameService.getGames();
    }

    @GetMapping("game/{id}")
    public Game getGameById(@PathVariable Long id) throws NotFoundException {
        return gameService.getGameById(id);
    }

    @PreAuthorize("hasAuthority('Admin')")
    @PostMapping("addgame")
    public Game addGame(@RequestBody Game game) {
        return gameService.addGame(game);
    }

    @PreAuthorize("hasAuthority('Admin')")
    @DeleteMapping("deletegame/{id}")
    public ResponseEntity<String> deleteGame(@PathVariable("id") Long id) {
        gameService.deleteGame(id);
        return ResponseEntity.ok("Todo deleted successfully!.");
    }
}
