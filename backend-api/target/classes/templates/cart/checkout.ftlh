<#import "/layouts/base.ftlh" as layout>
<@layout.block pageTitle="Checkout - Centrix Marketplace">

<div class="container py-5">
    <h1 class="mb-4">Checkout</h1>

    <#if checkoutError??>
        <div class="alert alert-danger">${checkoutError}</div>
    </#if>
    <#if checkoutMessage??>
        <div class="alert alert-success">${checkoutMessage}</div>
    </#if>

    <div class="row g-4">
        <!-- Left column: Shipping & Payment Info -->
        <div class="col-md-7">
            <div class="card shadow-sm mb-4">
                <div class="card-body">
                    <h4 class="mb-3">Shipping & Contact</h4>
                    <form method="post" action="/cart/checkout/submit" class="d-grid gap-3">
                        <div class="row g-3">
                            <div class="col-sm-6">
                                <label class="form-label">Full Name</label>
                                <input type="text" class="form-control" name="name" placeholder="John Doe" required>
                            </div>
                            <div class="col-sm-6">
                                <label class="form-label">Email</label>
                                <input type="email" class="form-control" name="email" placeholder="you@example.com" required>
                            </div>
                            <div class="col-12">
                                <label class="form-label">Shipping Address</label>
                                <input type="text" class="form-control" name="address" placeholder="123 Main St" required>
                            </div>
                            <div class="col-sm-6">
                                <label class="form-label">Phone Number</label>
                                <input type="tel" class="form-control" name="phone" placeholder="(555) 123-4567">
                            </div>
                        </div>

                        <h4 class="mb-2">Payment</h4>
                        <div class="form-check mb-2">
                            <input class="form-check-input" type="radio" name="payment" value="card" id="pay-card" checked>
                            <label class="form-check-label" for="pay-card">Credit/Debit Card</label>
                        </div>
                        <div class="row g-3 ms-1">
                            <div class="col-12">
                                <label class="form-label">Card Number</label>
                                <input class="form-control" name="cardNumber" placeholder="4242 4242 4242 4242" required>
                            </div>
                            <div class="col-6">
                                <label class="form-label">Expiry</label>
                                <input class="form-control" name="cardExpiry" placeholder="12/27" required>
                            </div>
                            <div class="col-6">
                                <label class="form-label">CVV</label>
                                <input class="form-control" name="cardCvv" placeholder="123" required>
                            </div>
                        </div>
                        <div class="form-check mb-3">
                            <input class="form-check-input" type="radio" name="payment" value="paypal" id="pay-paypal">
                            <label class="form-check-label" for="pay-paypal">PayPal</label>
                        </div>

        <!-- Right column: Order Summary -->
                        <button type="submit" class="btn btn-primary w-100 py-2">Complete Purchase</button>
                    </form>
                </div>
            </div>
        </div>

        <div class="col-md-5">
            <div class="card shadow-sm mb-3">
                <div class="card-body">
                    <h4 class="mb-3">Order Summary</h4>

                    <#if cartItems?? && (cartItems?size > 0)>
                        <ul class="list-group mb-3">
                            <#list cartItems as item>
                                <li class="list-group-item d-flex justify-content-between lh-sm">
                                    <div>
                                        <h6 class="my-0">${item.productName}</h6>
                                        <small class="text-muted">Qty: ${item.quantity}</small>
                                    </div>
                                    <span class="text-muted">$${(item.subtotal)?string["0.00"]}</span>
                                </li>
                            </#list>
                            <li class="list-group-item d-flex justify-content-between">
                                <span>Subtotal</span>
                                <strong>$${(cartTotal!0)?string["0.00"]}</strong>
                            </li>
                            <#if discount?? && discount gt 0>
                                <li class="list-group-item d-flex justify-content-between text-success">
                                    <span>Savings</span>
                                    <strong>- $${(discount)?string["0.00"]}</strong>
                                </li>
                            </#if>
                            <li class="list-group-item d-flex justify-content-between">
                                <span>Total (USD)</span>
                                <strong>$${(grandTotal!cartTotal)?string["0.00"]}</strong>
                            </li>
                        </ul>
                    <#else>
                        <div class="alert alert-info mb-0">Your cart is empty.</div>
                    </#if>

                    <div class="d-grid gap-3">
                        <form class="d-flex gap-2" action="/cart/checkout/apply-coupon" method="post">
                            <input class="form-control" name="couponCode" placeholder="Coupon code (SAVE10, SAVE20, FREESHIP)" value="${couponCode!""}">
                            <button class="btn btn-outline-primary" type="submit">Apply</button>
                        </form>

                        <form class="d-flex gap-2" action="/cart/checkout/apply-subscription" method="post">
                            <select class="form-select" name="subscription">
                                <option value="">Choose subscription special</option>
                                <option value="BASIC" <#if subscriptionCode?? && subscriptionCode == "BASIC">selected</#if>>Basic (5% off)</option>
                                <option value="PLUS" <#if subscriptionCode?? && subscriptionCode == "PLUS">selected</#if>>Plus (8% off)</option>
                                <option value="PREMIUM" <#if subscriptionCode?? && subscriptionCode == "PREMIUM">selected</#if>>Premium (12% off)</option>
                            </select>
                            <button class="btn btn-outline-secondary" type="submit">Apply</button>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

</@layout.block>
