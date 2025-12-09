package com.example.Centrix.Marketplace.mvc.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.example.Centrix.Marketplace.Product.Product;
import com.example.Centrix.Marketplace.Product.ProductService;
import com.example.Centrix.Marketplace.Provider.Provider;
import com.example.Centrix.Marketplace.Provider.ProviderService;
import com.example.Centrix.Marketplace.Review.Review;
import com.example.Centrix.Marketplace.Review.ReviewService;
import com.example.Centrix.Marketplace.SessionConstants;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/mvc/providers")
public class ProviderMvcController {

    private final ProviderService providerService;
    private final ProductService productService;
    private final ReviewService reviewService;

    public ProviderMvcController(ProviderService providerService, ProductService productService, ReviewService reviewService) {
        this.providerService = providerService;
        this.productService = productService;
        this.reviewService = reviewService;
    }

    // -------------------------------
    // AUTH
    // -------------------------------

    @GetMapping("/signin")
    public String signinForm() {
        return "provider/signin";
    }

    @GetMapping("/signup")
    public String signupForm(Model model) {
        model.addAttribute("provider", new Provider());
        return "provider/signup";
    }

    @PostMapping("/signup")
    public String signUp(@RequestParam("name") String name,
                         @RequestParam("email") String email,
                         @RequestParam("password") String password,
                         @RequestParam("address") String address,
                         @RequestParam(value = "phoneNumber", required = false) String phoneNumber) {

        Provider provider = new Provider();
        provider.setName(name);
        provider.setEmail(email);
        provider.setPassword(password);
        provider.setAddress(address);
        provider.setPhoneNumber(phoneNumber);

        providerService.createProvider(provider);
        return "redirect:/providers/signin";
    }

    @PostMapping("/signin")
    public String signin(@RequestParam String email,
                         @RequestParam String password,
                         HttpSession session) {
        try {
            Provider provider = providerService.authenticate(email, password);
            session.setAttribute(SessionConstants.PROVIDER_ID, provider.getId());
            return "redirect:/providers/home";
        } catch (Exception e) {
            return "redirect:/providers/signin?error";
        }
    }

    // -------------------------------
    // HOME
    // -------------------------------

    @GetMapping("/home")
    public String home(HttpSession session, Model model) {

        Long providerId = (Long) session.getAttribute(SessionConstants.PROVIDER_ID);
        if (providerId == null) return "redirect:/providers/signin";

        Provider provider = providerService.getProviderById(providerId);
        model.addAttribute("provider", provider);

        List<Product> products = productService.findAllProducts(providerId, null, null);
        model.addAttribute("products", products);

        return "provider/home";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.removeAttribute(SessionConstants.PROVIDER_ID);
        return "redirect:/providers/signin";
    }

    // -------------------------------
    // PRODUCT UPLOAD
    // -------------------------------

    @GetMapping("/products/upload")
    public String uploadProductForm(HttpSession session, Model model) {

        Long providerId = (Long) session.getAttribute(SessionConstants.PROVIDER_ID);
        if (providerId == null) return "redirect:/providers/signin";

        return "provider/upload-product";
    }

    @PostMapping("/products/upload")
    public String uploadProduct(@RequestParam String name,
                                @RequestParam String category,
                                @RequestParam double price,
                                @RequestParam(required = false) String imageUrl,
                                @RequestParam String description,
                                @RequestParam String status,
                                HttpSession session) {

        Long providerId = (Long) session.getAttribute(SessionConstants.PROVIDER_ID);
        if (providerId == null) return "redirect:/providers/signin";

        Provider provider = providerService.getProviderById(providerId);

        Product product = new Product();
        product.setProvider(provider); // FK set correctly
        product.setName(name);
        product.setCategory(category);
        product.setPrice(price);
        product.setImageUrl(imageUrl);
        product.setDescription(description);
        product.setStatus(status);

        productService.create(product);

        return "redirect:/providers/home";
    }

