package com.example.Centrix.Marketplace.Cart;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "carts")
public class Cart {

   
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // store provider reference by id to avoid coupling
    @Column(nullable = false)
    private Long providerId;

    @OneToMany(mappedBy = "cart", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<CartItem> items = new ArrayList<>();

    // simple subscription name (placeholder)
    @Column(nullable = false)
    private String subscription = "NONE";

   
    public Cart() {
    }

    public Cart(Long providerId) {
        this.providerId = providerId;
    }

    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getProviderId() {
        return providerId;
    }

    public void setProviderId(Long providerId) {
        this.providerId = providerId;
    }

    public List<CartItem> getItems() {
        return items;
    }

    public void setItems(List<CartItem> items) {
        this.items = items;
    }

    public String getSubscription() {
        return subscription;
    }

    public void setSubscription(String subscription) {
        this.subscription = subscription;
    }

    
    public double getTotal() {
        return items.stream()
                .mapToDouble(item -> item.getUnitPrice() * item.getQuantity())
                .sum();
    }

    
    public void addItem(CartItem item) {
        items.add(item);
        item.setCart(this);
    }

    
    @Override
    public String toString() {
        return "Cart{" +
                "id=" + id +
                ", providerId=" + providerId +
                ", items=" + items.size() +
                ", subscription='" + subscription + '\'' +
                ", total=" + getTotal() +
                '}';
    }
}
