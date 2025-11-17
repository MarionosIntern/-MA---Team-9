package com.example.Centrix.Marketplace.Cart;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/cart")
public class CartController {

    private final CartService cartService;

    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    // ================================
    // 1️⃣ Show cart list or dashboard
    // ================================
    @GetMapping
    public String viewAllCarts(Model model) {
        model.addAttribute("cartList", cartService.getAllCarts());
        model.addAttribute("title", "All Carts");
        return "cart/list";  // templates/cart/list.html
    }

    // ================================
    // 2️⃣ View individual cart
    // ================================
    @GetMapping("/{cartId}")
    public String viewCart(@PathVariable Long cartId, Model model) {
        model.addAttribute("cart", cartService.getCart(cartId));
        model.addAttribute("title", "Cart Details");
        return "cart/details"; // templates/cart/details.html
    }

    // ================================
    // 3️⃣ Show form to create a new cart
    // ================================
    @GetMapping("/create")
    public String showCreateForm(Model model) {
        model.addAttribute("cartRequest", new CartRequest());
        model.addAttribute("title", "Create New Cart");
        return "cart/create"; // templates/cart/create.html
    }

    // ================================
    // 4️⃣ Handle cart creation (with providerId)
    // ================================
    @PostMapping("/create")
    public String createCart(@RequestParam Long providerId, @ModelAttribute CartRequest cartRequest) {
        cartService.createCart(providerId, cartRequest);
        return "redirect:/cart";
    }

    // ================================
    // 5️⃣ Add an item to a cart
    // ================================
    @PostMapping("/{cartId}/add")
    public String addItem(@PathVariable Long cartId, @ModelAttribute CartRequest request) {
        cartService.addItemToCart(cartId, request);
        return "redirect:/cart/" + cartId;
    }

    // ================================
    // 6️⃣ Apply subscription discount
    // ================================
    @PostMapping("/{cartId}/subscription")
    public String applySubscription(@PathVariable Long cartId, @RequestParam String subscription) {
        cartService.applySubscription(cartId, subscription);
        return "redirect:/cart/" + cartId;
    }

    // ================================
    // 7️⃣ Clear the cart
    // ================================
    @GetMapping("/{cartId}/clear")
    public String clearCart(@PathVariable Long cartId) {
        cartService.clearCart(cartId);
        return "redirect:/cart";
    }

    // ================================
    // 8️⃣ Redirect root to /cart
    // ================================
    @GetMapping("/")
    public String redirectToList() {
        return "redirect:/cart";
    }
}
