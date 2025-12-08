package com.example.Centrix.Marketplace;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.Centrix.Marketplace.Product.Product;
import com.example.Centrix.Marketplace.Product.ProductService;

@Controller
public class HomeController {

    private final ProductService productService;

    public HomeController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping({"/", "/home", "/customer/home"})
    public String showHome(Model model) {
        List<Product> products = productService.findAllProducts(null, null, null);
        model.addAttribute("products", products);
        return "customer/home";
    }
}
