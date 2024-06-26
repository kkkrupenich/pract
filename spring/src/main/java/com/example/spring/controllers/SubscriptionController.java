package com.example.spring.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.spring.entities.Subscription;
import com.example.spring.services.SubscriptionService;

@RestController
public class SubscriptionController {

    private final SubscriptionService subscriptionService;

    public SubscriptionController(SubscriptionService subscriptionService) {
        this.subscriptionService = subscriptionService;
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("subscriptions")
    public List<Subscription> getSubscriptions() {
        return subscriptionService.getSubscriptions();
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping("addsubscription")
    public Subscription addSubscription(@RequestBody Subscription subscription) {
        return subscriptionService.addSubscription(subscription);
    }

    @PreAuthorize("isAuthenticated()")
    @PutMapping("updatesubscription/{id}")
    public Subscription updateSubscription(@PathVariable("id") Long id, @RequestBody Subscription subscription) {
        return subscriptionService.updateSubscription(id, subscription);
    }

    @PreAuthorize("isAuthenticated()")
    @DeleteMapping("deletesubscription/{id}")
    public ResponseEntity<String> deleteSubscription(@PathVariable("id") Long id) {
        subscriptionService.deleteSubscription(id);
        return ResponseEntity.ok("Todo deleted successfully!.");
    }
}
