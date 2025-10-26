package com.example.Centrix.Marketplace.Provider;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;

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