    // -------------------------------
    // PROFILE EDIT
    // -------------------------------

    @GetMapping("profile/edit")
    public String editProfileForm(HttpSession session, Model model) {

        Long providerId = (Long) session.getAttribute(SessionConstants.PROVIDER_ID);
        if (providerId == null) return "redirect:/providers/signin";

        Provider provider = providerService.getProviderById(providerId);
        model.addAttribute("provider", provider);

        return "provider/edit-profile";
    }

    @PostMapping("profile/edit")
    public String editProfile(@RequestParam String name,
                              @RequestParam String email,
                              @RequestParam(required = false) String phoneNumber,
                              @RequestParam String address,
                              @RequestParam String currentPassword,
                              @RequestParam(required = false) String newPassword,
                              HttpSession session, Model model) {

        Long providerId = (Long) session.getAttribute(SessionConstants.PROVIDER_ID);
        if (providerId == null) return "redirect:/providers/signin";

        try {
            Provider provider = providerService.getProviderById(providerId);
            providerService.authenticate(email, currentPassword);

            Provider updatedProvider = new Provider();
            updatedProvider.setName(name);
            updatedProvider.setEmail(email);
            updatedProvider.setAddress(address);
            updatedProvider.setPhoneNumber(phoneNumber);
            updatedProvider.setPassword(
                    (newPassword != null && !newPassword.trim().isEmpty())
                            ? newPassword
                            : currentPassword
            );

            providerService.updateProvider(providerId, updatedProvider);
            return "redirect:/providers/home";

        } catch (Exception e) {
            model.addAttribute("provider", providerService.getProviderById(providerId));
            model.addAttribute("error", "Password is invalid");
            return "provider/edit-profile";
        }
    }

    // -------------------------------
    // PRODUCT EDIT
    // -------------------------------

    @GetMapping("products/{id}/edit")
    public String editProductForm(@PathVariable Long id, HttpSession session, Model model) {

        Long providerId = (Long) session.getAttribute(SessionConstants.PROVIDER_ID);
        if (providerId == null) return "redirect:/providers/signin";

        Provider provider = providerService.getProviderById(providerId);
        Product product = productService.findById(id);

        // Ensure provider owns the product
        if (!product.getProvider().getId().equals(provider.getId())) {
            return "redirect:/providers/home";
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
                              HttpSession session) {

        Long providerId = (Long) session.getAttribute(SessionConstants.PROVIDER_ID);
        if (providerId == null) return "redirect:/providers/signin";

        Provider provider = providerService.getProviderById(providerId);
        Product existingProduct = productService.findById(id);

        // Ensure provider owns the product
        if (!existingProduct.getProvider().getId().equals(provider.getId())) {
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

    // -------------------------------
    // REVIEWS
    // -------------------------------

    @GetMapping("/reviews")
    public String viewReviews(HttpSession session, Model model) {

        Long providerId = (Long) session.getAttribute(SessionConstants.PROVIDER_ID);
        if (providerId == null) return "redirect:/providers/signin";

        Provider provider = providerService.getProviderById(providerId);
        List<Review> reviews = reviewService.getReviewsByProviderId(providerId);

        model.addAttribute("reviews", reviews);
        return "provider/reviews";
    }

    @PostMapping("/reviews/{id}/reply")
    public String respondToReview(@PathVariable Long id,
                                  @RequestParam String response,
                                  HttpSession session) {

        Long providerId = (Long) session.getAttribute(SessionConstants.PROVIDER_ID);
        if (providerId == null) return "redirect:/providers/signin";

        Provider provider = providerService.getProviderById(providerId);
        Review review = reviewService.addProviderResponse(id, response);

        // Ensure provider owns the product being reviewed
        if (review.getProduct().getProvider().getId().equals(provider.getId())) {
            return "redirect:/providers/reviews";
        }

        return "redirect:/providers/reviews";
    }
}
