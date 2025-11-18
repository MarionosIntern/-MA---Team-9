package com.example.Centrix.Marketplace.Product;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {

    List<Product> findByProviderId(Long providerId);

    List<Product> findByCategory(String category);

}
