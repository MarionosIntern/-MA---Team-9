package com.example.Centrix.Marketplace.Favorites;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
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
@RequestMapping("/favorites")
public class FavoritesController {

    private final ProductService productService;

    public FavoritesController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public String viewFavorites(HttpSession session, Model model) {
        Set<Long> favoriteIds = getFavoriteIds(session);
        List<Product> favorites = favoriteIds.stream()
                .map(id -> {
                    try {
                        return productService.findById(id);
                    } catch (Exception ex) {
                        return null;
                    }
                })
                .filter(product -> product != null)
                .collect(Collectors.toList());
        model.addAttribute("favorites", favorites);
        return "favorites/list";
    }

    @PostMapping("/toggle/{productId}")
    public String toggleFavorite(@PathVariable Long productId,
                                 @RequestParam(name = "redirect", required = false) String redirectTo,
                                 HttpSession session,
                                 RedirectAttributes redirectAttributes) {
        Set<Long> favoriteIds = getFavoriteIds(session);
        Product product;
        try {
            product = productService.findById(productId);
        } catch (Exception ex) {
            redirectAttributes.addFlashAttribute("favoritesMessage", "Product could not be found.");
            return "redirect:" + normalizeRedirect(redirectTo);
        }
        String message;
        if (favoriteIds.contains(productId)) {
            favoriteIds.remove(productId);
            message = product.getName() + " removed from favorites.";
        } else {
            favoriteIds.add(productId);
            message = product.getName() + " added to favorites.";
        }
        session.setAttribute(SessionConstants.FAVORITES_KEY, favoriteIds);
        redirectAttributes.addFlashAttribute("favoritesMessage", message);
        return "redirect:" + normalizeRedirect(redirectTo);
    }

    @PostMapping("/clear")
    public String clearFavorites(@RequestParam(name = "redirect", required = false) String redirectTo,
                                 HttpSession session,
                                 RedirectAttributes redirectAttributes) {
        session.removeAttribute(SessionConstants.FAVORITES_KEY);
        redirectAttributes.addFlashAttribute("favoritesMessage", "Favorites cleared.");
        return "redirect:" + normalizeRedirect(redirectTo);
    }

    @SuppressWarnings("unchecked")
    private Set<Long> getFavoriteIds(HttpSession session) {
        Object attribute = session.getAttribute(SessionConstants.FAVORITES_KEY);
        if (attribute instanceof Set<?>) {
            return (Set<Long>) attribute;
        }
        Set<Long> favorites = new LinkedHashSet<>();
        session.setAttribute(SessionConstants.FAVORITES_KEY, favorites);
        return favorites;
    }

    private String normalizeRedirect(String redirectTo) {
        if (redirectTo == null || redirectTo.isBlank()) {
            return "/favorites";
        }
        return redirectTo.startsWith("/") ? redirectTo : "/" + redirectTo;
    }
}
