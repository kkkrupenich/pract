package com.example.spring.services;

import java.util.List;
import java.util.Optional;

import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
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

    public Review getReviewById(Long id) throws NotFoundException {
        Optional<Review> review = reviewRepository.findById(id);
        if (review.isEmpty()) {
            throw new NotFoundException();
        }
        return review.get();
    }

    public List<Review> getReviewsByUserId(String id) {
        return reviewRepository.findByUserId(Long.parseLong(id));
    }

    public Review addReview(Review review, Long gameId, String id) throws NotFoundException {
        Optional<User> user = userRepository.findById(Long.parseLong(id));
        if (user.isEmpty()) {
            throw new NotFoundException();
        }
        review.setUser(user.get());

        Optional<Game> game = gameRepository.findById(gameId);
        if (game.isEmpty()) {
            throw new NotFoundException();
        }
        review.setGame(game.get());

        return reviewRepository.save(review);
    }

    public Review updateReview(Long id, Review review) throws NotFoundException {
        Optional<Review> reviewOptional = reviewRepository.findById(id);
        if (reviewOptional.isEmpty()) {
            throw new NotFoundException();
        }

        Review oldReview = reviewOptional.get();
        review.setId(id);
        review.setUser(oldReview.getUser());
        review.setGame(oldReview.getGame());
        
        return reviewRepository.save(review);
    }

    public ResponseEntity<String> deleteReview(Long id) {
        reviewRepository.deleteById(id);
        return ResponseEntity.ok("Todo deleted successfully!.");
    }
}
