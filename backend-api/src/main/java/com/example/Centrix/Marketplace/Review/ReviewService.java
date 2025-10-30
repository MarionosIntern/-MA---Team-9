package com.example.Centrix.Marketplace.Review;

import java.time.LocalDateTime;
import java.util.List;
import com.example.Centrix.Marketplace.Customer.Customer;
import com.example.Centrix.Marketplace.Product.Product;
// provider type not required here; we query by providerId (Long)

import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import com.example.Centrix.Marketplace.Customer.CustomerRepository;
import com.example.Centrix.Marketplace.Product.ProductRepository;

@Service
@Transactional
public class ReviewService {
    private final ReviewRepository reviewRepository;
    private final CustomerRepository customerRepository;
    private final ProductRepository productRepository;

    public ReviewService(ReviewRepository reviewRepository, CustomerRepository customerRepository, ProductRepository productRepository) {
        this.reviewRepository = reviewRepository;
        this.customerRepository = customerRepository;
        this.productRepository = productRepository;
    }

    public double getAverageOverallRating(Product product) {
        List<Review> reviews = reviewRepository.findByProduct(product);
        return reviews.stream()
                .mapToDouble(r -> r.overallRating != null ? r.overallRating : 0.0)
                .average().orElse(0.0);
    }

    public double getAverageQualityRating(Product product) {
        List<Review> reviews = reviewRepository.findByProduct(product);
        return reviews.stream()
                .mapToDouble(r -> r.qualityRating != null ? r.qualityRating : 0.0)
                .average().orElse(0.0);
    }

    public double getAverageDeliveryRating(Product product) {
        List<Review> reviews = reviewRepository.findByProduct(product);
        return reviews.stream()
                .mapToDouble(r -> r.deliveryRating != null ? r.deliveryRating : 0.0)
                .average().orElse(0.0);
    }

    public Review createReview(Review review) {
        // Basic validation: ensure customer and product references (or ids) are present
        if (review.customer == null || review.customer.getId() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Review must include a customer with an id");
        }
        if (review.product == null || review.product.getProductId() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Review must include a product with an id");
        }

        Long customerId = review.customer.getId();
        Long productId = review.product.getProductId();

        if (!customerRepository.existsById(customerId)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Customer not found");
        }
        if (!productRepository.existsById(productId)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found");
        }

        // replace the lightweight nested objects with managed entities to avoid detached references
        review.customer = customerRepository.findById(customerId).orElseThrow(() -> new EntityNotFoundException("Customer not found"));
        review.product = productRepository.findById(productId).orElseThrow(() -> new EntityNotFoundException("Product not found"));

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

    public List<Review> getReviewsByProduct(Product product) {
        return reviewRepository.findByProduct(product);
    }

    public List<Review> getReviewsByCustomer(Customer customer) {
        return reviewRepository.findByCustomer(customer);
    }

    public List<Review> getReviewsByProviderId(Long providerId) {
        // Product stores providerId; query reviews where review.product.providerId == providerId
        return reviewRepository.findByProductProviderId(providerId);
    }

    public List<Review> getAllReviews() {
        return reviewRepository.findAll();
    }
}
