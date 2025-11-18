<#include "/layout/header.ftlh">

<h2>Create Product</h2>

<form method="post">
    Name: <input type="text" name="name"><br>
    Category: <input type="text" name="category"><br>
    Price: <input type="number" step="0.01" name="price"><br>
    Description: <textarea name="description"></textarea><br>
    Status: <input type="text" name="status"><br>
    <button type="submit">Create</button>
</form>

<#include "/layout/footer.ftlh">
