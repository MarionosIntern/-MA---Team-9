package com.example.Centrix.Marketplace.Review;

import java.time.LocalDateTime;
import java.util.List;
import com.example.Centrix.Marketplace.Customer.Customer;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ReviewService {
    private final ReviewRepository reviewRepository;

    public ReviewService(ReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;
    }

    public Review createReview(Review review) {
        double freshnessRating = review.freshnessRating != null ? review.freshnessRating : 0;
        double deliveryRating = review.deliveryRating != null ? review.deliveryRating : 0;

        review.overallRating = Double.valueOf(freshnessRating + deliveryRating) / 2;
        review.createdAt = LocalDateTime.now();
        return reviewRepository.save(review);
    }

    public Review addFarmerResponse(Long id, String response) {
        Review review = reviewRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Review not found"));

        review.providerResponse = response;
        review.providerResponseDate = LocalDateTime.now();
        return reviewRepository.save(review);
    }

    public void deleteReview(Long id) {
        if (!reviewRepository.existsById(id)) {
            throw new EntityNotFoundException("Review not found");
        }
        reviewRepository.deleteById(id);
    }

    public List<Review> getReviewsByCustomer(Customer customer) {
        return reviewRepository.findByCustomer(customer);
    }
}
