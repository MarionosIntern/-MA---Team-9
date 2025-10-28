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

    @PostMapping("/{cartId}/add")
    public ResponseEntity<CartResponse> addItem(@PathVariable Long cartId, @RequestBody CartRequest request) {
        return ResponseEntity.ok(cartService.addItemToCart(cartId, request));
    }

    @PutMapping("/{cartId}/subscription")
    public ResponseEntity<CartResponse> applySubscription(@PathVariable Long cartId, @RequestParam String subscription) {
        return ResponseEntity.ok(cartService.applySubscription(cartId, subscription));
    }

    @GetMapping("/{cartId}")
    public ResponseEntity<CartResponse> getCart(@PathVariable Long cartId) {
        return ResponseEntity.ok(cartService.getCart(cartId));
    }

    @DeleteMapping("/{cartId}/clear")
    public ResponseEntity<Void> clearCart(@PathVariable Long cartId) {
        cartService.clearCart(cartId);
        return ResponseEntity.noContent().build();
    }
}
