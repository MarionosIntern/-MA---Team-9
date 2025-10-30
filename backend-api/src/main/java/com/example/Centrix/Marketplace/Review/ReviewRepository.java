package com.example.Centrix.Marketplace.Review;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;   
import com.example.Centrix.Marketplace.Customer.Customer;
import com.example.Centrix.Marketplace.Product.Product;
// Provider type not required in repository signatures; we query by providerId on Product

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {
    List<Review> findByCustomer(Customer customer);
    List<Review> findByProduct(Product product);
    // Query reviews by the provider id stored on the Product entity (product.providerId)
    List<Review> findByProductProviderId(Long providerId);
}

