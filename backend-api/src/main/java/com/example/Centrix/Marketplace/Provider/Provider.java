package com.example.Centrix.Marketplace.Provider;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "providers", uniqueConstraints = {
        @UniqueConstraint(name = "uk_provider_email", columnNames = {"email"})
})
public class Provider {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "name is required")
    @Size(max = 120)
    private String name;

    @NotBlank(message = "email is required")
    @Email
    @Size(max = 160)
    private String email;

    @NotBlank(message = "password is required")
    @Size(min = 6, max = 255)
    private String password;

    @NotBlank(message = "address is required")
    @Size(max = 255)
    private String address;

    @NotBlank(message = "phoneNumber is required")
    @Size(max = 40)
    private String phoneNumber;

    public Provider() {}

    public Provider(Long id, String name, String email, String password, String address, String phoneNumber) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.address = address;
        this.phoneNumber = phoneNumber;
    }

    @Override
    public String toString() {
        return "Provider{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                '}';
    }


    // Getters & Setters

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }

    public String getPhoneNumber() { return phoneNumber; }
    public void setPhoneNumber(String phoneNumber) { this.phoneNumber = phoneNumber; }
}