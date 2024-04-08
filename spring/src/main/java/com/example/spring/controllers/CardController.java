package com.example.spring.controllers;

import java.security.Principal;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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

    CardService cardService;
    
    public CardController(CardService cardService) {
        this.cardService = cardService;
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("cards")
    public List<Card> getCards() {
        return cardService.getCards();
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping("addcard")
    public ResponseEntity<String> addCard(@RequestBody Card card, Principal principal) {
        Card check = cardService.addCard(card, principal.getName());
        if (check == null) 
            return ResponseEntity.ok("This card was already added");
        else
            return ResponseEntity.ok("Card added");
    }

    @PreAuthorize("isAuthenticated()")
    @DeleteMapping("deletecard/{id}")
    public ResponseEntity<String> deleteCard(@PathVariable("id") Long id, Principal principal) {
        return cardService.deleteCard(id, principal.getName());
    }
}
