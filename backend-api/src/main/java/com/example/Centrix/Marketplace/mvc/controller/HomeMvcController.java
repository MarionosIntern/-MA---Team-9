package com.example.Centrix.Marketplace.mvc.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.Centrix.Marketplace.Product.Product;
import com.example.Centrix.Marketplace.Product.ProductService;
import com.example.Centrix.Marketplace.Provider.Provider;
import com.example.Centrix.Marketplace.Provider.ProviderService;

@Controller
@RequestMapping("/mvc")
public class HomeMvcController {
    private final ProviderService providerService;
    private final ProductService productService;

    public HomeMvcController(ProviderService providerService, ProductService productService) {
        this.providerService = providerService;
        this.productService = productService;
    }

    @GetMapping("/")
    public String home(Model model){
       List<Provider> providers =  providerService.getAllProviders().stream().limit(5).toList();
       List<Product> products = productService.findAllProducts(null, null, null).stream().limit(12).toList();
       model.addAttribute("providers", providers);
       model.addAttribute("products", products);    
       return "home";
    }
}
