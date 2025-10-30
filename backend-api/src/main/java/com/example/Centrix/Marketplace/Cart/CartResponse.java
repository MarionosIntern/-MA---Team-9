package com.example.Centrix.Marketplace.Cart;

public class CartResponse {
    public Long cartId;
    public int itemCount;
    public double total;
    public String subscription;
    public Long providerId;

    public CartResponse() {}

    public CartResponse(Long cartId, int itemCount, double total, String subscription, Long providerId) {
        this.cartId = cartId;
        this.itemCount = itemCount;
        this.total = total;
        this.subscription = subscription;
        this.providerId = providerId;
    }
}
