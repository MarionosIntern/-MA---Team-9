package com.example.Centrix.Marketplace.Review;

import java.util.List;
import java.util.Map;
import java.util.HashMap;
import com.example.Centrix.Marketplace.Product.Product;
import com.example.Centrix.Marketplace.Provider.Provider;

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

    @PostMapping("/{id}/provider-response")
    public ResponseEntity<Review> addProviderResponse(@PathVariable Long id, @RequestBody String response) {
        return ResponseEntity.ok(reviewService.addFarmerResponse(id, response));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteReview(@PathVariable Long id) {
        reviewService.deleteReview(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/product/{productId}")
    public ResponseEntity<List<Review>> getProductReviews(@PathVariable Long productId) {
        // will resolve Products via ProductsService in future; here we accept productId and construct a lightweight lookup
    Product product = new Product();
    product.setProductId(productId);
    return ResponseEntity.ok(reviewService.getReviewsByProduct(product));
    }

    @GetMapping("/product/{productId}/ratings")
    public ResponseEntity<Map<String, Double>> getProductRatings(@PathVariable Long productId) {
    Product product = new Product();
    product.setProductId(productId);
        Map<String, Double> ratings = new HashMap<>();
        ratings.put("overall", reviewService.getAverageOverallRating(product));
        ratings.put("quality", reviewService.getAverageQualityRating(product));
        ratings.put("delivery", reviewService.getAverageDeliveryRating(product));
        return ResponseEntity.ok(ratings);
    }

    @GetMapping("/provider/{providerId}")
    public ResponseEntity<List<Review>> getProviderReviews(@PathVariable Long providerId) {
    Provider provider = new Provider();
    provider.setId(providerId);
    return ResponseEntity.ok(reviewService.getReviewsByProvider(provider));
    }

    @GetMapping("/customer/{customerId}")
    public ResponseEntity<List<Review>> getCustomerReviews(@PathVariable Long customerId) {
        Customer customer = customerService.getCustomerById(customerId);
        return ResponseEntity.ok(reviewService.getReviewsByCustomer(customer));
    }
}
