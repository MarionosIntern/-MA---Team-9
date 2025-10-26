package com.example.Centrix.Marketplace.Provider;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/providers")
@RequiredArgsConstructor
public class ProviderController{
    private final ProviderService providerService;

    @postMapping
    public ResponseEntity<Farmer> createProvider(@Valid @RequestBody Provider provider){
        return ResponseEntity.ok(providerService.createProvider(provider));
    }

    @PostMapping("/{id}")
    public ResponseEntity<Provider> updateProvider(@PathVariable Long id, @Valid @RequestBody Provider providerDetails){
        return ResponseEntity.ok(providerService.updateProvider(id, providerDetails));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Provider> getProviderById(@PathVariable Long id){
        return ResponseEntity.ok(providerService.getProviderById(id));
    }
}