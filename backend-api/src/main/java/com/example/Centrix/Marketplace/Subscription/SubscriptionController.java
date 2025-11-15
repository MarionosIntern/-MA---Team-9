package com.example.Centrix.Marketplace.Subscription;

import com.example.Centrix.Marketplace.Customer.Customer;
import com.example.Centrix.Marketplace.Customer.CustomerService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/subscriptions")
public class SubscriptionController {

    private final SubscriptionService subscriptionService;
    private final CustomerService customerService;

    public SubscriptionController(SubscriptionService subscriptionService, CustomerService customerService) {
        this.subscriptionService = subscriptionService;
        this.customerService = customerService;
    }

    // ================================
    // 1️⃣ List all subscriptions
    // ================================
    @GetMapping
    public String getAllSubscriptions(Model model) {
        model.addAttribute("subscriptionList", subscriptionService.getAllSubscriptions());
        model.addAttribute("title", "Subscription List");
        return "subscription/list"; // templates/subscription/list.html
    }

    // ================================
    // 2️⃣ Get subscription by ID
    // ================================
    @GetMapping("/{id}")
    public String getSubscriptionById(@PathVariable Long id, Model model) {
        model.addAttribute("subscription", subscriptionService.getSubscriptionById(id));
        model.addAttribute("title", "Subscription Details");
        return "subscription/details"; // templates/subscription/details.html
    }

    // ================================
    // 3️⃣ Get subscriptions by customer name
    // ================================
   @GetMapping("/searchByCustomerName")
public String getSubscriptionsByCustomerName(@RequestParam(required = false) String name, Model model) {
    if (name == null || name.isBlank()) {
        return "redirect:/subscriptions";
    }

    List<Customer> customers = customerService.getCustomerByName(name);
    model.addAttribute("customerList", customers);
    model.addAttribute("title", "Customers matching: " + name);
    return "customer/list"; // or "subscription/list" if you want to show their subscriptions too
}

    // ================================
    // 4️⃣ Show form to create subscription
    // ================================
    @GetMapping("/create")
    public String showCreateForm(Model model) {
        model.addAttribute("subscription", new Subscription());
        model.addAttribute("title", "Create New Subscription");
        return "subscription/create"; // templates/subscription/create.html
    }

    // ================================
    // 5️⃣ Handle new subscription creation
    // ================================
    @PostMapping("/create")
    public String createSubscription(@ModelAttribute Subscription subscription) {
        subscriptionService.createSubscription(subscription);
        return "redirect:/subscriptions";
    }

    // ================================
    // 6️⃣ Cancel a subscription
    // ================================
    @GetMapping("/cancel/{id}")
    public String cancelSubscription(@PathVariable Long id) {
        subscriptionService.cancelSubscription(id);
        return "redirect:/subscriptions";
    }

    // ================================
    // 7️⃣ Show all subscriptions for a specific customer
    // ================================
    @GetMapping("/customer/{customerId}")
    public String getCustomerSubscriptions(@PathVariable Long customerId, Model model) {
        model.addAttribute("subscriptionList", subscriptionService.getSubscriptionsByCustomerId(customerId));
        model.addAttribute("title", "Subscriptions for Customer ID: " + customerId);
        return "subscription/list";
    }

    // ================================
    // 8️⃣ Default redirect
    // ================================
    @GetMapping("/")
    public String redirectToList() {
        return "redirect:/subscriptions";
    }
}
