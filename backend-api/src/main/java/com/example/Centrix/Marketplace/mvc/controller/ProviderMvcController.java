package com.example.Centrix.Marketplace.mvc.controller;

import com.example.Centrix.Marketplace.Provider.Provider;
import com.example.Centrix.Marketplace.Provider.ProviderService;
import com.example.Centrix.Marketplace.Product.Product;
import com.example.Centrix.Marketplace.Product.ProductService;
import com.example.Centrix.Marketplace.Review.Review;
import com.example.Centrix.Marketplace.Review.ReviewService;

import java.util.List;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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
        return "redirect:/signin";
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
            return "redirect: signin";
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

    @GetMapping("/products/sell")
    public String uploadProductForm(HttpSession session, Model model){
        Long providerId = (Long) session.getAttribute("providerId");
        if(providerId == null){
            return "redirect:/sigin";
        }
        Provider provider = providerService.getProviderById(providerId);
        if(provider.getProducts() != null){
            return "redirect:/providers/home";
        }
        return "provider/sell-product";
    }

    @PostMapping("/products/sell")
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

        return "redirect: /provider/home";
    }


}