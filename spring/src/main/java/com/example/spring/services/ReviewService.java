package com.example.spring.services;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.spring.entities.Game;
import com.example.spring.entities.Review;
import com.example.spring.entities.User;
import com.example.spring.repositories.GameRepository;
import com.example.spring.repositories.ReviewRepository;
import com.example.spring.repositories.UserRepository;

@Service
public class ReviewService {

    ReviewRepository reviewRepository;
    UserRepository userRepository;
    GameRepository gameRepository;

    public ReviewService(ReviewRepository reviewRepository, UserRepository userRepository,
            GameRepository gameRepository) {
        this.reviewRepository = reviewRepository;
        this.userRepository = userRepository;
        this.gameRepository = gameRepository;
    }

    public List<Review> getReviews() {
        return reviewRepository.findAll();
    }

    public Review getReviewById(Long id) {
        return reviewRepository.findById(id).get();
    }

    public List<Review> getReviewsByUserId(String id) {
        return reviewRepository.findByUserId(Long.parseLong(id));
    }

    public Review addReview(Review review, Long gameId, String id) {
        User user = userRepository.findById(Long.parseLong(id)).get();
        review.setUser(user);
        Game game = gameRepository.findById(gameId).get();
        review.setGame(game);
        return reviewRepository.save(review);
    }

    public ResponseEntity<String> deleteReview(Long id) {
        reviewRepository.deleteById(id);
        return ResponseEntity.ok("Todo deleted successfully!.");
    }
}
