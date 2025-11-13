package com.example.Centrix.Marketplace.Review;

import java.util.List;
import java.util.Map;
import java.util.HashMap;
import com.example.Centrix.Marketplace.Product.Product;
// Provider type not required in controller; we query by providerId (Long)

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.Centrix.Marketplace.Customer.Customer;
import com.example.Centrix.Marketplace.Customer.CustomerService;

@RestController
@RequestMapping("/api/reviews")
public class ReviewController {
    private final ReviewService reviewService;
    private final CustomerService customerService;

    public ReviewController(ReviewService reviewService, CustomerService customerService) {
        this.reviewService = reviewService;
        this.customerService = customerService;
    }

    @PostMapping
    public ResponseEntity<Review> createReview(@RequestBody Review review) {
        return ResponseEntity.ok(reviewService.createReview(review));
    }

    @GetMapping
    public ResponseEntity<List<Review>> getAllReviews() {
        return ResponseEntity.ok(reviewService.getAllReviews());
    }

    @PostMapping("/{id}/provider-response")
    public ResponseEntity<Review> addProviderResponse(@PathVariable("id") Long id, @RequestBody String response) {
        return ResponseEntity.ok(reviewService.addProviderResponse(id, response));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteReview(@PathVariable("id") Long id) {
        reviewService.deleteReview(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/product/{productId}")
    public ResponseEntity<List<Review>> getProductReviews(@PathVariable("productId") Long productId) {
        // will resolve Products via ProductsService in future; here we accept productId and construct a lightweight lookup
    Product product = new Product();
    product.setProductId(productId);
    return ResponseEntity.ok(reviewService.getReviewsByProduct(product));
    }

    @GetMapping("/product/{productId}/ratings")
    public ResponseEntity<Map<String, Double>> getProductRatings(@PathVariable("productId") Long productId) {
    Product product = new Product();
    product.setProductId(productId);
        Map<String, Double> ratings = new HashMap<>();
        ratings.put("overall", reviewService.getAverageOverallRating(product));
        ratings.put("quality", reviewService.getAverageQualityRating(product));
        ratings.put("delivery", reviewService.getAverageDeliveryRating(product));
        return ResponseEntity.ok(ratings);
    }

    @GetMapping("/provider/{providerId}")
    public ResponseEntity<List<Review>> getProviderReviews(@PathVariable("providerId") Long providerId) {
        // Product stores providerId; query reviews by that providerId directly
        return ResponseEntity.ok(reviewService.getReviewsByProviderId(providerId));
    }

    @GetMapping("/customer/{customerId}")
    public ResponseEntity<List<Review>> getCustomerReviews(@PathVariable("customerId") Long customerId) {
        Customer customer = customerService.getCustomerById(customerId);
        return ResponseEntity.ok(reviewService.getReviewsByCustomer(customer));
    }
}
