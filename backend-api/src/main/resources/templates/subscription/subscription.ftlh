<#import "/layouts/base.ftlh" as layout>
<@layout.base title="Your Subscriptions">

<h1>Your Subscriptions</h1>

<p><strong>Customer:</strong> ${customer.name}</p>

<#if subscriptions?size == 0>
    <p>You do not have any active subscriptions.</p>
<#else>

<table>
<thead>
<tr>
    <th>ID</th>
    <th>Type</th>
    <th>Start Date</th>
    <th>End Date</th>
    <th>Active</th>
    <th>Details</th>
    <th>Cancel</th>
</tr>
</thead>

<tbody>
<#list subscriptions as s>
<tr>
    <td>${s.id}</td>
    <td>${s.type}</td>
    <td>${s.startDate?string("yyyy-MM-dd HH:mm")}</td>
    <td>${s.endDate?string("yyyy-MM-dd HH:mm")?default("N/A")}</td>
    <td>${s.active?string("Yes","No")}</td>
    <td><a href="/subscriptions/${s.id}">View</a></td>
    <td><a href="/subscriptions/cancel/${s.id}" onclick="return confirm('Cancel this subscription?')">Cancel</a></td>
</tr>
</#list>
</tbody>

</table>
</#if>

<br>
<a href="/subscriptions/create">➕ Create New Subscription</a>

</@layout.base>
