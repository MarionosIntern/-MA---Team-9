package com.example.Centrix.Marketplace.Cart;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/cart")
public class CartController {
    private final CartService cartService;

    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @PostMapping("/create/{providerId}")
    public ResponseEntity<CartResponse> createCart(@PathVariable Long providerId) {
        return ResponseEntity.ok(cartService.createCart(providerId));
    }

    // plural alias to support clients that call /api/carts
    @PostMapping("/carts/create/{providerId}")
    public ResponseEntity<CartResponse> createCartPlural(@PathVariable Long providerId) {
        return createCart(providerId);
    }

    @PostMapping("/{cartId}/add")
    public ResponseEntity<CartResponse> addItem(@PathVariable Long cartId, @RequestBody CartRequest request) {
        return ResponseEntity.ok(cartService.addItemToCart(cartId, request));
    }

    @PostMapping("/carts/{cartId}/add")
    public ResponseEntity<CartResponse> addItemPlural(@PathVariable Long cartId, @RequestBody CartRequest request) {
        return addItem(cartId, request);
    }

    @PutMapping("/{cartId}/subscription")
    public ResponseEntity<CartResponse> applySubscription(@PathVariable Long cartId, @RequestParam String subscription) {
        return ResponseEntity.ok(cartService.applySubscription(cartId, subscription));
    }

    @PutMapping("/carts/{cartId}/subscription")
    public ResponseEntity<CartResponse> applySubscriptionPlural(@PathVariable Long cartId, @RequestParam String subscription) {
        return applySubscription(cartId, subscription);
    }

    @GetMapping("/{cartId}")
    public ResponseEntity<CartResponse> getCart(@PathVariable Long cartId) {
        return ResponseEntity.ok(cartService.getCart(cartId));
    }

    @GetMapping("/carts/{cartId}")
    public ResponseEntity<CartResponse> getCartPlural(@PathVariable("cartId") Long cartId) {
        return getCart(cartId);
    }

    @DeleteMapping("/{cartId}/clear")
    public ResponseEntity<Void> clearCart(@PathVariable Long cartId) {
        cartService.clearCart(cartId);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/carts/{cartId}/clear")
    public ResponseEntity<Void> clearCartPlural(@PathVariable Long cartId) {
        return clearCart(cartId);
    }
}
