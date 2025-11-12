package com.example.Centrix.Marketplace.mvc.controller;

import com.example.Centrix.Marketplace.Product.Product;
import com.example.Centrix.Marketplace.Product.ProductService;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

import com.example.Centrix.Marketplace.Product.Product;

@Controller
public class HomeMvcController {
    private final ProductService productService;

    public HomeMvcController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/")
    public String home(Model model){
        List<Product> products = productService.findAllProducts().stream().limit(10).toList();

    }
}