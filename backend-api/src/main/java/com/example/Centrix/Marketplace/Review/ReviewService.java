package com.example.Centrix.Marketplace.Review;

import java.time.LocalDateTime;
import java.util.List;

import com.example.Centrix.Marketplace.Customer.Customer;
import com.example.Centrix.Marketplace.Customer.CustomerRepository;
import com.example.Centrix.Marketplace.Product.Product;
import com.example.Centrix.Marketplace.Product.ProductRepository;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import com.example.Centrix.Marketplace.Customer.Customer;
import com.example.Centrix.Marketplace.Customer.CustomerRepository;
import com.example.Centrix.Marketplace.Product.Product;
import com.example.Centrix.Marketplace.Product.ProductRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
@Transactional
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final CustomerRepository customerRepository;
    private final ProductRepository productRepository;

    public ReviewService(ReviewRepository reviewRepository,
                         CustomerRepository customerRepository,
                         ProductRepository productRepository) {
        this.reviewRepository = reviewRepository;
        this.customerRepository = customerRepository;
        this.productRepository = productRepository;
    }

    // =======================================
    // ✅ Get all reviews (for admin)
    // =======================================
    public List<Review> getAllReviews() {
        return reviewRepository.findAll();
    }

    // =======================================
    // ✅ Get review by ID
    // =======================================
    public Review getReviewById(Long id) {
        return reviewRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Review not found"));
    }

    // =======================================
    // ✅ Get all reviews for a customer
    // =======================================
    public List<Review> getAllForCustomer(Long customerId) {
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new EntityNotFoundException("Customer not found"));
        return reviewRepository.findByCustomer(customer);
    }

    // =======================================
    // ✅ Get all reviews for a product
    // =======================================
    public List<Review> getAllForProduct(Long productId) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new EntityNotFoundException("Product not found"));
        return reviewRepository.findByProduct(product);
    }

    // =======================================
    // ✅ Create new review
    // =======================================
    public Review createReview(Review review) {
        if (review.getCustomer() == null || review.getCustomer().getId() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Review must include a customer with an ID");
        }
        if (review.getProduct() == null || review.getProduct().getProductId == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Review must include a product with an ID");
        }

        Long customerId = review.getCustomer().getId();
        Long productId = review.getProduct().getProductId();

        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new EntityNotFoundException("Customer not found"));
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new EntityNotFoundException("Product not found"));

        double quality = review.getQualityRating() != null ? review.getQualityRating() : 0;
        double delivery = review.getDeliveryRating() != null ? review.getDeliveryRating() : 0;
        review.setOverallRating((quality + delivery) / 2);
        review.setCustomer(customer);
        review.setProduct(product);
        review.setCreatedAt(LocalDateTime.now());

        return reviewRepository.save(review);
    }

    // =======================================
    // ✅ Add provider response
    // =======================================
    public Review addProviderResponse(Long id, String response) {
        Review review = reviewRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Review not found"));

        review.setProviderResponse(response);
        review.setProviderResponseDate(LocalDateTime.now());
        return reviewRepository.save(review);
    }

    // =======================================
    // ✅ Delete review
    // =======================================
    public void deleteReview(Long id) {
        if (!reviewRepository.existsById(id)) {
            throw new EntityNotFoundException("Review not found");
        }
        reviewRepository.deleteById(id);
    }

    // =======================================
    // ✅ Ratings + Aggregations
    // =======================================
    public double getAverageOverallRating(Product product) {
        List<Review> reviews = reviewRepository.findByProduct(product);
        return reviews.stream()
                .mapToDouble(r -> r.getOverallRating() != null ? r.getOverallRating() : 0.0)
                .average().orElse(0.0);
    }

    public double getAverageQualityRating(Product product) {
        List<Review> reviews = reviewRepository.findByProduct(product);
        return reviews.stream()
                .mapToDouble(r -> r.getQualityRating() != null ? r.getQualityRating() : 0.0)
                .average().orElse(0.0);
    }

    public double getAverageDeliveryRating(Product product) {
        List<Review> reviews = reviewRepository.findByProduct(product);
        return reviews.stream()
                .mapToDouble(r -> r.getDeliveryRating() != null ? r.getDeliveryRating() : 0.0)
                .average().orElse(0.0);
    }

    // =======================================
    // ✅ Provider and Product-based filters
    // =======================================
    public List<Review> getReviewsByProduct(Product product) {
        return reviewRepository.findByProduct(product);
    }

    public List<Review> getReviewsByCustomer(Customer customer) {
        return reviewRepository.findByCustomer(customer);
    }

    public List<Review> getReviewsByProviderId(Long providerId) {
        return reviewRepository.findByProductProviderId(providerId);
    }

    // =======================================
    // ✅ Optional: Search by customer name
    // =======================================
    public List<Review> searchReviewsByCustomerName(String name) {
    List<Customer> customers = customerRepository.findByNameContainingIgnoreCase(name);
    return customers.stream()
            .flatMap(c -> reviewRepository.findByCustomer(c).stream())
            .toList();
}


}