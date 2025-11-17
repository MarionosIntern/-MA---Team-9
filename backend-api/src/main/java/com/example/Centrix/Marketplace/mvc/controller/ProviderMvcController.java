package com.example.Centrix.Marketplace.mvc.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.Centrix.Marketplace.Product.Product;
import com.example.Centrix.Marketplace.Product.ProductService;
import com.example.Centrix.Marketplace.Provider.Provider;
import com.example.Centrix.Marketplace.Provider.ProviderService;
import com.example.Centrix.Marketplace.Review.Review;
import com.example.Centrix.Marketplace.Review.ReviewService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/providers")
public class ProviderMvcController{
    private final ProviderService providerService;
    private final ProductService productService;
    private final ReviewService reviewService;

    public ProviderMvcController(ProviderService providerService, ProductService productService, ReviewService reviewService) {
        this.providerService = providerService;
        this.productService = productService;
        this.reviewService = reviewService;
    }

    @GetMapping("/signup")
    public String signupForm(Model model) {
        model.addAttribute("provider", new Provider());
        return "provider/signup";
    }

    @PostMapping("/signup")
    public String signUp(@ModelAttribute Provider provider){
        providerService.createProvider(provider);
        return "redirect:/providers/signin";
    }

    @PostMapping("/signin")
    public String signin(@RequestParam String email, @RequestParam String password, HttpSession session){
        try {
            Provider provider = providerService.authenticate(email, password);
            session.setAttribute("providerId", provider.getId());
            return "redirect:/providers/home";
        } catch (Exception e) {
            return "redirect:/signin?error";
        }
    }

    @GetMapping("/home")
    public String home(HttpSession session, Model model){
        Long providerId = (Long) session.getAttribute("providerID");
        if(providerId == null){
            return "redirect: providers/signin";
        }
        Provider provider = providerService.getProviderById(providerId);
        model.addAttribute("provider", provider);
        return "provider/home";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session){
        session.removeAttribute("providerId");
        return "redirect:/signin";
    }

    @GetMapping("/products/upload")
    public String uploadProductForm(HttpSession session, Model model){
        Long providerId = (Long) session.getAttribute("providerId");
        if(providerId == null){
            return "redirect:/sigin";
        }
        Provider provider = providerService.getProviderById(providerId);
        if(provider.getProducts() != null){
            return "redirect:/providers/home";
        }
        return "provider/upload-product";
    }

    @PostMapping("/products/upload")
    public String uploadProduct(@RequestParam String name,
                                @RequestParam String category,
                                @RequestParam double price,
                                @RequestParam String description,
                                @RequestParam String status,
                                HttpSession session){
        Long providerId = (Long) session.getAttribute("providerId");
        if(providerId == null){
            return "redirect:/signin";
        }
        Product product = new Product();
        product.setName(name);
        product.setCategory(category);
        product.setPrice(price);
        product.setDescription(description);
        product.setStatus(status);

        productService.create(product);

        return "redirect:/provider/home";
    }

    @GetMapping("profile/edit")
    public String editProfileForm(HttpSession session, Model model){
        Long providerId = (Long) session.getAttribute("providerId");
        if(providerId == null){
            return "redirect:/siginin";
        }
        Provider provider = providerService.getProviderById(providerId);
        model.addAttribute("provider", provider);
        return "provider/edit-profile";
    }

    @PostMapping("profile/edit")
    public String editProfile(@RequestParam String name,
                              @RequestParam String email,
                              @RequestParam (required = false) String phoneNumber,
                              @RequestParam String address,
                              @RequestParam String currentPassword,
                              @RequestParam (required = false) String newPassword, HttpSession session,
                              Model model){
        Long providerId = (Long) session.getAttribute("providerId");
        if(providerId == null){
            return "redirect:/signin";
        }

        try {
            Provider provider = providerService.getProviderById(providerId);
            providerService.authenticate(email, currentPassword);

            Provider updatedProvider = new Provider();
            updatedProvider.setName(name);
            updatedProvider.setEmail(email);
            updatedProvider.setAddress(address);
            updatedProvider.setPhoneNumber(phoneNumber);
            updatedProvider.setPassword(newPassword != null && !newPassword.trim().isEmpty() ? newPassword : currentPassword); 
            
            providerService.updateProvider(providerId, updatedProvider);
            return "redirect:/provider/home";
        } catch (Exception e) {
            model.addAttribute("provider", providerService.getProviderById(providerId));
            model.addAttribute("error", "Password is invalid");
            return "redirect:/provider/edit-profile";
        }
     }

     @GetMapping("products/{id}/edit")
     public String editProductForm(@PathVariable Long id, HttpSession session, Model model){
        Long providerId = (Long) session.getAttribute("providerId");
        if(providerId == null){
            return "redirect:/signin";
        }
        Provider provider = providerService.getProviderById(providerId);
        Product product = productService.findById(providerId);

        if(!product.getProviderId().equals(provider.getId())){
            return "redirect:/provider/home";
        }

        model.addAttribute("product", product);
        return "provider/edit-product";
     }

     @PostMapping("products/{id}/edit")
     public String editProduct(@PathVariable Long id,
                               @RequestParam String name,
                               @RequestParam String category,
                               @RequestParam double price,
                               @RequestParam String description,
                               @RequestParam String status,
                               HttpSession session){
        Long providerId = (Long) session.getAttribute("providerId");
        if(providerId == null){
            return "redirect:/siginin";
        }
        Provider provider = providerService.getProviderById(providerId);
        Product existingProduct = productService.findById(id);

        if(!existingProduct.getProviderId().equals(provider.getId())){
            return "redirect:/providers/home";
        }

        Product updatedProduct = new Product();
        updatedProduct.setName(name);
        updatedProduct.setCategory(category);
        updatedProduct.setPrice(price);
        updatedProduct.setDescription(description);
        updatedProduct.setStatus(status);

        productService.update(id, updatedProduct);
        return "redirect:/providers/home";
     }

     @GetMapping("/reviews")
     public String viewReviews(HttpSession session, Model model){
        Long providerId = (Long) session.getAttribute("providerId");
        if(providerId == null){
            return "redirect:/siginin";
        }
        Provider provider = providerService.getProviderById(providerId);
        if(provider.getProducts() == null){
            return "redirect:/providers/home";
        }
        List<Review> reviews = reviewService.getReviewsByProviderId(providerId);
        model.addAttribute("reviews", reviews);
        return "provider/reviews";
     } 

     @PostMapping("/reviews/{id}/reply")
     public String respondToReview(@PathVariable Long id,
                                   @RequestParam String repsonse,
                                   HttpSession session){
        Long providerId = (Long) session.getAttribute("providerId");
        if(providerId == null){
            return "redirect:/signin";
        }
        Provider provider = providerService.getProviderById(providerId);
        Review review = reviewService.addProviderResponse(id, repsonse);

        if(review.getProduct().getProviderId().equals(provider.getId())){
            return "redirect:/providers/reviews";
        }
        reviewService.addProviderResponse(id, repsonse);
        return "redirect:/providers/reviews";
    }




     






}