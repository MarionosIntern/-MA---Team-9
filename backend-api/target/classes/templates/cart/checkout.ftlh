<#import "/layouts/base.ftlh" as layout>
<@layout.block pageTitle="Checkout - Centrix Marketplace">

<div class="container py-5">
    <h1 class="mb-4">Checkout</h1>

    <div class="row g-4">
        <!-- Left column: Shipping & Payment Info -->
        <div class="col-md-7">
            <div class="card shadow-sm mb-4">
                <div class="card-body">
                    <h4 class="mb-3">Shipping Information</h4>
                    <form method="post" action="/checkout/submit">
                        <div class="mb-3">
                            <label class="form-label">Full Name</label>
                            <input type="text" class="form-control" name="name" placeholder="John Doe" required>
                        </div>

                        <div class="mb-3">
                            <label class="form-label">Email</label>
                            <input type="email" class="form-control" name="email" placeholder="you@example.com" required>
                        </div>

                        <div class="mb-3">
                            <label class="form-label">Shipping Address</label>
                            <input type="text" class="form-control" name="address" placeholder="123 Main St" required>
                        </div>

                        <div class="mb-3">
                            <label class="form-label">Phone Number</label>
                            <input type="tel" class="form-control" name="phone" placeholder="(555) 123-4567">
                        </div>

                        <h4 class="mb-3">Payment Method</h4>
                        <div class="form-check mb-2">
                            <input class="form-check-input" type="radio" name="payment" value="card" checked>
                            <label class="form-check-label">Credit/Debit Card</label>
                        </div>
                        <div class="form-check mb-3">
                            <input class="form-check-input" type="radio" name="payment" value="paypal">
                            <label class="form-check-label">PayPal</label>
                        </div>

                        <div class="mt-4">
                            <button type="submit" class="btn btn-primary w-100 py-2">Complete Purchase</button>
                        </div>
                    </form>
                </div>
            </div>
        </div>

        <!-- Right column: Order Summary -->
        <div class="col-md-5">
            <div class="card shadow-sm">
                <div class="card-body">
                    <h4 class="mb-3">Order Summary</h4>

                    <#if cartItems?? && cartItems?size > 0>
                        <ul class="list-group mb-3">
                            <#list cartItems as item>
                                <li class="list-group-item d-flex justify-content-between lh-sm">
                                    <div>
                                        <h6 class="my-0">${item.productName}</h6>
                                        <small class="text-muted">Qty: ${item.quantity}</small>
                                    </div>
                                    <span class="text-muted">$${item.subtotal}</span>
                                </li>
                            </#list>
                            <li class="list-group-item d-flex justify-content-between">
                                <span>Total (USD)</span>
                                <strong>$${cartTotal}</strong>
                            </li>
                        </ul>
                    <#else>
                        <div class="alert alert-info mb-0">Your cart is empty.</div>
                    </#if>

                    <#if subscription??>
                        <div class="mt-3">
                            <span class="badge bg-success">Subscription Applied: ${subscription}</span>
                        </div>
                    </#if>
                </div>
            </div>
        </div>
    </div>
</div>

</@layout.block>
