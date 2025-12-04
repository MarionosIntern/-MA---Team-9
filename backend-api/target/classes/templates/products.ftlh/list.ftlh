<#include "/layout/header.ftlh">

<h2>Products</h2>

<table>
    <tr>
        <th>Name</th>
        <th>Category</th>
        <th>Price</th>
        <th>Actions</th>
    </tr>
    <#list products as p>
        <tr>
            <td>${p.name}</td>
            <td>${p.category}</td>
            <td>$${p.price}</td>
            <td>
                <a href="/products/${p.id}">View</a> |
                <a href="/products/${p.id}/edit">Edit</a> |
                <a href="/products/${p.id}/delete">Delete</a>
            </td>
        </tr>
    </#list>
</table>

<a href="/products/create">Create New Product</a>

<#include "/layout/footer.ftlh">
