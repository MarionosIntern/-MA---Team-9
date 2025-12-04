<#include "/layout/header.ftlh">

<h2>Edit Product</h2>

<form method="post">
    Name: <input type="text" name="name" value="${product.name}"><br>
    Category: <input type="text" name="category" value="${product.category}"><br>
    Price: <input type="number" step="0.01" name="price" value="${product.price}"><br>
    Description: <textarea name="description">${product.description}</textarea><br>
    Status: <input type="text" name="status" value="${product.status}"><br>

    <button type="submit">Save</button>
</form>

<#include "/layout/footer.ftlh">
