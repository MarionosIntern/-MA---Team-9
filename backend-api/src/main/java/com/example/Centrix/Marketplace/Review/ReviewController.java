package com.example.Centrix.Marketplace.Review;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.Centrix.Marketplace.Customer.Customer;
import com.example.Centrix.Marketplace.Customer.CustomerService;
import com.example.Centrix.Marketplace.Product.Product;
import com.example.Centrix.Marketplace.Customer.Customer;
import com.example.Centrix.Marketplace.Customer.CustomerService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;

import java.util.List;

@Controller
@RequestMapping("/reviews")
public class ReviewController {

    private final ReviewService reviewService;
    private final CustomerService customerService;

    public ReviewController(ReviewService reviewService, CustomerService customerService) {
        this.reviewService = reviewService;
        this.customerService = customerService;
    }

    // =====================================
    // ✅ 1️⃣ List all reviews (admin-style view)
    // =====================================
    @GetMapping("/all")
    public String getAllReviews(Model model) {
        model.addAttribute("reviewList", reviewService.getAllReviews());
        model.addAttribute("title", "All Reviews");
        return "review/list"; // templates/review/list.fthl
    }

    // =====================================
    // ✅ 2️⃣ List reviews for current customer
    // =====================================
    @GetMapping
    public String listReviews(Model model) {
        Customer currentCustomer = customerService.getCurrentCustomer();
        model.addAttribute("reviews", reviewService.getAllForCustomer(currentCustomer.getId()));
        model.addAttribute("customer", currentCustomer);
        model.addAttribute("title", "Your Reviews");
        return "review/index"; // templates/review/index.fthl
    }

    @PostMapping("/{id}/provider-response")
    public ResponseEntity<Review> addProviderResponse(@PathVariable Long id, @RequestBody String response) {
        return ResponseEntity.ok(reviewService.addProviderResponse(id, response));
    // =====================================
    // ✅ 3️⃣ View a single review
    // =====================================
    @GetMapping("/{id}")
    public String getReviewById(@PathVariable("id") Long id, Model model) {
        Review review = reviewService.getReviewById(id);
        if (review == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Review not found");
        }
        model.addAttribute("review", review);
        model.addAttribute("title", "Review Details");
        return "review/details"; // templates/review/details.fthl
    }

    // =====================================
    // ✅ 4️⃣ Search reviews by customer name
    // =====================================
    @GetMapping("/search")
    public String searchReviewsByCustomerName(@RequestParam("name") String name, Model model) {
        if (name == null || name.isBlank()) {
            return "redirect:/api/reviews";
        }

        List<Review> reviews = reviewService.searchReviewsByCustomerName(name);
        model.addAttribute("reviews", reviews);
        model.addAttribute("title", "Search Results for '" + name + "'");
        return "review/search-results"; // templates/review/search-results.fthl
    }

    // =====================================
    // ✅ 5️⃣ Create review (form)
    // =====================================
    @GetMapping("/create")
    public String showCreateReviewForm(Model model) {
        model.addAttribute("review", new Review());
        model.addAttribute("title", "Create New Review");
        return "review/create"; // templates/review/create.fthl
    }

    // =====================================
    // ✅ 6️⃣ Submit review (form POST)
    // =====================================
    @PostMapping("/create")
    public String createReview(@ModelAttribute Review review) {
        reviewService.createReview(review);
        return "redirect:/api/reviews";
    }

    // =====================================
    // ✅ 7️⃣ Delete review
    // =====================================
    @GetMapping("/delete/{id}")
    public String deleteReview(@PathVariable("id") Long id) {
        reviewService.deleteReview(id);
        return "redirect:/api/reviews";
    }

    // =====================================
    // ✅ 8️⃣ View reviews for a specific customer (admin view)
    // =====================================
    @GetMapping("/customer/{customerId}")
    public String getCustomerReviews(@PathVariable("customerId") Long customerId, Model model) {
        Customer customer = customerService.getCustomerById(customerId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Customer not found"));
        model.addAttribute("reviews", reviewService.getAllForCustomer(customerId));
        model.addAttribute("customer", customer);
        model.addAttribute("title", "Reviews for " + customer.getName());
        return "review/list";
    }
}
