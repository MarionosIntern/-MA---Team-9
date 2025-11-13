package com.example.Centrix.Marketplace.Product;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class ProductService{
    private final ProductRepository repo;

    public ProductService(ProductRepository repo) {
        this.repo = repo;
    }

    public List<Product> findAllProducts(Long providerId, String category ){
        if (providerId != null) {
            return repo.findByProviderId(providerId);
        } else if (category != null) {
            return repo.findByCategory(category);
        } else {
            return repo.findAll();
        }
    }

    public Product findById(Long productId){
        return repo.findById(productId).orElseThrow(() -> new RuntimeException("Product not found" + productId));
    }

    public Product create(Product p){
        p.setProductId(null);
        return repo.save(p);
    }

    public Product update(Long id, Product p){
        Product existingProduct = findById(id);
        existingProduct.setName(p.getName());
        existingProduct.setCategory(p.getCategory());
        existingProduct.setPrice(p.getPrice());
        existingProduct.setDescription(p.getDescription());
        existingProduct.setStatus(p.getStatus());
        return repo.save(existingProduct);
    }
    
    public void delete(Long id){
        Product existingProduct = findById(id);
        repo.delete(existingProduct);
    }

    public List<Product> getAllProducts(){
        return repo.findAll();
    }

}
