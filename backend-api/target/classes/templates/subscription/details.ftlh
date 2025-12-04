<#import "/layouts/base.ftlh" as layout>
<@layout.base title="Subscription Details">

<h1>Subscription Details</h1>

<p><strong>ID:</strong> ${subscription.id}</p>
<p><strong>Customer:</strong> ${subscription.customer.name}</p>
<p><strong>Email:</strong> ${subscription.customer.email}</p>

<p><strong>Type:</strong> ${subscription.type}</p>

<p><strong>Start Date:</strong>
    ${subscription.startDate?string("yyyy-MM-dd HH:mm")}
</p>

<p><strong>End Date:</strong>
    ${subscription.endDate?string("yyyy-MM-dd HH:mm")?default("N/A")}
</p>

<p><strong>Active:</strong>
    ${subscription.active?string("Yes","No")}
</p>

<hr>

<a href="/subscriptions">← Back to Your Subscriptions</a> |
<a href="/subscriptions/cancel/${subscription.id}" onclick="return confirm('Are you sure?')">Cancel Subscription</a>

</@layout.base>
