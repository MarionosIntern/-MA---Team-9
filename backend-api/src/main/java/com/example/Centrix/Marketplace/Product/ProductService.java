package com.example.Centrix.Marketplace.Product;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    // -----------------------------
    // Find all products with filters
    // -----------------------------
    public List<Product> findAllProducts(Long providerId, String category) {

        if (providerId != null) {
            return productRepository.findByProviderId(providerId);
        }

        if (category != null) {
            return productRepository.findByCategory(category);
        }

        return productRepository.findAll();
    }

    // -----------------------------
    // Find product by ID
    // -----------------------------
    public Product findById(Long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Product not found: " + id));
    }

    // -----------------------------
    // Create product
    // -----------------------------
    public Product create(Product product) {
        return productRepository.save(product);
    }

    // -----------------------------
    // Update product
    // -----------------------------
    public Product update(Long id, Product updated) {
        Product existing = findById(id);

        existing.setName(updated.getName());
        existing.setCategory(updated.getCategory());
        existing.setPrice(updated.getPrice());
        existing.setDescription(updated.getDescription());
        existing.setStatus(updated.getStatus());

        return productRepository.save(existing);
    }

    // -----------------------------
    // Delete product
    // -----------------------------
    public void delete(Long id) {
        productRepository.deleteById(id);
    }
}
