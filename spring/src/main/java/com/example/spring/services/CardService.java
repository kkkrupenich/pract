package com.example.spring.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.spring.entities.Card;
import com.example.spring.entities.User;
import com.example.spring.repositories.CardRepository;
import com.example.spring.repositories.UserRepository;

@Service
public class CardService {

    @Autowired
    CardRepository cardRepository;
    @Autowired
    UserRepository userRepository;

    public CardService() {
        // Constructor is empty because any specific initialization logic is not needed
    }

    public List<Card> getCards() {
        return cardRepository.findAll();
    }

    public Card getCardById(Long id) {
        return cardRepository.findById(id).get();
    }

    public Card addCard(Card card, String id) {
        User user = userRepository.findById(Long.parseLong(id)).get();
        List<User> users = card.getUsers();
        users.add(user);
        card.setUsers(users);
        return cardRepository.save(card);
    }

    public ResponseEntity<String> deleteCard(Long id) {
        cardRepository.deleteById(id);
        return ResponseEntity.ok("Todo deleted successfully!.");
    }
}
