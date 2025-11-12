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
}