package com.example.Centrix.Marketplace.Review;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.Centrix.Marketplace.Customer.Customer;
import com.example.Centrix.Marketplace.Product.Product;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {

    // ✅ Get all reviews for a specific customer
    List<Review> findByCustomer(Customer customer);

    // ✅ Get all reviews for a specific product
    List<Review> findByProduct(Product product);

    // ✅ Get all reviews by provider ID (linked via product)
    List<Review> findByProductProviderId(Long providerId);
}
