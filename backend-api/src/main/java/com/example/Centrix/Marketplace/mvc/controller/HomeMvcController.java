package com.example.Centrix.Marketplace.mvc.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.Centrix.Marketplace.Product.Product;
import com.example.Centrix.Marketplace.Product.ProductService;
import com.example.Centrix.Marketplace.Provider.Provider;
import com.example.Centrix.Marketplace.Provider.ProviderService;

@Controller
public class HomeMvcController {
    private final ProviderService providerService;
    private final ProductService productService;

    public HomeMvcController(ProviderService providerService, ProductService productService) {
        this.providerService = providerService;
        this.productService = productService;
    }

    @GetMapping("/")
    public String home(Model model){
       List<Provider> providers =  providerService.getAll().stream().limit(5).toList();
       List<Product> products = productService.getAllProducts().stream().limit(12).toList();
       model.addAttribute("providers", providers);
       model.addAttribute("products", products);    
       return "home";
    }

    @GetMapping("/signup")
    public String signup(){
        return "signup";
    }

    @GetMapping("/signin")
    public String signin(){
        return "signin";
    }
}