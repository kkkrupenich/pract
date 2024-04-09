package com.example.spring.controllers;

import java.security.Principal;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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

    ReviewService reviewService;

    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @GetMapping("reviews")
    public List<Review> getReviews() {
        return reviewService.getReviews();
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("myreviews")
    public List<Review> getReviewsByUserId(Principal principal) {
        return reviewService.getReviewsByUserId(principal.getName());
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping("addreview/{id}")
    public Review addReview(@RequestBody Review review, @PathVariable Long id, Principal principal) {
        return reviewService.addReview(review, id, principal.getName());
    }

    @PreAuthorize("isAuthenticated()")
    @DeleteMapping("deletereview/{id}")
    public ResponseEntity<String> deleteReview(@PathVariable("id") Long id) {
        reviewService.deleteReview(id);
        return ResponseEntity.ok("Todo deleted successfully!.");
    }
}
