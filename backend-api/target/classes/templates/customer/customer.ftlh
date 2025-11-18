<#import "layouts/base.fthl" as layout>
<@layout.block pageTitle="Customer Dashboard">

<div class="container py-5">
    <h1 class="mb-4">Customer Portal</h1>

    <div class="card shadow-sm p-4 mb-4">
        <h4 class="mb-3">Account Details</h4>

        <#if customer??>
            <p><strong>Name:</strong> ${customer.name}</p>
            <p><strong>Email:</strong> ${customer.email}</p>
            <p><strong>Subscription:</strong> ${customer.subscriptionType! "None"}</p>
        <#else>
            <p class="text-muted">No customer details found. Please <a href="/signin">sign in</a>.</p>
        </#if>
    </div>

    <div class="card shadow-sm p-4 mb-4">
        <h4 class="mb-3">Order History</h4>

        <#if orders?? && orders?size > 0>
            <table class="table table-hover">
                <thead>
                    <tr><th>Order ID</th><th>Date</th><th>Total</th><th>Status</th></tr>
                </thead>
                <tbody>
                <#list orders as order>
                    <tr>
                        <td>${order.id}</td>
                        <td>${order.date}</td>
                        <td>$${order.total}</td>
                        <td>${order.status}</td>
                    </tr>
                </#list>
                </tbody>
            </table>
        <#else>
            <p class="text-muted">You have no past orders.</p>
        </#if>
    </div>

    <a href="/subscriptions" class="btn btn-outline-primary">View Subscriptions</a>
</div>

</@layout.block>
