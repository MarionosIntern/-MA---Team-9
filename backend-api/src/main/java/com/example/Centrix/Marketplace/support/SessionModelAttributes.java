package com.example.Centrix.Marketplace.support;

import java.util.List;
import java.util.Set;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.example.Centrix.Marketplace.SessionConstants;
import com.example.Centrix.Marketplace.Cart.SessionCartItem;

import jakarta.servlet.http.HttpSession;

@ControllerAdvice
public class SessionModelAttributes {

    @SuppressWarnings("unchecked")
    @ModelAttribute("sessionCartCount")
    public int sessionCartCount(HttpSession session) {
        Object attribute = session.getAttribute(SessionConstants.CART_KEY);
        if (attribute instanceof List<?>) {
            List<SessionCartItem> items = (List<SessionCartItem>) attribute;
            return items.stream().mapToInt(SessionCartItem::getQuantity).sum();
        }
        return 0;
    }

    @SuppressWarnings("unchecked")
    @ModelAttribute("favoritesCount")
    public int favoritesCount(HttpSession session) {
        Object attribute = session.getAttribute(SessionConstants.FAVORITES_KEY);
        if (attribute instanceof Set<?>) {
            return ((Set<Long>) attribute).size();
        }
        return 0;
    }

    @ModelAttribute("currentCustomerName")
    public String currentCustomerName(HttpSession session) {
        Object name = session.getAttribute(SessionConstants.CUSTOMER_NAME);
        return name != null ? name.toString() : null;
    }
}
