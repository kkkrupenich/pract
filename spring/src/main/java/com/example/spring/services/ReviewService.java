package com.example.spring.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.spring.entities.Review;
import com.example.spring.repositories.ReviewRepository;

@Service
public class ReviewService {

    @Autowired
    ReviewRepository reviewRepository;

    public ReviewService() {

    }

    public List<Review> getRoles() {
        return reviewRepository.findAll();
    }

    public Review saveRole(Review role) {
        return reviewRepository.save(role);
    }

    public ResponseEntity<String> deleteRole(Long id) {
        reviewRepository.deleteById(id);
        return ResponseEntity.ok("Todo deleted successfully!.");
    }
}
