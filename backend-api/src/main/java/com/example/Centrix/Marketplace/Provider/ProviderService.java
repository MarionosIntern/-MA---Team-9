package com.example.Centrix.Marketplace.Provider;

import java.util.List;

import org.springframework.stereotype.Service;

import jakarta.persistence.EntityNotFoundException;

@Service
public class ProviderService {

    private final ProviderRepository providerRepository;

    public ProviderService(ProviderRepository providerRepository) {
        this.providerRepository = providerRepository;
    }

    // CREATE PROVIDER (Signup)
    public Provider createProvider(Provider provider) {
        return providerRepository.save(provider);
    }

    // AUTHENTICATE (Signin)
    public Provider authenticate(String email, String password) {
        Provider provider = providerRepository.findByEmail(email);
        if (provider == null) {
            throw new EntityNotFoundException("Provider not found with email: " + email);
        }
        if (!provider.getPassword().equals(password)) {
            throw new IllegalArgumentException("Invalid password");
        }
        return provider;
    }

    // GET PROVIDER BY ID (Home, Edit Profile)
    public Provider getProviderById(Long id) {
        return providerRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Provider not found"));
    }

    // UPDATE PROVIDER (Edit Profile)
    public Provider updateProvider(Long id, Provider updatedData) {
        Provider provider = getProviderById(id);

        provider.setName(updatedData.getName());
        provider.setEmail(updatedData.getEmail());
        provider.setAddress(updatedData.getAddress());
        provider.setPhoneNumber(updatedData.getPhoneNumber());
        provider.setPassword(updatedData.getPassword());

        return providerRepository.save(provider);
    }

    // LIST ALL PROVIDERS
    public List<Provider> getAllProviders() {
        return providerRepository.findAll();
    }

    // DELETE PROVIDER
    public void deleteProvider(Long id) {
        providerRepository.deleteById(id);
    }

    // FIND BY EMAIL (helper for login)
    public Provider findByEmail(String email) {
        return providerRepository.findByEmail(email);
    }
}
