<#import "/layouts/base.ftlh" as layout>
<@layout.base title="All Reviews">

<h1>All Reviews</h1>

<table>
<thead>
<tr>
    <th>ID</th>
    <th>Customer</th>
    <th>Product</th>
    <th>Rating</th>
    <th>Details</th>
</tr>
</thead>

<tbody>
<#list reviewList as r>
<tr>
    <td>${r.id}</td>
    <td>${r.customer.name}</td>
    <td>${r.product.name}</td>
    <td>${r.overallRating}</td>
    <td><a href="/reviews/${r.id}">View</a></td>
</tr>
</#list>
</tbody>
</table>

</@layout.base>
