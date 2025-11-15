package com.example.Centrix.Marketplace.Subscription;

import com.example.Centrix.Marketplace.Customer.Customer;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "subscriptions")
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class Subscription {

    // ================================
    // 1️⃣ Fields
    // ================================
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY) // each subscription belongs to one customer
    @JoinColumn(name = "customer_id", nullable = false)
    @JsonIgnoreProperties("subscriptions") // prevent circular reference in JSON
    private Customer customer;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private SubscriptionType type;

    @Column(nullable = false)
    private LocalDateTime startDate;

    private LocalDateTime endDate;

    @Column(nullable = false)
    private boolean active = true;

    // ================================
    // 2️⃣ Constructors
    // ================================
    public Subscription() {
        // JPA requires a no-arg constructor
    }

    public Subscription(Customer customer, SubscriptionType type, LocalDateTime startDate, LocalDateTime endDate) {
        this.customer = customer;
        this.type = type;
        this.startDate = startDate;
        this.endDate = endDate;
        this.active = true;
    }

    // ================================
    // 3️⃣ Getters and Setters
    // ================================
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public SubscriptionType getType() {
        return type;
    }

    public void setType(SubscriptionType type) {
        this.type = type;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }

    public LocalDateTime getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDateTime endDate) {
        this.endDate = endDate;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    // ================================
    // 4️⃣ toString (for debugging/logging)
    // ================================
    @Override
    public String toString() {
        return "Subscription{" +
                "id=" + id +
                ", customerId=" + (customer != null ? customer.getId() : null) +
                ", type=" + type +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", active=" + active +
                '}';
    }
}
