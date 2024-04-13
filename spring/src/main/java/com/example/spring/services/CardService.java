package com.example.spring.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
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

    public Card getCardById(Long id) throws NotFoundException {
        Optional<Card> card = cardRepository.findById(id);
        if (card.isEmpty()) {
            throw new NotFoundException();
        } 
        return card.get();
    }

    public Card getCardByNumber(Long number) throws NotFoundException {
        Optional<Card> card = cardRepository.findByNumber(number);
        if (card.isEmpty()) {
            throw new NotFoundException();
        }
        return card.get();
    }

    public Card addCard(Card card, String id) throws NotFoundException {
        Optional<User> userCheck = userRepository.findById(Long.parseLong(id));
        if (userCheck.isEmpty()) {
            throw new NotFoundException();
        }
        User user = userCheck.get();
        Card existedCard = getCardByNumber(card.getNumber());

        if (existedCard != null) {

            if (card.getId().equals(existedCard.getId())) {
                card.setUsers(existedCard.getUsers());
                return cardRepository.save(card);
            }

            List<User> users = existedCard.getUsers();
            if (users != null) {
                for (Card test : user.getCards()) {
                    if (test.equals(existedCard)) {
                        return null;
                    }
                }
            }
            users = new ArrayList<>();
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

    public ResponseEntity<String> deleteCard(Long id, String userId) throws NotFoundException {
        Optional<Card> cardCheck = cardRepository.findById(id);
        if (cardCheck.isEmpty()) {
            throw new NotFoundException();
        }
        Card card = cardCheck.get();
        List<User> users = card.getUsers();

        if (users.size() == 1) {
            cardRepository.deleteById(id);
            return ResponseEntity.ok("Card deleted");
        } else {
            Optional<User> userCheck = userRepository.findById(Long.parseLong(userId));
            if (userCheck.isEmpty()) {
                throw new NotFoundException();
            }
            User user = userCheck.get();
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
