package com.example.Centrix.Marketplace.Review;

import jakarta.persistence.*;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.time.LocalDateTime;

import com.example.Centrix.Marketplace.Customer.Customer;

@Entity
@Table(name = "reviews")
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @ManyToOne
    @JoinColumn(name = "customer_id", nullable = false)
    @JsonIgnoreProperties({"reviews", "subscriptions"})
    Customer customer;

    // Products/ProduceBox type not present in backend; omit relation to keep package compile-clean
    // Ratings and comments
    Double freshnessRating;
    Double deliveryRating;
    Double overallRating;

    @Column(columnDefinition = "TEXT")
    String comment;

    LocalDateTime createdAt = LocalDateTime.now();

    @Column(columnDefinition = "TEXT")
    String providerResponse;

    LocalDateTime providerResponseDate;

    public Review() {}
}


