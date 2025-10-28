package com.example.Centrix.Marketplace.Review;

import java.time.LocalDateTime;
import java.util.List;
import com.example.Centrix.Marketplace.Customer.Customer;
import com.example.Centrix.Marketplace.Products.Products;
import com.example.Centrix.Marketplace.Provider.Provider;

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

    public double getAverageOverallRating(Products products) {
        List<Review> reviews = reviewRepository.findByProducts(products);
        return reviews.stream()
                .mapToDouble(r -> r.overallRating != null ? r.overallRating : 0.0)
                .average().orElse(0.0);
    }

    public double getAverageQualityRating(Products products) {
        List<Review> reviews = reviewRepository.findByProducts(products);
        return reviews.stream()
                .mapToDouble(r -> r.qualityRating != null ? r.qualityRating : 0.0)
                .average().orElse(0.0);
    }

    public double getAverageDeliveryRating(Products products) {
        List<Review> reviews = reviewRepository.findByProducts(products);
        return reviews.stream()
                .mapToDouble(r -> r.deliveryRating != null ? r.deliveryRating : 0.0)
                .average().orElse(0.0);
    }

    public Review createReview(Review review) {
        double qualityRating = review.qualityRating != null ? review.qualityRating : 0;
        double deliveryRating = review.deliveryRating != null ? review.deliveryRating : 0;

        review.overallRating = Double.valueOf(qualityRating + deliveryRating) / 2;
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

    public List<Review> getReviewsByProducts(Products products) {
        return reviewRepository.findByProducts(products);
    }

    public List<Review> getReviewsByCustomer(Customer customer) {
        return reviewRepository.findByCustomer(customer);
    }

    public List<Review> getReviewsByProvider(Provider provider) {
        return reviewRepository.findByProductsProvider(provider);
    }
}
