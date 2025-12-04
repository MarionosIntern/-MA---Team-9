package com.example.Centrix.Marketplace.Provider;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
public class ProviderService {

    private final ProviderRepository providerRepository;

    // ✅ Constructor Injection (prevents your earlier compilation error)
    public ProviderService(ProviderRepository providerRepository) {
        this.providerRepository = providerRepository;
    }

    // ---------------------------------------------------------
    // CREATE PROVIDER (Signup)
    // ---------------------------------------------------------
    public Provider createProvider(Provider provider) {
        return providerRepository.save(provider);
    }

    // ---------------------------------------------------------
    // AUTHENTICATE (Signin)
    // ---------------------------------------------------------
    public Provider authenticate(String email, String password) {
        Provider provider = providerRepository.findByEmail(email);

        if (provider == null) {
            throw new RuntimeException("Provider not found");
        }

        if (!provider.getPassword().equals(password)) {
            throw new RuntimeException("Invalid password");
        }

        return provider;
    }

    // ---------------------------------------------------------
    // GET PROVIDER BY ID (Home, Edit Profile)
    // ---------------------------------------------------------
    public Provider getProviderById(Long id) {
        return providerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Provider not found"));
    }

    // ---------------------------------------------------------
    // UPDATE PROVIDER (Edit Profile)
    // ---------------------------------------------------------
    public Provider updateProvider(Long id, Provider updatedData) {
        Provider provider = getProviderById(id);

        provider.setName(updatedData.getName());
        provider.setEmail(updatedData.getEmail());
        provider.setAddress(updatedData.getAddress());
        provider.setPhoneNumber(updatedData.getPhoneNumber());
        provider.setPassword(updatedData.getPassword());

        return providerRepository.save(provider);
    }

    public Provider authenticate(String email, String password){
        Provider provider  = providerRepository.findByEmail(email)
        .orElseThrow(() -> new EntityNotFoundException("Provider not found with email: " + email));
         if(!provider.getEmail().equals(email) && !provider.getPassword().equals(password)){
            throw new IllegalArgumentException("Invalid email and password");
        }
        if(!provider.getPassword().equals(password)){
            throw new IllegalArgumentException("Invalid password");
        }
        return provider;
    }

    public List<Provider> getAll(){
    // ---------------------------------------------------------
    // LIST ALL PROVIDERS (not used but helpful)
    // ---------------------------------------------------------
    public Iterable<Provider> getAllProviders() {
        return providerRepository.findAll();
    }

    // ---------------------------------------------------------
    // DELETE PROVIDER (optional)
    // ---------------------------------------------------------
    public void deleteProvider(Long id) {
        providerRepository.deleteById(id);
    }

    // ---------------------------------------------------------
    // FIND BY EMAIL (helper for login)
    // ---------------------------------------------------------
    public Provider findByEmail(String email) {
        return providerRepository.findByEmail(email);
    }
}
