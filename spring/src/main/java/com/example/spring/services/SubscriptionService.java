package com.example.spring.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.spring.entities.Subscription;
import com.example.spring.repositories.SubscriptionRepository;;

@Service
public class SubscriptionService {

    @Autowired
    SubscriptionRepository subscriptionRepository;

    public SubscriptionService() {

    }

    public List<Subscription> getRoles() {
        return subscriptionRepository.findAll();
    }

    public Subscription saveRole(Subscription role) {
        return subscriptionRepository.save(role);
    }

    public ResponseEntity<String> deleteRole(Long id) {
        subscriptionRepository.deleteById(id);
        return ResponseEntity.ok("Todo deleted successfully!.");
    }
}
