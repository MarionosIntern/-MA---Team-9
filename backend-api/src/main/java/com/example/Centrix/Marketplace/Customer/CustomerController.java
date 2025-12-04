package com.example.Centrix.Marketplace.Customer;



import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.Centrix.Marketplace.SessionConstants;

import java.util.List;


@Controller
@RequestMapping("/customers") // Simpler & cleaner base path
public class CustomerController {

    private final CustomerService customerService;
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    //  List all customers
    
    @GetMapping
    public String getAllCustomers(Model model) {
        List<Customer> customers = customerService.getAllCustomers();
        model.addAttribute("customerList", customers);
        model.addAttribute("title", "Customer List");
        return "customer/list"; // points to templates/customer/list.html
    }

    
    //  View a customer by ID
    
    @GetMapping("/{id}")
    public String getCustomerById(@PathVariable Long id, Model model) {
        Customer customer = customerService.getCustomerById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid customer ID: " + id));
        model.addAttribute("customer", customer);
        model.addAttribute("title", "Customer Details");
        return "customer/details"; // templates/customer/details.html
    }

    // Search by name
   
    @GetMapping("/searchByName")
    public String getCustomerByName(@RequestParam(required = false) String name, Model model) {
        if (name == null || name.isBlank()) {
            return "redirect:/customers";
        }
        model.addAttribute("customerList", customerService.getCustomerByName(name));
        model.addAttribute("title", "Customers named: " + name);
        return "customer/list";
    }

    // 
    //  Search by email
    
    @GetMapping("/searchByEmail")
    public String getCustomerByEmail(@RequestParam(required = false) String email, Model model) {
        if (email == null || email.isBlank()) {
            return "redirect:/customers";
        }
        model.addAttribute("customerList", customerService.getCustomerByEmail(email));
        model.addAttribute("title", "Customer Email Search");
        return "customer/list";
    }

    // 
    //  Search by phone number
    // 
    @GetMapping("/searchByPhone")
    public String getCustomerByPhone(@RequestParam(required = false) String phoneNumber, Model model) {
        if (phoneNumber == null || phoneNumber.isBlank()) {
            return "redirect:/customers";
        }
        model.addAttribute("customerList", customerService.getCustomerByPhoneNumber(phoneNumber));
        model.addAttribute("title", "Customer Phone Search");
        return "customer/list";
    }

    
    //  Create Form
   
    @GetMapping("/create")
    public String showCreateForm(Model model) {
        model.addAttribute("customer", new Customer());
        model.addAttribute("title", "Create New Customer");
        return "customer/create";
    }

   
    //  Save new customer
  
    @PostMapping
    public String createCustomer(@ModelAttribute Customer customer) {
        Customer saved = customerService.createCustomer(customer);
        return "redirect:/customers/" + saved.getId();
    }

    //  Update Form
   
    @GetMapping("/update/{id}")
    public String showUpdateForm(@PathVariable Long id, Model model) {
        Customer customer = customerService.getCustomerById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid customer ID: " + id));
        model.addAttribute("customer", customer);
        model.addAttribute("title", "Update Customer");
        return "customer/update";
    }

   
    //  Save updated customer
    
    @PostMapping("/update/{id}")
    public String updateCustomer(@PathVariable Long id, @ModelAttribute Customer customer) {
        customerService.updateCustomer(id, customer);
        return "redirect:/customers/" + id;
    }


    //  Delete customer
    
    @GetMapping("/delete/{id}")
    public String deleteCustomer(@PathVariable Long id) {
        customerService.deleteCustomer(id);
        return "redirect:/customers";
    }



    // View Profile
    @GetMapping("/customers/profile")
    public String viewProfile(@PathVariable Long id, Model model) {
        String customer = SessionConstants.CUSTOMER_ID "CURRENT_CUSTOMER"  ;
              .orElseThrow(() -> new IllegalArgumentException("Invalid customer ID: " + id));
        model.addAttribute("customer", customer);
        model.addAttribute("title", "Customer Profile");
        return "customer/profile"; // templates/customer/profile.html
    }


// Default redirect

@GetMapping("/")
public String redirectToList() {
    return "redirect:/customers";
}
}


    







 
