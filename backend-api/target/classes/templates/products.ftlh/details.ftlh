<#include "/layout/header.ftlh">

<h2>${product.name}</h2>

<p><strong>Category:</strong> ${product.category}</p>
<p><strong>Price:</strong> $${product.price}</p>
<p><strong>Status:</strong> ${product.status}</p>
<p><strong>Description:</strong> ${product.description}</p>

<a href="/products/${product.id}/edit">Edit</a> |
<a href="/products">Back to list</a>

<#include "/layout/footer.ftlh">
