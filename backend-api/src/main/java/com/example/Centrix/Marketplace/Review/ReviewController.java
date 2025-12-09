package com.example.Centrix.Marketplace.Review;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.server.ResponseStatusException;

import com.example.Centrix.Marketplace.Customer.Customer;
import com.example.Centrix.Marketplace.Customer.CustomerService;

@Controller
@RequestMapping("/reviews")
public class ReviewController {

    private final ReviewService reviewService;
    private final CustomerService customerService;

    public ReviewController(ReviewService reviewService, CustomerService customerService) {
        this.reviewService = reviewService;
        this.customerService = customerService;
    }

    @GetMapping("/all")
    public String getAllReviews(Model model) {
        model.addAttribute("reviewList", reviewService.getAllReviews());
        model.addAttribute("title", "All Reviews");
        return "review/list";
    }

    @GetMapping
    public String listReviews(Model model) {
        Customer currentCustomer = customerService.getCurrentCustomer();
        model.addAttribute("reviews", reviewService.getAllForCustomer(currentCustomer.getId()));
        model.addAttribute("customer", currentCustomer);
        model.addAttribute("title", "Your Reviews");
        return "review/index";
    }

    @GetMapping("/{id}")
    public String getReviewById(@PathVariable("id") Long id, Model model) {
        Review review = reviewService.getReviewById(id);
        if (review == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Review not found");
        }
        model.addAttribute("review", review);
        model.addAttribute("title", "Review Details");
        return "review/details";
    }

    @GetMapping("/search")
    public String searchReviewsByCustomerName(@RequestParam("name") String name, Model model) {
        if (name == null || name.isBlank()) {
            return "redirect:/reviews";
        }

        List<Review> reviews = reviewService.searchReviewsByCustomerName(name);
        model.addAttribute("reviews", reviews);
        model.addAttribute("title", "Search Results for '" + name + "'");
        return "review/search-results";
    }

    @GetMapping("/create")
    public String showCreateReviewForm(Model model) {
        model.addAttribute("review", new Review());
        model.addAttribute("title", "Create New Review");
        return "review/create";
    }

    @PostMapping("/create")
    public String createReview(@ModelAttribute Review review) {
        reviewService.createReview(review);
        return "redirect:/reviews";
    }

    @GetMapping("/delete/{id}")
    public String deleteReview(@PathVariable("id") Long id) {
        reviewService.deleteReview(id);
        return "redirect:/reviews";
    }

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
