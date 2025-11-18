package com.example.Centrix.Marketplace;

import java.util.Optional;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.Centrix.Marketplace.Customer.Customer;
import com.example.Centrix.Marketplace.Customer.CustomerService;

import jakarta.servlet.http.HttpSession;

@Controller
public class AuthController {

    private final CustomerService customerService;

    public AuthController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping("/signin")
    public String showSignIn(Model model) {
        return "auth/signin";
    }

    @PostMapping("/signin")
    public String handleSignIn(@RequestParam String email,
                               @RequestParam String password,
                               HttpSession session,
                               RedirectAttributes redirectAttributes) {
        Optional<Customer> customerOpt = customerService.getCustomerByEmail(email);
        if (customerOpt.isEmpty() || !password.equals(customerOpt.get().getPassword())) {
            redirectAttributes.addFlashAttribute("loginError", "Invalid email or password.");
            return "redirect:/signin";
        }

        Customer customer = customerOpt.get();
        session.setAttribute(SessionConstants.CUSTOMER_ID, customer.getId());
        session.setAttribute(SessionConstants.CUSTOMER_NAME, customer.getName());
        redirectAttributes.addFlashAttribute("loginSuccess", "Welcome back, " + customer.getName() + "!");
        return "redirect:/customer/home";
    }

    @GetMapping("/signup")
    public String showSignUp(Model model) {
        return "auth/signup";
    }

    @PostMapping("/signup")
    public String handleSignUp(@RequestParam String name,
                               @RequestParam String email,
                               @RequestParam String password,
                               @RequestParam(required = false) String phoneNumber,
                               @RequestParam(required = false) String shippingAddress,
                               HttpSession session,
                               RedirectAttributes redirectAttributes) {
        if (customerService.getCustomerByEmail(email).isPresent()) {
            redirectAttributes.addFlashAttribute("signupError", "An account with that email already exists.");
            return "redirect:/signup";
        }

        Customer customer = new Customer();
        customer.setName(name);
        customer.setEmail(email);
        customer.setPassword(password);
        customer.setPhoneNumber(phoneNumber);
        customer.setShippingAddress(shippingAddress);
        Customer saved = customerService.createCustomer(customer);

        session.setAttribute(SessionConstants.CUSTOMER_ID, saved.getId());
        session.setAttribute(SessionConstants.CUSTOMER_NAME, saved.getName());
        redirectAttributes.addFlashAttribute("loginSuccess", "Welcome to Centrix, " + saved.getName() + "!");
        return "redirect:/customer/home";
    }

    @PostMapping("/signout")
    public String signOut(HttpSession session, RedirectAttributes redirectAttributes) {
        session.removeAttribute(SessionConstants.CUSTOMER_ID);
        session.removeAttribute(SessionConstants.CUSTOMER_NAME);
        redirectAttributes.addFlashAttribute("loginSuccess", "You have been signed out.");
        return "redirect:/";
    }
}
