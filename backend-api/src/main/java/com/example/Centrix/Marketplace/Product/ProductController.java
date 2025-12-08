package com.example.Centrix.Marketplace.Product;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.example.Centrix.Marketplace.SessionConstants;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    // VIEW: product list
    @GetMapping("")
    public String listProducts(Model model,
                               @RequestParam(name = "providerId", required = false) Long providerId,
                               @RequestParam(name = "category", required = false) String category,
                               @RequestParam(name = "q", required = false) String searchTerm) {

        List<Product> products = productService.findAllProducts(providerId, category, searchTerm);
        model.addAttribute("products", products);
        model.addAttribute("title", "Product List");
        model.addAttribute("selectedCategory", category);
        model.addAttribute("searchTerm", searchTerm);
        return "product/list";
    }

    // VIEW: product details
    @GetMapping("/{id}")
    public String productDetails(@PathVariable Long id, Model model) {
        Product product = productService.findById(id);
        model.addAttribute("product", product);
        return "product/details";
    }

    // VIEW: show create form
    @GetMapping("/create")
    public String showCreateForm(Model model, HttpSession session) {
        if (!isProvider(session)) {
            return "redirect:/providers/signin";
        }
        model.addAttribute("product", new Product());
        return "product/create";
    }

    // POST: create product
    @PostMapping("/create")
    public String createProduct(@ModelAttribute Product p, HttpSession session) {
        if (!isProvider(session)) {
            return "redirect:/providers/signin";
        }
        productService.create(p);
        return "redirect:/products";
    }

    // VIEW: edit form
    @GetMapping("/{id}/edit")
    public String editForm(@PathVariable Long id, Model model, HttpSession session) {
        if (!isProvider(session)) {
            return "redirect:/providers/signin";
        }
        Product product = productService.findById(id);
        model.addAttribute("product", product);
        return "product/edit";
    }

    // POST: update product
    @PostMapping("/{id}/edit")
    public String editProduct(@PathVariable Long id, @ModelAttribute Product p, HttpSession session) {
        if (!isProvider(session)) {
            return "redirect:/providers/signin";
        }
        productService.update(id, p);
        return "redirect:/products/" + id;
    }

    // DELETE
    @GetMapping("/{id}/delete")
    public String deleteProduct(@PathVariable Long id, HttpSession session) {
        if (!isProvider(session)) {
            return "redirect:/providers/signin";
        }
        productService.delete(id);
        return "redirect:/products";
    }

    private boolean isProvider(HttpSession session) {
        return session.getAttribute(SessionConstants.PROVIDER_ID) != null;
    }
}

