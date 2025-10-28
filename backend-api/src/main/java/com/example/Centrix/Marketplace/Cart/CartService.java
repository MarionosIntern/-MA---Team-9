package com.example.Centrix.Marketplace.Cart;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class CartService {
        private final CartRepository cartRepository;

        public CartService(CartRepository cartRepository) {
                this.cartRepository = cartRepository;
        }

        public CartResponse createCart(Long providerId) {
                Cart cart = new Cart(providerId);
                Cart saved = cartRepository.save(cart);

                return new CartResponse(saved.id, 0, 0.0, saved.subscription, saved.providerId);
        }

        public CartResponse addItemToCart(Long cartId, CartRequest request) {
                Cart cart = cartRepository.findById(cartId)
                                .orElseThrow(() -> new IllegalArgumentException("Cart not found"));

                CartItem item = new CartItem(request.productId, request.unitPrice, request.quantity);
                item.cart = cart;
                cart.items.add(item);

                Cart saved = cartRepository.save(cart);
                return new CartResponse(saved.id, saved.items.size(), saved.getTotal(), saved.subscription, saved.providerId);
        }

        public CartResponse applySubscription(Long cartId, String subscriptionName) {
                Cart cart = cartRepository.findById(cartId)
                                .orElseThrow(() -> new IllegalArgumentException("Cart not found"));

                cart.subscription = subscriptionName;
                Cart saved = cartRepository.save(cart);

                return new CartResponse(saved.id, saved.items.size(), saved.getTotal(), saved.subscription, saved.providerId);
        }

        public void clearCart(Long cartId) {
                Cart cart = cartRepository.findById(cartId)
                                .orElseThrow(() -> new IllegalArgumentException("Cart not found"));

                cart.items.clear();
                cartRepository.save(cart);
        }

        public CartResponse getCart(Long cartId) {
                Cart cart = cartRepository.findById(cartId)
                                .orElseThrow(() -> new IllegalArgumentException("Cart not found"));

                return new CartResponse(cart.id, cart.items.size(), cart.getTotal(), cart.subscription, cart.providerId);
        }
}