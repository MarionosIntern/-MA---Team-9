package com.example.Centrix.Marketplace.Customer;

import com.example.Centrix.Marketplace.Subscription.*;

import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.List;


@Controller
@RequestMapping("/customers") // Simpler & cleaner base path
public class CustomerController {

    private final CustomerService customerService;
    private final SubscriptionService subscriptionService;

    public CustomerController(CustomerService customerService, SubscriptionService subscriptionService) {
        this.customerService = customerService;
        this.subscriptionService = subscriptionService;
    }

    // ================================
    // 1️⃣ List all customers
    // ================================
    @GetMapping
    public String getAllCustomers(Model model) {
        List<Customer> customers = customerService.getAllCustomers();
        model.addAttribute("customerList", customers);
        model.addAttribute("title", "Customer List");
        return "customer/list"; // points to templates/customer/list.html
    }

    // ================================
    // 2️⃣ View a customer by ID
    // ================================
    @GetMapping("/{id}")
    public String getCustomerById(@PathVariable Long id, Model model) {
        Customer customer = customerService.getCustomerById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid customer ID: " + id));
        model.addAttribute("customer", customer);
        model.addAttribute("title", "Customer Details");
        return "customer/details"; // templates/customer/details.html
    }

    // ================================
    // 3️⃣ Search by name
    // ================================
    @GetMapping("/searchByName")
    public String getCustomerByName(@RequestParam(required = false) String name, Model model) {
        if (name == null || name.isBlank()) {
            return "redirect:/customers";
        }
        model.addAttribute("customerList", customerService.getCustomerByName(name));
        model.addAttribute("title", "Customers named: " + name);
        return "customer/list";
    }

    // ================================
    // 4️⃣ Search by email
    // ================================
    @GetMapping("/searchByEmail")
    public String getCustomerByEmail(@RequestParam(required = false) String email, Model model) {
        if (email == null || email.isBlank()) {
            return "redirect:/customers";
        }
        model.addAttribute("customerList", customerService.getCustomerByEmail(email));
        model.addAttribute("title", "Customer Email Search");
        return "customer/list";
    }

    // ================================
    // 5️⃣ Search by phone number
    // ================================
    @GetMapping("/searchByPhone")
    public String getCustomerByPhone(@RequestParam(required = false) String phoneNumber, Model model) {
        if (phoneNumber == null || phoneNumber.isBlank()) {
            return "redirect:/customers";
        }
        model.addAttribute("customerList", customerService.getCustomerByPhoneNumber(phoneNumber));
        model.addAttribute("title", "Customer Phone Search");
        return "customer/list";
    }

    // ================================
    // 6️⃣ Create Form
    // ================================
    @GetMapping("/create")
    public String showCreateForm(Model model) {
        model.addAttribute("customer", new Customer());
        model.addAttribute("title", "Create New Customer");
        return "customer/create";
    }

    // ================================
    // 7️⃣ Save new customer
    // ================================
    @PostMapping
    public String createCustomer(@ModelAttribute Customer customer) {
        Customer saved = customerService.createCustomer(customer);
        return "redirect:/customers/" + saved.getId();
    }

    // ================================
    // 8️⃣ Update Form
    // ================================
    @GetMapping("/update/{id}")
    public String showUpdateForm(@PathVariable Long id, Model model) {
        Customer customer = customerService.getCustomerById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid customer ID: " + id));
        model.addAttribute("customer", customer);
        model.addAttribute("title", "Update Customer");
        return "customer/update";
    }

    // ================================
    // 9️⃣ Save updated customer
    // ================================
    @PostMapping("/update/{id}")
    public String updateCustomer(@PathVariable Long id, @ModelAttribute Customer customer) {
        customerService.updateCustomer(id, customer);
        return "redirect:/customers/" + id;
    }

    // ================================
    // 🔟 Delete customer
    // ================================
    @GetMapping("/delete/{id}")
    public String deleteCustomer(@PathVariable Long id) {
        customerService.deleteCustomer(id);
        return "redirect:/customers";
    }

    // ================================
    // 🔁 Default redirect
    // ================================
    @GetMapping("/")
    public String redirectToList() {
        return "redirect:/customers";
    }

     @GetMapping("/customer")
    public String dashboard(Model model) {
    // Get the current customer (for now, you could hardcode or fetch by ID)
    Customer customer = customerService.getCurrentCustomer(); // Replace with actual logged-in ID logic
    model.addAttribute("customer", customer);

    // Add this line to show subscriptions
    model.addAttribute("subscriptions", subscriptionService.getAllForUser(customer.getId()));

    return "customer";
}
}





 
