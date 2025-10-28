package com.example.Centrix.Marketplace.Review;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;   
import com.example.Centrix.Marketplace.Customer.Customer;
import com.example.Centrix.Marketplace.Products.Products;
import com.example.Centrix.Marketplace.Provider.Provider;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {
    List<Review> findByCustomer(Customer customer);
    List<Review> findByProducts(Products products);
    List<Review> findByProductsProvider(Provider provider);
}

