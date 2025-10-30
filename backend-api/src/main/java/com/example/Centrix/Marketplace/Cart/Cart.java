package com.example.Centrix.Marketplace.Cart;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "carts")
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    // store provider reference by id to avoid coupling if Provider entity is added later
    Long providerId;

    @OneToMany(mappedBy = "cart", cascade = CascadeType.ALL, orphanRemoval = true)
    List<CartItem> items = new ArrayList<>();

    // simple subscription name (placeholder). Integration with Subscription entity can be restored later.
    String subscription = "NONE";

    public Cart() {}

    public Cart(Long providerId) {
        this.providerId = providerId;
    }

    // compute total as sum(quantity * unitPrice) — subscription discounts can be applied later when Subscription is available
    public double getTotal() {
        double subtotal = items.stream()
                .mapToDouble(item -> item.unitPrice * item.quantity)
                .sum();
        return subtotal;
    }
}