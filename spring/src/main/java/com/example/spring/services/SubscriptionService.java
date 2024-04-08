package com.example.spring.services;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.spring.entities.Subscription;
import com.example.spring.repositories.SubscriptionRepository;

@Service
public class SubscriptionService {

    SubscriptionRepository subscriptionRepository;

    public SubscriptionService(SubscriptionRepository subscriptionRepository) {
        this.subscriptionRepository = subscriptionRepository;
    }

    public List<Subscription> getSubscriptions() {
        return subscriptionRepository.findAll();
    }

    public Subscription getSubscriptionById(Long id) {
        return subscriptionRepository.findById(id).get();
    }

    public Subscription addSubscription(Subscription subscription) {
        return subscriptionRepository.save(subscription);
    }

    public ResponseEntity<String> deleteSubscription(Long id) {
        subscriptionRepository.deleteById(id);
        return ResponseEntity.ok("Todo deleted successfully!.");
    }
}
