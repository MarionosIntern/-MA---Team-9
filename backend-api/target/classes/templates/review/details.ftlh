<#import "/layouts/base.ftlh" as layout>
<@layout.base title="Review Details">

<h1>Review #${review.id}</h1>

<p><strong>Customer:</strong> ${review.customer.name}</p>
<p><strong>Product:</strong> ${review.product.name}</p>
<p><strong>Quality Rating:</strong> ${review.qualityRating}</p>
<p><strong>Delivery Rating:</strong> ${review.deliveryRating}</p>
<p><strong>Overall Rating:</strong> ${review.overallRating}</p>

<p><strong>Comment:</strong><br/> ${review.comment}</p>

<p>
<strong>Provider Response:</strong><br/>
${review.providerResponse?default("No response yet")}
</p>

<a href="/reviews/delete/${review.id}" class="btn btn-danger">Delete Review</a>

</@layout.base>
