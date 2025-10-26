package com.example.Centrix.Marketplace.Subscription;

import com.example.Centrix.Marketplace.Customer.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SubscriptionRepository extends JpaRepository<Subscription, Long> {
    List<Subscription> findByCustomerAndActive(Customer customer, boolean active);
}