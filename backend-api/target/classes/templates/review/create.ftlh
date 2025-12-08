<#import "/layouts/base.ftlh" as layout>
<@layout.block pageTitle="New Review">

<h1>Create Review</h1>

<form action="/reviews/create" method="post">

    <label>Customer ID</label>
    <input type="number" name="customer.id" required>

    <label>Product ID</label>
    <input type="number" name="product.productId" required>

    <label>Quality Rating</label>
    <input type="number" name="qualityRating" step="0.1" min="0" max="5" required>

    <label>Delivery Rating</label>
    <input type="number" name="deliveryRating" step="0.1" min="0" max="5" required>

    <label>Comment</label>
    <textarea name="comment"></textarea>

    <button type="submit">Submit</button>
</form>

</@layout.block>
