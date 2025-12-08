package com.example.Centrix.Marketplace;

/**
 * Central place for session attribute keys so controllers/templates stay in sync.
 */
public final class SessionConstants {

    private SessionConstants() {
    }

    // Customer session keys
    public static final String CART_KEY = "SESSION_CART";
    public static final String FAVORITES_KEY = "SESSION_FAVORITES";
    public static final String CUSTOMER_ID = "CURRENT_CUSTOMER_ID";
    public static final String CUSTOMER_NAME = "CURRENT_CUSTOMER_NAME";

    // Provider session keys
    public static final String PROVIDER_ID = "providerId";

    // Cart extras
    public static final String COUPON_CODE = "SESSION_COUPON_CODE";
    public static final String SUBSCRIPTION_CODE = "SESSION_SUBSCRIPTION_CODE";
}
