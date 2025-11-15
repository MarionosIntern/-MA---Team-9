package com.example.Centrix.Marketplace.Subscription;

import com.example.Centrix.Marketplace.Customer.Customer;
import com.example.Centrix.Marketplace.Customer.CustomerRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@Transactional
public class SubscriptionService {

    private final SubscriptionRepository subscriptionRepository;
    private final CustomerRepository customerRepository;

    public SubscriptionService(SubscriptionRepository subscriptionRepository, CustomerRepository customerRepository) {
        this.subscriptionRepository = subscriptionRepository;
        this.customerRepository = customerRepository;
    }

    // ================================
    // 1️⃣ Get all subscriptions
    // ================================
    public List<Subscription> getAllSubscriptions() {
        return subscriptionRepository.findAll();
    }

    // ================================
    // 2️⃣ Get subscription by ID
    // ================================
    public Subscription getSubscriptionById(Long id) {
        return subscriptionRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Subscription not found"));
    }

    // ================================
    // 3️⃣ Get active subscriptions for a single customer
    // ================================
    public List<Subscription> getActiveSubscriptionsByCustomer(Customer customer) {
        if (customer == null || customer.getId() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Customer information is required");
        }
        return subscriptionRepository.findByCustomerAndActive(customer, true);
    }

    // ================================
    // 4️⃣ Create a new subscription
    // ================================
    public Subscription createSubscription(Subscription subscription) {
        if (subscription == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Subscription payload is required");
        }

        Customer customer = subscription.getCustomer();
        if (customer == null || customer.getId() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Subscription must include a customer with ID");
        }

        Customer managedCustomer = customerRepository.findById(customer.getId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Customer not found"));

        subscription.setCustomer(managedCustomer);
        subscription.setActive(true);

        return subscriptionRepository.save(subscription);
    }

    // ================================
    // 5️⃣ Cancel (soft delete) subscription
    // ================================
    public void cancelSubscription(Long id) {
        Subscription subscription = subscriptionRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Subscription not found"));

        subscription.setActive(false);
        subscriptionRepository.save(subscription);
    }

    // ================================
    // 6️⃣ Get active subscriptions by customer ID
    // ================================
    public List<Subscription> getSubscriptionsByCustomerId(Long id) {
        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Customer not found"));

        return subscriptionRepository.findByCustomerAndActive(customer, true);
    }
}




