<#import "/layout.ftl" as layout>

<@layout.provider title="Customer Reviews – Centrix Marketplace">

    <div class="container py-3">
        <h1 class="h5 mb-3">Customer Reviews</h1>

        <a href="/providers/reviews/fake" class="demo-button">Generate demo review</a>

        <#if reviews?? && reviews?size gt 0>
            <#list reviews as review>
                <div class="cm-review-card mb-3 p-3">
                    <h2 class="h6 mb-1">${review.product.name}</h2>
                    <p class="cm-muted mb-1">
                        From: ${review.customerName} · Rating: ${review.rating}/5
                    </p>
                    <p class="mb-2"><strong>Comment:</strong> ${review.comment}</p>

                    <#if review.providerResponse?has_content>
                        <div class="alert alert-secondary py-2 small mb-0">
                            <strong>Your reply:</strong> ${review.providerResponse}
                        </div>
                    <#else>
                        <form action="/providers/reviews/${review.id}/reply" method="post" class="mt-2">
                            <div class="mb-2">
                                <label class="form-label cm-form-label small">Reply to this customer</label>
                                <textarea class="form-control cm-form-control" name="response"
                                          rows="2" required></textarea>
                            </div>
                            <button class="btn btn-sm btn-primary" type="submit">Send reply</button>
                        </form>
                    </#if>
                </div>
            </#list>
        <#else>
            <p class="cm-muted">You don’t have any reviews yet.</p>
        </#if>
    </div>

</@layout.provider>