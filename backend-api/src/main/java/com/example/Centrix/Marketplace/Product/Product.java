package com.example.Centrix.Marketplace.Product;

import com.example.Centrix.Marketplace.Provider.Provider;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;


@Entity
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private Long productId;

   
    @Column(name =  "provider_id", nullable = false)
    private Long providerId;
   

    @OneToOne
    @JoinColumn(name =  "provider", nullable = false)
    private Provider provider;

    @Column( name = "name", nullable = false, length = 255)
    private String name;

    @Column(name = "category", nullable = false) 
    private String category;

    @Column(name = "price", nullable = false)
    private double price;

    @Column(name = "description", nullable = false, length = 1000)
    private String description;

    @Column(name = "status", nullable = false)
    private String status; 

    public Product() {}

    public Product(Long productId, Long providerId, String name, String category, double price, String description, String status) {
        this.productId = productId;
        this.providerId = providerId;
        this.name = name;
        this.category = category;
        this.price = price;
        this.description = description;
        this.status = status;
    }

    @Override
    public String toString() {
        return "Product{" +
                "productId=" + productId +
                ", providerId=" + providerId +
                ", name='" + name + '\'' +
                ", category='" + category + '\'' +
                ", price=" + price +
                ", description='" + description + '\'' +
                ", status='" + status + '\'' +
                '}';
    }

    // Getters and Setters
    public Long getProductId() {return productId;}
    public void setProductId(Long productId) {this.productId = productId;}
    
    public Long getProviderId() {return providerId;}
    public void setProviderId(Long providerId) {this.providerId = providerId;}
   
    public String getName() {return name;}
    public void setName(String name) {this.name = name;}

    public String getCategory() {return category;}
    public void setCategory(String category) {this.category = category;}
    
    public double getPrice() {return price;}
    public void setPrice(double price) {this.price = price;}
    
    public String getDescription() {return description;}
    public void setDescription(String description) {this.description = description;}
    
    public String getStatus() {return status;}
    public void setStatus(String status) {this.status = status;}
}

