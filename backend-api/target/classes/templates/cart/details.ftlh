<#import "/layouts/base.ftlh" as layout>
<@layout.base title="Cart Details">

<h1>Cart #${cart.id}</h1>

<p><strong>Provider:</strong> ${cart.providerId}</p>
<p><strong>Subscription:</strong> ${cart.subscription}</p>
<p><strong>Total:</strong> $${cart.total?string("0.00")}</p>

<h2>Items</h2>

<table>
    <thead>
    <tr>
        <th>Product ID</th>
        <th>Unit Price</th>
        <th>Quantity</th>
        <th>Subtotal</th>
    </tr>
    </thead>
    <tbody>
    <#list cart.items as item>
        <tr>
            <td>${item.productId}</td>
            <td>$${item.unitPrice}</td>
            <td>${item.quantity}</td>
            <td>$${(item.unitPrice * item.quantity)?string("0.00")}</td>
        </tr>
    </#list>
    </tbody>
</table>

<p><a href="/cart/${cart.id}/cart">Go To Cart Page</a></p>

</@layout.base>
