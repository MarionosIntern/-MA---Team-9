<#import "/provider/layout.ftlh" as layout>

<@layout.provider title="Provider Reviews - Centrix">

    <section class="py-4">
        <div class="container">
            <div class="d-flex flex-column flex-lg-row justify-content-between align-items-lg-center mb-3 gap-2">
                <div>
                    <p class="text-muted small mb-1">Feedback</p>
                    <h1 class="h5 mb-0">Customer reviews</h1>
                </div>
                <a class="btn btn-outline-primary btn-sm" href="/providers/home">
                    <i class="bi bi-arrow-left me-1"></i>Back to dashboard
                </a>
            </div>

            <#if reviews?? && reviews?size gt 0>
                <div class="vstack gap-3">
                    <#list reviews as review>
                        <article class="card shadow-sm">
                            <div class="card-body">
                                <div class="d-flex justify-content-between align-items-start mb-2">
                                    <div>
                                        <h2 class="h6 mb-1">${review.product.name!'Product'}</h2>
                                        <p class="text-muted small mb-0">
                                            From: ${review.customerName!'Customer'} • Rating: ${review.rating!review.overallRating!0}/5
                                        </p>
                                    </div>
                                    <span class="badge text-bg-secondary">${review.createdAt!''}</span>
                                </div>

                                <p class="mb-3"><strong>Comment:</strong> ${review.comment!''}</p>

                                <#if review.providerResponse?has_content>
                                    <div class="alert alert-secondary py-2 small mb-0">
                                        <strong>Your reply:</strong> ${review.providerResponse}
                                    </div>
                                <#else>
                                    <form action="/providers/reviews/${review.id}/reply" method="post" class="mt-2">
                                        <div class="mb-2">
                                            <label class="form-label small mb-1">Reply to this customer</label>
                                            <textarea class="form-control" name="response" rows="2" required></textarea>
                                        </div>
                                        <button class="btn btn-sm btn-primary" type="submit">Send reply</button>
                                    </form>
                                </#if>
                            </div>
                        </article>
                    </#list>
                </div>
            <#else>
                <div class="alert alert-info mb-0">
                    You don’t have any reviews yet.
                </div>
            </#if>
        </div>
    </section>

</@layout.provider>
