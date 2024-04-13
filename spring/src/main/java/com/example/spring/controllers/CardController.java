package com.example.spring.controllers;

import java.security.Principal;
import java.util.List;

import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.spring.entities.Card;
import com.example.spring.services.CardService;

@RestController
public class CardController {

    private final CardService cardService;

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
    public ResponseEntity<String> addCard(@RequestBody Card card, Principal principal) throws NotFoundException {
        Card check = cardService.addCard(card, principal.getName());
        return check == null ? ResponseEntity.ok("This card was already added")
                : ResponseEntity.ok("Card added");
    }

    @PreAuthorize("isAuthenticated()")
    @PutMapping("updatecard/{id}")
    public Card updateCard(@PathVariable("id") Long id, @RequestBody Card card) {
        return cardService.updateCard(id, card);
    }
    
    @PreAuthorize("isAuthenticated()")
    @DeleteMapping("deletecard/{id}")
    public ResponseEntity<String> deleteCard(@PathVariable("id") Long id, Principal principal)
            throws NotFoundException {
        return cardService.deleteCard(id, principal.getName());
    }
}
