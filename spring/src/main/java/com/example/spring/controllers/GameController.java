package com.example.spring.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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

    @Autowired
    GameService gameService;

    @GetMapping("games")
    public List<Game> getRoles() {
        return gameService.getRoles();
    }

    @PostMapping("addgame")
    public Game addRole(@RequestBody Game role) {
        return gameService.saveRole(role);
    }

    @DeleteMapping("deletegame/{id}")
    public ResponseEntity<String> deleteTodo(@PathVariable("id") Long id) {
        gameService.deleteRole(id);
        return ResponseEntity.ok("Todo deleted successfully!.");
    }
}
