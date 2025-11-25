package com.example.Centrix.Marketplace.Customer;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.springframework.http.HttpStatus;

import org.springframework.web.server.ResponseStatusException;




import java.util.List;
import java.util.Optional;
@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    
    //  Get all customers
   
    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    
    //  Get customer by ID
    
    public Optional<Customer> getCustomerById(Long id) {
        return customerRepository.findById(id);
    }

    
    //  Get customer by name
    
   public List<Customer> getCustomerByName(String name) {
    if (name == null || name.isBlank()) {
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Name is required");
    }
    return customerRepository.findByNameContainingIgnoreCase(name);
}


    
    //  Get customer by email
   
    public Optional<Customer> getCustomerByEmail(String email) {
        return customerRepository.findByEmail(email);
    }

   
    //  Get customer by phone (if applicable)
    
    public List<Customer> getCustomerByPhoneNumber(String phoneNumber) {
        return customerRepository.findByPhoneNumberContaining(phoneNumber);
    }

   
    //  Create new customer
    
    public Customer createCustomer(Customer customer) {
        return customerRepository.save(customer);
    }

   
    //  Update existing customer
   
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

    
    //  Delete customer
    
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


















