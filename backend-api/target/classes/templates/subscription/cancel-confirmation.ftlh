<#import "/layouts/base.ftlh" as layout>
<@layout.base title="Cancel Subscription">

<h1>Cancel Subscription</h1>

<p>Are you sure you want to cancel this subscription?</p>

<div class="subscription-details-box">

    <p><strong>Subscription ID:</strong> ${subscription.id}</p>
    <p><strong>Customer:</strong> ${subscription.customer.name}</p>
    <p><strong>Type:</strong> ${subscription.type}</p>

    <p><strong>Start Date:</strong>
        ${subscription.startDate?string("yyyy-MM-dd HH:mm")}
    </p>

    <p><strong>End Date:</strong>
        ${subscription.endDate?string("yyyy-MM-dd HH:mm")?default("N/A")}
    </p>

    <p><strong>Status:</strong>
        ${subscription.active?string("Active", "Canceled")}
    </p>
</div>

<br>

<form action="/subscriptions/cancel/${subscription.id}" method="post">
    <button type="submit" style="background-color: red; color: white; padding: 10px;">
        Yes, Cancel Subscription
    </button>
</form>

<br>
<a href="/subscriptions">← No, go back</a>

</@layout.base>
