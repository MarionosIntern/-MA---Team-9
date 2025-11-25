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

   
    // List all subscriptions
    
    @GetMapping("/all")
    public String getAllSubscriptions(Model model) {
        model.addAttribute("subscriptionList", subscriptionService.getAllSubscriptions());
        model.addAttribute("title", "Subscription List");
        return "subscription/list"; // templates/subscription/list.html
    }

    
    //  Display subscriptions for the current user
    
    @GetMapping("")
    public String listSubscriptions(Model model) {
        // Get the "logged-in" customer (currently hardcoded to ID 1)
        Customer currentCustomer = customerService.getCurrentCustomer();

        // Retrieve subscriptions for that customer
        model.addAttribute("subscriptions", subscriptionService.getAllForUser(currentCustomer.getId()));

        // Add optional info for template
        model.addAttribute("customer", currentCustomer);
        model.addAttribute("title", "Your Subscriptions");

        // Render the subscriptions page (subscription.fthl)
        return "subscription";
    }
   
    //  Get subscription by ID
    
    @GetMapping("/{id}")
    public String getSubscriptionById(@PathVariable Long id, Model model) {
        model.addAttribute("subscription", subscriptionService.getSubscriptionById(id));
        model.addAttribute("title", "Subscription Details");
        return "subscription/details"; // templates/subscription/details.html
    }

    
    //  Get subscriptions by customer name
    
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

    
    //  Show form to create subscription
    
    @GetMapping("/create")
    public String showCreateForm(Model model) {
        model.addAttribute("subscription", new Subscription());
        model.addAttribute("title", "Create New Subscription");
        return "subscription/create"; // templates/subscription/create.html
    }

    
    //  Handle new subscription creation
    
    @PostMapping("/create")
    public String createSubscription(@ModelAttribute Subscription subscription) {
        subscriptionService.createSubscription(subscription);
        return "redirect:/subscriptions";
    }

    

    
    //  Show all subscriptions for a specific customer
   
    @GetMapping("/customer/{customerId}")
    public String getCustomerSubscriptions(@PathVariable Long customerId, Model model) {
        model.addAttribute("subscriptionList", subscriptionService.getSubscriptionsByCustomerId(customerId));
        model.addAttribute("title", "Subscriptions for Customer ID: " + customerId);
        return "subscription/list";
    }

    @GetMapping("/cancel/{id}")
    public String showCancelConfirmation(@PathVariable Long id, Model model) {
    Subscription subscription = subscriptionService.getSubscriptionById(id);
    model.addAttribute("subscription", subscription);
    return "subscription/cancel-confirmation";
}
    @PostMapping("/cancel/{id}")
public String cancelSubscription(@PathVariable Long id) {
    subscriptionService.cancelSubscription(id);
    return "redirect:/subscriptions";
}





    
    //  Default redirect
    
    @GetMapping("/")
    public String redirectToList() {
        return "redirect:/subscriptions";
    }
}
