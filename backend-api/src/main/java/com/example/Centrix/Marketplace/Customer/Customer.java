package com.example.Centrix.Marketplace.Customer;

import com.example.Centrix.Marketplace.Subscription.Subscription;
import jakarta.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import static com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility.ANY;

import java.util.ArrayList;
import java.util.List;

@Entity
@JsonAutoDetect(fieldVisibility = ANY)
@Table(name = "customers")
public class Customer {
    // explicit no-arg constructor (replaces Lombok @NoArgsConstructor)
    public Customer() {
    }
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(nullable = false)
    String name;
    
    @Column(unique = true, nullable = false)
    String email;

    @Column(nullable = false)
    String password;

    @OneToMany(mappedBy = "customer")
    @JsonIgnoreProperties("customer")
    List<Subscription> subscriptions = new ArrayList<>();

    String shippingAddress;

    String phoneNumber;

    public Customer(Long id) {
        this.id = id;
    }

    // getters/setters intentionally removed from entity; access fields directly from service/controller within the package

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", shippingAddress='" + shippingAddress + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                '}';
    }

    // Minimal getter for id so other packages can reference the customer id when needed
    public Long getId() {
        return this.id;
    }

}