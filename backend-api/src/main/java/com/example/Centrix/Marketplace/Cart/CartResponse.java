package com.example.Centrix.Marketplace.Cart;

public class CartResponse {

    
    private Long cartId;
    private int itemCount;
    private double total;
    private String subscription;
    private Long providerId;

    
    //  Constructors
    
    public CartResponse() {
       
    }

    public CartResponse(Long cartId, int itemCount, double total, String subscription, Long providerId) {
        this.cartId = cartId;
        this.itemCount = itemCount;
        this.total = total;
        this.subscription = subscription;
        this.providerId = providerId;
    }

    
    //  Getters & Setters
    
    public Long getCartId() {
        return cartId;
    }

    public void setCartId(Long cartId) {
        this.cartId = cartId;
    }

    public int getItemCount() {
        return itemCount;
    }

    public void setItemCount(int itemCount) {
        this.itemCount = itemCount;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public String getSubscription() {
        return subscription;
    }

    public void setSubscription(String subscription) {
        this.subscription = subscription;
    }

    public Long getProviderId() {
        return providerId;
    }

    public void setProviderId(Long providerId) {
        this.providerId = providerId;
    }

     
    @Override
    public String toString() {
        return "CartResponse{" +
                "cartId=" + cartId +
                ", itemCount=" + itemCount +
                ", total=" + total +
                ", subscription='" + subscription + '\'' +
                ", providerId=" + providerId +
                '}';
    }
}
