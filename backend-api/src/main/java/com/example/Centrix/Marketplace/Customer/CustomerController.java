package com.example.Centrix.Marketplace.Customer;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {
    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping
    public ResponseEntity<Customer> createCustomer(@RequestBody Customer customer) {
        return ResponseEntity.ok(customerService.createCustomer(customer));
    }

    // Field-specific endpoints
    @GetMapping("/{id}/name")
    public ResponseEntity<String> getCustomerName(@PathVariable("id") Long id) {
        return ResponseEntity.ok(customerService.getCustomerById(id).name);
    }

    @PutMapping("/{id}/name")
    public ResponseEntity<Void> setCustomerName(@PathVariable("id") Long id, @RequestBody String name) {
        Customer c = customerService.getCustomerById(id);
        c.name = name;
        customerService.updateCustomer(id, c);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}/shippingAddress")
    public ResponseEntity<String> getShippingAddress(@PathVariable("id") Long id) {
        return ResponseEntity.ok(customerService.getCustomerById(id).shippingAddress);
    }

    @PutMapping("/{id}/shippingAddress")
    public ResponseEntity<Void> setShippingAddress(@PathVariable("id") Long id, @RequestBody String address) {
        Customer c = customerService.getCustomerById(id);
        c.shippingAddress = address;
        customerService.updateCustomer(id, c);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}/phoneNumber")
    public ResponseEntity<String> getPhoneNumber(@PathVariable("id") Long id) {
        return ResponseEntity.ok(customerService.getCustomerById(id).phoneNumber);
    }

    @PutMapping("/{id}/phoneNumber")
    public ResponseEntity<Void> setPhoneNumber(@PathVariable("id") Long id, @RequestBody String phone) {
        Customer c = customerService.getCustomerById(id);
        c.phoneNumber = phone;
        customerService.updateCustomer(id, c);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Customer> updateCustomer(@PathVariable("id") Long id, @RequestBody Customer customerDetails) {
        return ResponseEntity.ok(customerService.updateCustomer(id, customerDetails));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Customer> getCustomer(@PathVariable("id") Long id) {
        return ResponseEntity.ok(customerService.getCustomerById(id));
    }

    @GetMapping
    public ResponseEntity<List<Customer>> getAllCustomers() {
        return ResponseEntity.ok(customerService.getAllCustomers());
    }

    @GetMapping("/search/address")
    public ResponseEntity<List<Customer>> searchByAddress(@RequestParam String address) {
        return ResponseEntity.ok(customerService.searchByAddress(address));
    }

    @GetMapping("/search/phone")
    public ResponseEntity<List<Customer>> searchByPhoneNumber(@RequestParam String phoneNumber) {
        return ResponseEntity.ok(customerService.searchByPhoneNumber(phoneNumber));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCustomer(@PathVariable("id") Long id) {
        customerService.deleteCustomer(id);
        return ResponseEntity.noContent().build();
    }
}