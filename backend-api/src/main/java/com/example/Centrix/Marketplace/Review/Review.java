package com.example.Centrix.Marketplace.Review;

import jakarta.persistence.*;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.time.LocalDateTime;
import com.example.Centrix.Marketplace.Customer.Customer;
import com.example.Centrix.Marketplace.Product.Product;

@Entity
@Table(name = "reviews")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // ----- Relationships -----
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id", nullable = false)
    @JsonIgnoreProperties({"reviews", "subscriptions"})
    private Customer customer;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id", nullable = false)
    @JsonIgnoreProperties("reviews")
    private Product product;

    // ----- Ratings -----
    @Column
    private Double qualityRating;

    @Column
    private Double deliveryRating;

    @Column
    private Double overallRating;

    // ----- Comments and responses -----
    @Column(columnDefinition = "TEXT")
    private String comment;

    @Column(columnDefinition = "TEXT")
    private String providerResponse;

    // ----- Dates -----
    private LocalDateTime createdAt = LocalDateTime.now();
    private LocalDateTime providerResponseDate;

    // ----- Constructors -----
    public Review() {}

    public Review(Customer customer, Product product, Double qualityRating,
                  Double deliveryRating, String comment) {
        this.customer = customer;
        this.product = product;
        this.qualityRating = qualityRating;
        this.deliveryRating = deliveryRating;
        this.comment = comment;
        this.overallRating = (qualityRating + deliveryRating) / 2;
        this.createdAt = LocalDateTime.now();
    }

    // ----- Getters and setters -----
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Customer getCustomer() { return customer; }
    public void setCustomer(Customer customer) { this.customer = customer; }

    public Product getProduct() { return product; }
    public void setProduct(Product product) { this.product = product; }

    public Double getQualityRating() { return qualityRating; }
    public void setQualityRating(Double qualityRating) { this.qualityRating = qualityRating; }

    public Double getDeliveryRating() { return deliveryRating; }
    public void setDeliveryRating(Double deliveryRating) { this.deliveryRating = deliveryRating; }

    public Double getOverallRating() { return overallRating; }
    public void setOverallRating(Double overallRating) { this.overallRating = overallRating; }

    public String getComment() { return comment; }
    public void setComment(String comment) { this.comment = comment; }

    public String getProviderResponse() { return providerResponse; }
    public void setProviderResponse(String providerResponse) { this.providerResponse = providerResponse; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }

    public LocalDateTime getProviderResponseDate() { return providerResponseDate; }
    public void setProviderResponseDate(LocalDateTime providerResponseDate) { this.providerResponseDate = providerResponseDate; }

    @Override
    public String toString() {
        return "Review{" +
                "id=" + id +
                ", customer=" + (customer != null ? customer.getName() : null) +
                ", product=" + (product != null ? product.getProductId() : null) +
                ", overallRating=" + overallRating +
                ", comment='" + comment + '\'' +
                ", createdAt=" + createdAt +
                '}';
    }
}
