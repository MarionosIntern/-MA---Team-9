package com.example.Centrix.Marketplace.Cart;

public class CartRequest {
    public Long productId;
    public double unitPrice;
    public int quantity;

    public CartRequest() {}

    public CartRequest(Long productId, double unitPrice, int quantity) {
        this.productId = productId;
        this.unitPrice = unitPrice;
        this.quantity = quantity;
    }
}
