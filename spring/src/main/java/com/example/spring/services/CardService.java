package com.example.spring.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.spring.entities.Card;
import com.example.spring.repositories.CardRepository;

@Service
public class CardService {

    @Autowired
    CardRepository cardRepository;

    public CardService() {

    }

    public List<Card> getRoles() {
        return cardRepository.findAll();
    }

    public Card saveRole(Card role) {
        return cardRepository.save(role);
    }

    public ResponseEntity<String> deleteRole(Long id) {
        cardRepository.deleteById(id);
        return ResponseEntity.ok("Todo deleted successfully!.");
    }
}
