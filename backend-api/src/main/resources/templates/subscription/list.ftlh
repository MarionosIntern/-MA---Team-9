<#import "/layouts/base.ftlh" as layout>
<@layout.base title="Your Subscriptions">

<h1>Your Subscriptions</h1>

<table>
<thead>
<tr>
    <th>ID</th>
    <th>Type</th>
    <th>Start</th>
    <th>End</th>
    <th>Active</th>
</tr>
</thead>

<tbody>
<#list subscriptions as s>
<tr>
    <td>${s.id}</td>
    <td>${s.type}</td>
    <td>${s.startDate}</td>
    <td>${s.endDate?default("N/A")}</td>
    <td>${s.active?string("Yes","No")}</td>
</tr>
</#list>
</tbody>

</table>

</@layout.base>
