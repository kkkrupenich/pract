package com.example.spring.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.spring.entities.Subscription;
import com.example.spring.services.SubscriptionService;

@RestController
public class SubscriptionController {

    @Autowired
    SubscriptionService subscriptionService;

    @GetMapping("subscriptions")
    public List<Subscription> getRoles() {
        return subscriptionService.getRoles();
    }

    @PostMapping("addsubscription")
    public Subscription addRole(@RequestBody Subscription role) {
        return subscriptionService.saveRole(role);
    }

    @DeleteMapping("deletesubscription/{id}")
    public ResponseEntity<String> deleteTodo(@PathVariable("id") Long id) {
        subscriptionService.deleteRole(id);
        return ResponseEntity.ok("Todo deleted successfully!.");
    }
}
