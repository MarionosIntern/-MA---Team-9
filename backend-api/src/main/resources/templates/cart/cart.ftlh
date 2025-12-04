<#import "/layouts/base.ftlh" as layout>
<@layout.block pageTitle="My Cart">

<div class="container py-5">
    <h1 class="mb-4">Your Cart</h1>

    <#if cartMessage??>
        <div class="alert alert-success">${cartMessage}</div>
    </#if>

    <#if cartItems?? && (cartItems?size > 0)>
        <table class="table table-bordered align-middle">
            <thead class="table-light">
                <tr>
                    <th>Preview</th>
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
                    <td style="width:90px;">
                        <img src="${(item.imageUrl)!'/images/placeholder-product.png'}"
                             alt="${item.productName}"
                             class="img-fluid rounded"
                             style="max-height:70px; object-fit:contain;">
                    </td>
                    <td>${item.productName}</td>
                    <td>${item.quantity}</td>
                    <td>$${(item.unitPrice)?string["0.00"]}</td>
                    <td>$${(item.subtotal)?string["0.00"]}</td>
                    <td>
                        <form action="/cart/remove/${item.productId}" method="post" class="d-inline">
                            <input type="hidden" name="redirect" value="/cart/view">
                            <button class="btn btn-outline-danger btn-sm" type="submit">Remove</button>
                        </form>
                    </td>
                </tr>
            </#list>
            </tbody>
        </table>

        <div class="d-flex justify-content-between align-items-center flex-wrap gap-3">
            <div class="d-flex align-items-center gap-2">
                <h5 class="mb-0">Total: <span class="text-success">$${(cartTotal!0)?string["0.00"]}</span></h5>
                <form action="/cart/clear" method="post">
                    <input type="hidden" name="redirect" value="/cart/view">
                    <button type="submit" class="btn btn-outline-secondary btn-sm">Clear cart</button>
                </form>
            </div>
            <button class="btn btn-primary" type="button" disabled>Checkout coming soon</button>
        </div>
    <#else>
        <div class="alert alert-info">Your cart is empty. <a href="/">Browse products</a>.</div>
    </#if>
</div>

</@layout.block>
