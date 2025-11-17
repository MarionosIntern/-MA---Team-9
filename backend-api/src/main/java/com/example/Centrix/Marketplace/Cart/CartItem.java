package com.example.Centrix.Marketplace.Cart;

import jakarta.persistence.*;

@Entity
@Table(name = "cart_items")
public class CartItem {

    // ================================
    // 1️⃣ Fields
    // ================================
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private int quantity;

    // store product reference by id (no direct entity dependency yet)
    @Column(nullable = false)
    private Long productId;

    @Column(nullable = false)
    private double unitPrice;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cart_id", nullable = false)
    private Cart cart;

    // ================================
    // 2️⃣ Constructors
    // ================================
    public CartItem() {}

    public CartItem(Long productId, double unitPrice, int quantity) {
        this.productId = productId;
        this.unitPrice = unitPrice;
        this.quantity = quantity;
    }

    // ================================
    // 3️⃣ Getters & Setters
    // ================================
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }
}