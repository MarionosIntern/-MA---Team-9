package com.example.Centrix.Marketplace.Customer;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

    // Search customers by part of their shipping address
    List<Customer> findByShippingAddressContaining(String address);

    // Search customers by partial phone number match
    List<Customer> findByPhoneNumberContaining(String phoneNumber);

    // Check if a customer with a given email already exists
    boolean existsByEmail(String email);

    // Find a customer by their exact email address
    Optional<Customer> findByEmail(String email);
}