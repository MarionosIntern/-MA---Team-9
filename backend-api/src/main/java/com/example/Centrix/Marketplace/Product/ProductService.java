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

   
    public List<Product> findAllProducts(Long providerId, String category, String searchTerm) {
        // Normalize inputs
        boolean hasSearch = searchTerm != null && !searchTerm.trim().isBlank();
        String normalizedCategory = (category != null && !"all".equalsIgnoreCase(category))
                ? category.trim()
                : null;

        // Provider filter takes priority
        if (providerId != null) {
            List<Product> products = productRepository.findByProviderId(providerId);
            return hasSearch ? filterBySearch(products, searchTerm) : products;
        }

        // Category filter
        if (normalizedCategory != null) {
            List<Product> products = productRepository.findByCategoryIgnoreCase(normalizedCategory);
            return hasSearch ? filterBySearch(products, searchTerm) : products;
        }

        // Search-only
        if (hasSearch) {
            return productRepository.findByNameContainingIgnoreCaseOrDescriptionContainingIgnoreCase(
                    searchTerm, searchTerm);
        }

        return productRepository.findAll();
    }

    
    public Product findById(Long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Product not found: " + id));
    }

    
    // Create product
   
    public Product create(Product product) {
        sanitizeImageUrl(product);
        return productRepository.save(product);
    }

    
    // Update product
    
    public Product update(Long id, Product updated) {
        Product existing = findById(id);

        existing.setName(updated.getName());
        existing.setCategory(updated.getCategory());
        existing.setPrice(updated.getPrice());
        existing.setImageUrl(normalizeImageUrl(updated.getImageUrl()));
        existing.setDescription(updated.getDescription());
        existing.setStatus(updated.getStatus());

        return productRepository.save(existing);
    }

  
    // Delete product
   
    public void delete(Long id) {
        productRepository.deleteById(id);
    }

    private void sanitizeImageUrl(Product product) {
        product.setImageUrl(normalizeImageUrl(product.getImageUrl()));
    }

    private String normalizeImageUrl(String raw) {
        if (raw == null) {
            return null;
        }
        String trimmed = raw.trim();
        return trimmed.isBlank() ? null : trimmed;
    }

    private List<Product> filterBySearch(List<Product> source, String searchTerm) {
        String term = searchTerm.trim().toLowerCase();
        return source.stream()
                .filter(p ->
                        (p.getName() != null && p.getName().toLowerCase().contains(term)) ||
                        (p.getDescription() != null && p.getDescription().toLowerCase().contains(term)))
                .toList();
    }
}
