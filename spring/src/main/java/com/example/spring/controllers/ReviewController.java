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

import com.example.spring.entities.Review;
import com.example.spring.services.ReviewService;

@RestController
public class ReviewController {

    @Autowired
    ReviewService reviewService;

    @GetMapping("reviews")
    public List<Review> getRoles() {
        return reviewService.getRoles();
    }

    @PostMapping("addreview")
    public Review addRole(@RequestBody Review role) {
        return reviewService.saveRole(role);
    }

    @DeleteMapping("deletereview/{id}")
    public ResponseEntity<String> deleteTodo(@PathVariable("id") Long id) {
        reviewService.deleteRole(id);
        return ResponseEntity.ok("Todo deleted successfully!.");
    }
}
