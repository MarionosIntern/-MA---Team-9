package com.example.Centrix.Marketplace.Cart;

public class CartRequest {

    
    private Long productId;
    private double unitPrice;
    private int quantity;

    // 
    //  Constructors
    // 
    public CartRequest() {
       
    }

    public CartRequest(Long productId, double unitPrice, int quantity) {
        this.productId = productId;
        this.unitPrice = unitPrice;
        this.quantity = quantity;
    }

    
    //  Getters & Setters
    
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

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    
    public double getSubtotal() {
        return unitPrice * quantity;
    }

    @Override
    public String toString() {
        return "CartRequest{" +
                "productId=" + productId +
                ", unitPrice=" + unitPrice +
                ", quantity=" + quantity +
                '}';
    }
}
