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
import com.example.Centrix.Marketplace.Provider.Provider;
import com.example.Centrix.Marketplace.Provider.ProviderService;

import jakarta.servlet.http.HttpSession;

@Controller
public class AuthController {

    private final CustomerService customerService;
    private final ProviderService providerService;

    public AuthController(CustomerService customerService, ProviderService providerService) {
        this.customerService = customerService;
        this.providerService = providerService;
    }

    @GetMapping("/signin")
    public String showSignIn(Model model) {
        return "auth/signin";
    }

    @PostMapping("/signin")
    public String handleSignIn(@RequestParam String email,
                               @RequestParam String password,
                               @RequestParam(defaultValue = "CUSTOMER") String role,
                               HttpSession session,
                               RedirectAttributes redirectAttributes) {

        boolean isProvider = "PROVIDER".equalsIgnoreCase(role);

        if (isProvider) {
            try {
                var provider = providerService.authenticate(email, password);
                session.setAttribute(SessionConstants.PROVIDER_ID, provider.getId());
                session.setAttribute(SessionConstants.PROVIDER_NAME, provider.getName());
                redirectAttributes.addFlashAttribute("loginSuccess", "Welcome back, " + provider.getName() + "!");
                return "redirect:/providers/home";
            } catch (Exception ex) {
                redirectAttributes.addFlashAttribute("loginError", "Invalid provider email or password.");
                return "redirect:/signin";
            }
        } else {
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
    }

    @GetMapping("/signup")
    public String showSignUp(Model model) {
        return "auth/signup";
    }

    @PostMapping("/signup")
    public String handleSignUp(@RequestParam String name,
                               @RequestParam String email,
                               @RequestParam String password,
                               @RequestParam(defaultValue = "CUSTOMER") String role,
                               @RequestParam(required = false) String phoneNumber,
                               @RequestParam(required = false) String shippingAddress,
                               HttpSession session,
                               RedirectAttributes redirectAttributes) {
        boolean isProvider = "PROVIDER".equalsIgnoreCase(role);

        if (!isProvider && customerService.getCustomerByEmail(email).isPresent()) {
            redirectAttributes.addFlashAttribute("signupError", "An account with that email already exists.");
            return "redirect:/signup";
        }
        if (isProvider && providerService.findByEmail(email) != null) {
            redirectAttributes.addFlashAttribute("signupError", "An account with that email already exists.");
            return "redirect:/signup";
        }

        if (isProvider) {
            Provider provider = new Provider();
            provider.setName(name);
            provider.setEmail(email);
            provider.setPassword(password);
            provider.setAddress(shippingAddress);
            provider.setPhoneNumber(phoneNumber);
            Provider saved = providerService.createProvider(provider);
            session.setAttribute(SessionConstants.PROVIDER_ID, saved.getId());
            redirectAttributes.addFlashAttribute("loginSuccess", "Welcome to Centrix, " + saved.getName() + "!");
            return "redirect:/providers/home";
        } else {
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
    }

    @PostMapping("/signout")
    public String signOut(HttpSession session, RedirectAttributes redirectAttributes) {
        session.removeAttribute(SessionConstants.CUSTOMER_ID);
        session.removeAttribute(SessionConstants.CUSTOMER_NAME);
        session.removeAttribute(SessionConstants.PROVIDER_ID);
        session.removeAttribute(SessionConstants.PROVIDER_NAME);
        redirectAttributes.addFlashAttribute("loginSuccess", "You have been signed out.");
        return "redirect:/";
    }
}
