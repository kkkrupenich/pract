package com.example.spring.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.spring.entities.Card;
import com.example.spring.entities.User;
import com.example.spring.repositories.CardRepository;
import com.example.spring.repositories.UserRepository;

@Service
public class CardService {

    CardRepository cardRepository;
    UserRepository userRepository;

    public CardService(CardRepository cardRepository, UserRepository userRepository) {
        this.cardRepository = cardRepository;
        this.userRepository = userRepository;
    }

    public List<Card> getCards() {
        return cardRepository.findAll();
    }

    public Card getCardById(Long id) {
        return cardRepository.findById(id).get();
    }

    public Card getCardByNumber(Long number) {
        Optional<Card> card = cardRepository.findByNumber(number);
        if (card.isPresent()) {
            return card.get();
        } else {
            return null;
        }
    }

    public Card addCard(Card card, String id) {
        User user = userRepository.findById(Long.parseLong(id)).get();
        Card existedCard = getCardByNumber(card.getNumber());

        if (existedCard != null) {

            if (card.getId().equals(existedCard.getId())) {
                card.setUsers(existedCard.getUsers());
                return cardRepository.save(card);
            }

            List<User> users = existedCard.getUsers();
            if (users == null) {
                users = new ArrayList<>();
            } else {
                for (Card test : user.getCards()) {
                    if (test.equals(existedCard)) {
                        return null;
                    }
                }
            }
            users.add(user);
            existedCard.setUsers(users);

            List<Card> cards = user.getCards();
            if (cards == null) {
                cards = new ArrayList<>();
            }
            cards.add(existedCard);
            user.setCards(cards);

            return cardRepository.save(existedCard);
        }
        return cardRepository.save(card);
    }

    public ResponseEntity<String> deleteCard(Long id, String userId) {
        Card card = cardRepository.findById(id).get();
        List<User> users = card.getUsers();

        if (users.size() == 1) {
            cardRepository.deleteById(id);
            return ResponseEntity.ok("Card deleted");
        } else {
            User user = userRepository.findById(Long.parseLong(userId)).get();
            users.remove(user);
            card.setUsers(users);
            cardRepository.save(card);

            List<Card> cards = user.getCards();
            cards.remove(card);
            user.setCards(cards);
            userRepository.save(user);

            return ResponseEntity.ok("Card from this user deleted");
        }

    }
}
