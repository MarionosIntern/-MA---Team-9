package com.example.Centrix.Marketplace.Provider;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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

        provider.setName(providerDetails.getName());
        provider.setEmail(providerDetails.getEmail());
        provider.setPassword(providerDetails.getPassword());
        provider.setAddress(providerDetails.getAddress());
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
        return providerRepository.findAll();
    }

    public void delete(Long id){
        if (!providerRepository.existsById(id)) {
            throw new EntityNotFoundException("Provider not found with id: " + id);
        }
        providerRepository.deleteById(id);
    }
}