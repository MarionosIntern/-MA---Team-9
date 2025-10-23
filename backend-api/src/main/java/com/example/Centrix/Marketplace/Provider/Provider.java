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

    @NotBlank
    @Column(nullable = false)
    private String name;

    @Email
    @NotBlank
    @Column(unique = true, nullable = false)
    private String email;

    @NotBlank
    @Column(nullable = false)
    private String password;

    @NotBlank
    @Column(nullable = false)
    private String address;

    @OneToOne(mappedBy = "provider", cascade = CascadeType.ALL)
    @JsonIgnoreProperties("provider")

    private Provider provider; 
    private String phoneNumber;

    public Provider(Long id){
        this.id = id;
    }
}