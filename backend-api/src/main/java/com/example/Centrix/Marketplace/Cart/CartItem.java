package com.example.Centrix.Marketplace.Cart;

import jakarta.persistence.*;

@Entity
@Table(name = "cart_items")
public class CartItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    int quantity;

    // store product reference by id and remembered unit price to avoid coupling until Product entity exists
    Long productId;
    double unitPrice;

    @ManyToOne
    @JoinColumn(name = "cart_id")
    Cart cart;

    public CartItem() {}

    public CartItem(Long productId, double unitPrice, int quantity) {
        this.productId = productId;
        this.unitPrice = unitPrice;
        this.quantity = quantity;
    }
}