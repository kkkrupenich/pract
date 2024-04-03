package com.example.spring.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.spring.entities.Review;
import com.example.spring.entities.User;
import com.example.spring.repositories.ReviewRepository;
import com.example.spring.repositories.UserRepository;

@Service
public class ReviewService {

    @Autowired
    ReviewRepository reviewRepository;
    @Autowired
    UserRepository userRepository;

    public ReviewService() {
        // Constructor is empty because any specific initialization logic is not needed
    }

    public List<Review> getReviews() {
        return reviewRepository.findAll();
    }

    public Review getReviewById(Long id) {
        return reviewRepository.findById(id).get();
    }

    public Review addReview(Review review, String id) {
        User user = userRepository.findById(Long.parseLong(id)).get();
        review.setUser(user);
        return reviewRepository.save(review);
    }

    public ResponseEntity<String> deleteReview(Long id) {
        reviewRepository.deleteById(id);
        return ResponseEntity.ok("Todo deleted successfully!.");
    }
}
