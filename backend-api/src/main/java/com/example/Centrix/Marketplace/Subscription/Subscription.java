package com.example.Centrix.Marketplace.Subscription;

import jakarta.persistence.*;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import java.time.LocalDateTime;

import com.example.Centrix.Marketplace.Customer.Customer;

@Entity
@Table(name = "subscriptions")
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class Subscription {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @ManyToOne
    @JoinColumn(name = "customer_id", nullable = false)
    @JsonIgnoreProperties("subscriptions")
    Customer customer;

    @Enumerated(EnumType.STRING)
    SubscriptionType type;

    LocalDateTime startDate;

    LocalDateTime endDate;

    boolean active = true;

    public Subscription() {
    }
}

enum SubscriptionType {
    ONE_TIME,
    MONTHLY
}