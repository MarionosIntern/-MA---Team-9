package com.example.Centrix.Marketplace.Review;

import jakarta.persistence.*;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import static com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility.ANY;
import java.time.LocalDateTime;

import com.example.Centrix.Marketplace.Customer.Customer;
import com.example.Centrix.Marketplace.Product.Product;


@Entity
@Table(name = "reviews")
@JsonAutoDetect(fieldVisibility = ANY)
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @ManyToOne
    @JoinColumn(name = "customer_id", nullable = false)
    @JsonIgnoreProperties({"reviews", "subscriptions"})
    Customer customer;

    // Link to the Products entity (will exist in the Products package)
    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    @JsonIgnoreProperties("reviews")
    Product product;

    // Ratings and comments
    Double qualityRating;
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


