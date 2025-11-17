<#import "layouts/base.fthl" as layout>
<@layout.block pageTitle="My Cart">

<div class="container py-5">
    <h1 class="mb-4">Your Cart</h1>

    <#if cartItems?? && cartItems?size > 0>
        <table class="table table-bordered align-middle">
            <thead class="table-light">
                <tr>
                    <th>Product</th>
                    <th>Quantity</th>
                    <th>Unit Price</th>
                    <th>Subtotal</th>
                    <th></th>
                </tr>
            </thead>
            <tbody>
            <#list cartItems as item>
                <tr>
                    <td>${item.productName}</td>
                    <td>${item.quantity}</td>
                    <td>$${item.unitPrice}</td>
                    <td>$${item.subtotal}</td>
                    <td>
                        <form action="/cart/remove/${item.productId}" method="post" style="display:inline;">
                            <button class="btn btn-outline-danger btn-sm" type="submit">Remove</button>
                        </form>
                    </td>
                </tr>
            </#list>
            </tbody>
        </table>

        <div class="d-flex justify-content-end align-items-center">
            <h5 class="me-3">Total: <span class="text-success">$${cartTotal}</span></h5>
            <a href="/checkout" class="btn btn-primary">Proceed to Checkout</a>
        </div>

    <#else>
        <div class="alert alert-info">Your cart is empty. <a href="/">Browse products</a>.</div>
    </#if>
</div>

</@layout.block>
