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
    public List<Card> getCards() {
        return cardService.getCards();
    }

    @PostMapping("addcard")
    public Card addCard(@RequestBody Card card) {
        return cardService.addCard(card);
    }

    @DeleteMapping("deletecard/{id}")
    public ResponseEntity<String> deleteCard(@PathVariable("id") Long id) {
        cardService.deleteCard(id);
        return ResponseEntity.ok("Todo deleted successfully!.");
    }
}
