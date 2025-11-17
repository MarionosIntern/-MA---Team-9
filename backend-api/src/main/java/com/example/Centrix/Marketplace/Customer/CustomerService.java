package com.example.Centrix.Marketplace.Customer;

import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.multipart.MultipartFile;

import jakarta.persistence.criteria.CriteriaBuilder.In;

import java.util.List;
import java.util.Optional;
@Service
public class CustomerService {

    private static final String UPLOAD_DIR = "uploads/profile_pics"; // optional

    @Autowired
    private CustomerRepository customerRepository;

    // ================================
    // 1️⃣ Get all customers
    // ================================
    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    // ================================
    // 2️⃣ Get customer by ID
    // ================================
    public Optional<Customer> getCustomerById(Long id) {
        return customerRepository.findById(id);
    }

    // ================================
    // 3️⃣ Get customer by name
    // ================================
   public List<Customer> getCustomerByName(String name) {
    if (name == null || name.isBlank()) {
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Name is required");
    }
    return customerRepository.findByNameContainingIgnoreCase(name);
}


    // ================================
    // 4️⃣ Get customer by email
    // ================================
    public Optional<Customer> getCustomerByEmail(String email) {
        return customerRepository.findByEmail(email);
    }

    // ================================
    // 5️⃣ Get customer by phone (if applicable)
    // ================================
    public List<Customer> getCustomerByPhoneNumber(String phoneNumber) {
        return customerRepository.findByPhoneNumberContaining(phoneNumber);
    }

    // ================================
    // 6️⃣ Create new customer
    // ================================
    public Customer createCustomer(Customer customer) {
        return customerRepository.save(customer);
    }

    // ================================
    // 7️⃣ Update existing customer
    // ================================
    public Customer updateCustomer(Long id, Customer customer) {
        Customer existing = customerRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Customer not found"));

        // Update the basic fields
        existing.setName(customer.getName());
        existing.setEmail(customer.getEmail());
        existing.setPhoneNumber(customer.getPhoneNumber());
        existing.setShippingAddress(customer.getShippingAddress());


        return customerRepository.save(existing);
    }

    // ================================
    // 8️⃣ Delete customer
    // ================================
    public void deleteCustomer(Long id) {
        if (!customerRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Customer not found");
        }
        customerRepository.deleteById(id);
    }

    public Customer getCurrentCustomer() {
        Long currentCustomerId = 1L; // Placeholder until authentication is wired in
        return getCurrentCustomer(currentCustomerId);
    }

    public Customer getCurrentCustomer(Long id) {
        return customerRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Customer not found"));
    }

}


















