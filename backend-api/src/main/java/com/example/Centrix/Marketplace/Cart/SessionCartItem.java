package com.example.Centrix.Marketplace.Cart;

public class SessionCartItem {

    private Long productId;
    private String productName;
    private double unitPrice;
    private int quantity;
    private String imageUrl;

    public SessionCartItem() {
    }

    public SessionCartItem(Long productId, String productName, double unitPrice, int quantity, String imageUrl) {
        this.productId = productId;
        this.productName = productName;
        this.unitPrice = unitPrice;
        this.quantity = quantity;
        this.imageUrl = imageUrl;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public double getSubtotal() {
        return unitPrice * quantity;
    }

    public void incrementQuantity(int amount) {
        this.quantity += amount;
    }
}
