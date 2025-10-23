package com.example.Centrix.Marketplace.Provider;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class ProviderService {
    private final ProviderRepository providerRepository;

    public Provider createProvider(Provider provider){
        if (providerRepository.existsByEmail(provider.getEmail())) {
            throw new IllegalArgumentException("Email already in use");
        }
        return providerRepository.save(provider);
    }

    public Provider updateProvider(Long id, Provider providerDetails){
        Provider provider = providerRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Provider not found with id: " + id));
        
        provider.setName(providerDetails.getName());
        if (!provider.getEmail().equals(providerDetails.getEmail()) && 
            providerRepository.existsByEmail(providerDetails.getEmail())) {
            throw new IllegalArgumentException("Email already in use");
        }

        provider.setEmail(providerDetails.getEmail());
        provider.setPhoneNumber(providerDetails.getPhoneNumber());

        return providerRepository.save(provider);
    }

    public Provider getProviderById(Long id){
        return providerRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Provider not found with id: " + id));
    }

    public Provider getProviderByEmail(String email){
        return providerRepository.findByEmail(email)
                .orElseThrow(() -> new EntityNotFoundException("Provider not found with email: " + email));
    }
}