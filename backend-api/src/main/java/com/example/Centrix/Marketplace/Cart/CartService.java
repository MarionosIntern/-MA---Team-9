package com.example.Centrix.Marketplace.Cart;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@Transactional
public class CartService {

    private final CartRepository cartRepository;

    public CartService(CartRepository cartRepository) {
        this.cartRepository = cartRepository;
    }

    // ================================
    // 1️⃣ Get all carts
    // ================================
    public List<Cart> getAllCarts() {
        return cartRepository.findAll();
    }

    // ================================
    // 2️⃣ Create a new cart for a provider
    // ================================
    public Cart createCart(Long providerId, CartRequest request) {
        if (providerId == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Provider ID is required");
        }

        Cart cart = new Cart(providerId);
        cartRepository.save(cart);
        return cart;
    }

    // ================================
    // 3️⃣ Add an item to the cart
    // ================================
    public Cart addItemToCart(Long cartId, CartRequest request) {
        Cart cart = cartRepository.findById(cartId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cart not found"));

        if (request == null || request.getProductId() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid item details");
        }

        CartItem item = new CartItem(request.getProductId(), request.getUnitPrice(), request.getQuantity());
        item.setCart(cart);

        cart.getItems().add(item);
        return cartRepository.save(cart);
    }

    // ================================
    // 4️⃣ Apply a subscription to the cart
    // ================================
    public Cart applySubscription(Long cartId, String subscriptionName) {
        Cart cart = cartRepository.findById(cartId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cart not found"));

        cart.setSubscription(subscriptionName);
        return cartRepository.save(cart);
    }

    // ================================
    // 5️⃣ Get a single cart
    // ================================
    public Cart getCart(Long cartId) {
        return cartRepository.findById(cartId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cart not found"));
    }

    // ================================
    // 6️⃣ Clear all items in a cart
    // ================================
    public void clearCart(Long cartId) {
        Cart cart = cartRepository.findById(cartId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cart not found"));

        cart.getItems().clear();
        cartRepository.save(cart);
    }
}
