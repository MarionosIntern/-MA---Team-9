<#import "/layouts/base.ftlh" as layout>
<@layout.block pageTitle="Your Reviews">

<h1>Your Reviews</h1>

<p>Customer: ${customer.name}</p>

<table>
<thead>
<tr>
    <th>ID</th>
    <th>Product</th>
    <th>Rating</th>
    <th>Details</th>
</tr>
</thead>

<tbody>
<#list reviews as r>
<tr>
    <td>${r.id}</td>
    <td>${r.product.name}</td>
    <td>${r.overallRating}</td>
    <td><a href="/reviews/${r.id}">View</a></td>
</tr>
</#list>
</tbody>
</table>

<a href="/reviews/create">Write New Review</a>

</@layout.block>
