package com.example.spring.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.spring.entities.Subscription;
import com.example.spring.repositories.SubscriptionRepository;

@Service
public class SubscriptionService {

    @Autowired
    SubscriptionRepository subscriptionRepository;

    public SubscriptionService() {
        // Constructor is empty because any specific initialization logic is not needed
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
