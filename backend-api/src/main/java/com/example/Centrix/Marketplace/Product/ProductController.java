package com.example.Centrix.Marketplace.Product;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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
                               @RequestParam(name = "category", required = false) String category) {

        List<Product> products = productService.findAllProducts(providerId, category);
        model.addAttribute("products", products);
        model.addAttribute("title", "Product List");
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
    public String showCreateForm(Model model) {
        model.addAttribute("product", new Product());
        return "product/create";
    }

    // POST: create product
    @PostMapping("/create")
    public String createProduct(@ModelAttribute Product p) {
        productService.create(p);
        return "redirect:/products";
    }

    // VIEW: edit form
    @GetMapping("/{id}/edit")
    public String editForm(@PathVariable Long id, Model model) {
        Product product = productService.findById(id);
        model.addAttribute("product", product);
        return "product/edit";
    }

    // POST: update product
    @PostMapping("/{id}/edit")
    public String editProduct(@PathVariable Long id, @ModelAttribute Product p) {
        productService.update(id, p);
        return "redirect:/products/" + id;
    }

    // DELETE
    @GetMapping("/{id}/delete")
    public String deleteProduct(@PathVariable Long id) {
        productService.delete(id);
        return "redirect:/products";
    }
}

