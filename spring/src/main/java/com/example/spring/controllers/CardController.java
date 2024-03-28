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

import com.example.spring.entities.Card;
import com.example.spring.services.CardService;

@RestController
public class CardController {

    @Autowired
    CardService cardService;

    @GetMapping("cards")
    public List<Card> getRoles() {
        return cardService.getRoles();
    }

    @PostMapping("addcard")
    public Card addRole(@RequestBody Card role) {
        return cardService.saveRole(role);
    }

    @DeleteMapping("deletecard/{id}")
    public ResponseEntity<String> deleteTodo(@PathVariable("id") Long id) {
        cardService.deleteRole(id);
        return ResponseEntity.ok("Todo deleted successfully!.");
    }
}
