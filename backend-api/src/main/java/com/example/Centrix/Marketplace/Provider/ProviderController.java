package com.example.Centrix.Marketplace.Provider;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;

@SuppressWarnings("rawtypes")
@RestController
@RequestMapping("/api/providers")
public class ProviderController {

    
    private final ProviderService providerService;

    public ProviderController(ProviderService providerService) {
        this.providerService = providerService;
    }

    // CREATE
    @PostMapping
    public ResponseEntity<Provider> createProvider(@Valid @RequestBody Provider provider) {
        Provider saved = providerService.createProvider(provider);
        return ResponseEntity.ok(saved);
    }

    // UPDATE
    @PutMapping("/{id}")
    public ResponseEntity<Provider> updateProvider(
            @PathVariable Long id,
            @Valid @RequestBody Provider providerDetails) {
        Provider updated = providerService.updateProvider(id, providerDetails);
        return ResponseEntity.ok(updated);
    }

    // READ by ID
    @GetMapping("/{id}")
    public ResponseEntity<Provider> getProviderById(@PathVariable Long id) {
        Provider provider = providerService.getProviderById(id);
        return ResponseEntity.ok(provider);
    }

    // READ BY NAME
    @GetMapping("/{id}/name")
    public ResponseEntity<String> getProviderByName(@PathVariable Long id){
        return ResponseEntity.ok(providerService.getProviderById(id).getName());
    }

    // UPDATE NAME
    @PutMapping("/{id}/name")
    public ResponseEntity<Void> setProviderName(@PathVariable Long id, @RequestBody String name){
        Provider p = providerService.getProviderById(id);
        p.setName(name);
        providerService.updateProvider(id, p);
        return ResponseEntity.noContent().build();
    }


    // READ all (optional)
    @GetMapping
    public ResponseEntity<List<Provider>> getAllProviders() {
        List<Provider> list = providerService.getAll();
        return ResponseEntity.ok(list);
    }

    // DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProvider(@PathVariable Long id) {
        providerService.delete(id);
        return ResponseEntity.noContent().build();
    }
}