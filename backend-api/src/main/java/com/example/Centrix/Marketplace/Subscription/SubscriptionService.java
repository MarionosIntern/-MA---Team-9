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

    public Subscription createSubscription(Subscription subscription) {
        // Validate presence of customer reference and id to avoid DB not-null constraint violation
        if (subscription == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Subscription payload is required");
        }
        if (subscription.customer == null || subscription.customer.getId() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Subscription must include a customer with id");
        }

        Long customerId = subscription.customer.getId();
        if (!customerRepository.existsById(customerId)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Customer not found");
        }

        // Attach managed Customer entity to avoid inserting a null or transient reference
        subscription.customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Customer not found"));

        return subscriptionRepository.save(subscription);
    }

    public Subscription updateSubscription(Long id, Subscription subscriptionDetails) {
        Subscription subscription = subscriptionRepository.findById(id)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Subscription not found"));

        // use package-private fields on Subscription (no Lombok getters/setters)
        subscription.type = subscriptionDetails.type;
        subscription.active = subscriptionDetails.active;
        subscription.startDate = subscriptionDetails.startDate;
        subscription.endDate = subscriptionDetails.endDate;

        return subscriptionRepository.save(subscription);
    }

    public void cancelSubscription(Long id) {
        Subscription subscription = subscriptionRepository.findById(id)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Subscription not found"));
        subscription.active = false;
        subscriptionRepository.save(subscription);
    }

    public List<Subscription> getActiveSubscriptionsByCustomer(Customer customer) {
        return subscriptionRepository.findByCustomerAndActive(customer, true);
    }
}