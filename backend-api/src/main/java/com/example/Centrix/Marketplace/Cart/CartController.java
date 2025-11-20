package com.example.Centrix.Marketplace.Cart;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.Centrix.Marketplace.SessionConstants;
import com.example.Centrix.Marketplace.Product.Product;
import com.example.Centrix.Marketplace.Product.ProductService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/cart")
public class CartController {

    private static final String DEFAULT_REDIRECT = "/cart/view";

    private final CartService cartService;
    private final ProductService productService;

    public CartController(CartService cartService, ProductService productService) {
        this.cartService = cartService;
        this.productService = productService;
    }

    
    // Admin/Provider cart screens
    
    @GetMapping
    public String viewAllCarts(Model model) {
        model.addAttribute("cartList", cartService.getAllCarts());
        model.addAttribute("title", "All Carts");
        return "cart/list";
    }

    @GetMapping("/{cartId}")
    public String viewCart(@PathVariable Long cartId, Model model) {
        model.addAttribute("cart", cartService.getCart(cartId));
        model.addAttribute("title", "Cart Details");
        return "cart/details";
    }

    @GetMapping("/{cartId}/cart")
    public String showCart(@PathVariable Long cartId, Model model) {
        model.addAttribute("cartItems", cartService.getItems(cartId));
        model.addAttribute("cartTotal", cartService.getTotal(cartId));
        model.addAttribute("subscription", cartService.getAppliedSubscription(cartId));
        model.addAttribute("cartId", cartId);
        return "cart/cart";
    }

    @GetMapping("/create")
    public String showCreateForm(Model model) {
        model.addAttribute("cartRequest", new CartRequest());
        model.addAttribute("title", "Create New Cart");
        return "cart/create";
    }

    @PostMapping("/create")
    public String createCart(@RequestParam Long providerId, @ModelAttribute CartRequest cartRequest) {
        cartService.createCart(providerId, cartRequest);
        return "redirect:/cart";
    }

    @PostMapping("/{cartId}/add")
    public String addItem(@PathVariable Long cartId, @ModelAttribute CartRequest request) {
        cartService.addItemToCart(cartId, request);
        return "redirect:/cart/" + cartId;
    }

    @PostMapping("/{cartId}/subscription")
    public String applySubscription(@PathVariable Long cartId, @RequestParam String subscription) {
        cartService.applySubscription(cartId, subscription);
        return "redirect:/cart/" + cartId;
    }

    @GetMapping("/checkout/{cartId}")
    public String showCheckout(@PathVariable Long cartId, Model model) {
        model.addAttribute("cartItems", cartService.getItems(cartId));
        model.addAttribute("cartTotal", cartService.getTotal(cartId));
        model.addAttribute("subscription", cartService.getAppliedSubscription(cartId));
        model.addAttribute("cartId", cartId);
        return "cart/checkout";
    }

    @PostMapping("/checkout/{cartId}/submit")
    public String processCheckout(@PathVariable Long cartId) {
        return "redirect:/confirmation";
    }

    @GetMapping("/{cartId}/clear")
    public String clearCart(@PathVariable Long cartId) {
        cartService.clearCart(cartId);
        return "redirect:/cart";
    }

    @GetMapping("/")
    public String redirectToList() {
        return "redirect:/cart";
    }

    
    // Session-based customer cart
    

    @GetMapping("/view")
    public String viewSessionCart(HttpSession session, Model model) {
        List<SessionCartItem> cartItems = getSessionCart(session);
        model.addAttribute("cartItems", cartItems);
        model.addAttribute("cartTotal", calculateCartTotal(cartItems));
        model.addAttribute("cartId", session.getId());
        return "cart/cart";
    }

    @PostMapping("/add/{productId}")
    public String addProductToSessionCart(@PathVariable Long productId,
                                          @RequestParam(defaultValue = "1") int quantity,
                                          @RequestParam(name = "redirect", required = false) String redirectTo,
                                          HttpSession session,
                                          RedirectAttributes redirectAttributes) {
        Product product = productService.findById(productId);
        List<SessionCartItem> cartItems = getSessionCart(session);
        Optional<SessionCartItem> existing = cartItems.stream()
                .filter(item -> item.getProductId().equals(productId))
                .findFirst();

        int safeQuantity = Math.max(quantity, 1);
        if (existing.isPresent()) {
            existing.get().incrementQuantity(safeQuantity);
        } else {
            cartItems.add(new SessionCartItem(productId,
                    product.getName(),
                    product.getPrice(),
                    safeQuantity,
                    product.getImageUrl()));
        }

        redirectAttributes.addFlashAttribute("cartMessage", product.getName() + " added to your cart.");
        return "redirect:" + normalizeRedirect(redirectTo);
    }

    @PostMapping("/remove/{productId}")
    public String removeFromSessionCart(@PathVariable Long productId,
                                        @RequestParam(name = "redirect", required = false) String redirectTo,
                                        HttpSession session,
                                        RedirectAttributes redirectAttributes) {
        List<SessionCartItem> cartItems = getSessionCart(session);
        cartItems.removeIf(item -> item.getProductId().equals(productId));
        redirectAttributes.addFlashAttribute("cartMessage", "Item removed from your cart.");
        return "redirect:" + normalizeRedirect(redirectTo);
    }

    @PostMapping("/clear")
    public String clearSessionCart(@RequestParam(name = "redirect", required = false) String redirectTo,
                                   HttpSession session,
                                   RedirectAttributes redirectAttributes) {
        session.removeAttribute(SessionConstants.CART_KEY);
        redirectAttributes.addFlashAttribute("cartMessage", "Cart cleared.");
        return "redirect:" + normalizeRedirect(redirectTo);
    }

    @SuppressWarnings("unchecked")
    private List<SessionCartItem> getSessionCart(HttpSession session) {
        Object attribute = session.getAttribute(SessionConstants.CART_KEY);
        if (attribute instanceof List<?>) {
            return (List<SessionCartItem>) attribute;
        }
        List<SessionCartItem> freshCart = new ArrayList<>();
        session.setAttribute(SessionConstants.CART_KEY, freshCart);
        return freshCart;
    }

    private double calculateCartTotal(List<SessionCartItem> items) {
        return items.stream()
                .mapToDouble(SessionCartItem::getSubtotal)
                .sum();
    }

    private String normalizeRedirect(String redirectTo) {
        if (redirectTo == null || redirectTo.isBlank()) {
            return DEFAULT_REDIRECT;
        }
        return redirectTo.startsWith("/") ? redirectTo : "/" + redirectTo;
    }
}
