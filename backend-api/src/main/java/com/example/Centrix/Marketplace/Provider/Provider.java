package com.example.Centrix.Marketplace.Provider;

import jakarta.persistence.Entity;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.example.CentrixMarketplaceApplication

@Data
@NoArgsConstructor
@Entity
@Table(name = "providers")
public class Provider{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @NotBlank
    @Column(nullable = false)
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Email
    @NotBlank
    @Column(unique = true, nullable = false)
    private String email;

      public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @NotBlank
    @Column(nullable = false)
    private String password;

      public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    @NotBlank
    @Column(nullable = false)
    private String address;

      public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @OneToOne(mappedBy = "provider", cascade = CascadeType.ALL)
    @JsonIgnoreProperties("provider")

    private Provider provider; 
    private String phoneNumber;
     
    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Provider(){}

    public Provider(Long id, String name, String email, String password, String address, String phoneNumber){
        this.id = id;
        this.name = name;
        this.email = email; 
        this.password = password;
        this.address = address;
    }
}