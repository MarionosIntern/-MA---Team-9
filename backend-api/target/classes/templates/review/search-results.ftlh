<#import "/layouts/base.ftlh" as layout>
<@layout.base title="Search Results">

<h1>Search Results</h1>

<#if reviews?size == 0>
<p>No reviews found.</p>
<#else>

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
<#list reviews as r>
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
</#if>

</@layout.base>
