<#import "/layouts/base.ftlh" as layout>
<@layout.base title="All Carts">

<h1>All Carts</h1>

<table>
    <thead>
        <tr>
            <th>ID</th>
            <th>Provider</th>
            <th>Items</th>
            <th>Total</th>
            <th>View</th>
        </tr>
    </thead>

    <tbody>
    <#list cartList as cart>
        <tr>
            <td>${cart.id}</td>
            <td>${cart.providerId}</td>
            <td>${cart.items?size}</td>
            <td>$${cart.total?string("0.00")}</td>
            <td>
                <a href="/cart/${cart.id}">View</a>
            </td>
        </tr>
    </#list>
    </tbody>
</table>

</@layout.base>
