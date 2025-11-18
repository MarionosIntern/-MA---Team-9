<!doctype html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>${title! "Your Reviews"}</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.5/font/bootstrap-icons.css">
</head>
<body class="bg-light">
<header class="bg-dark text-white py-3 mb-4">
    <div class="container d-flex justify-content-between align-items-center">
        <div>
            <h1 class="h4 mb-0">Centrix Marketplace</h1>
            <small class="text-muted text-white-50">Reviews & Feedback</small>
        </div>
        <div class="text-end">
            <#if customer??>
                <p class="mb-0">Signed in as <strong>${customer.name! "Valued Customer"}</strong></p>
                <a href="/customers/${customer.id}" class="link-light text-decoration-none">View profile</a>
            <#else>
                <a href="/signin" class="btn btn-outline-light btn-sm">Sign in</a>
            </#if>
        </div>
    </div>
</header>

<main class="container mb-5">
    <div class="d-flex justify-content-between align-items-center mb-4">
        <div>
            <h2 class="h3 mb-1">${title! "Your Reviews"}</h2>
            <p class="text-muted mb-0">Keep track of your feedback and see how providers respond.</p>
        </div>
        <a href="/api/reviews/create" class="btn btn-primary">
            <i class="bi bi-plus-circle me-2"></i>Write a Review
        </a>
    </div>

    <#if reviews?has_content>
        <div class="row g-4">
            <#list reviews as review>
                <div class="col-12 col-md-6">
                    <article class="card shadow-sm h-100">
                        <div class="card-body">
                            <div class="d-flex justify-content-between align-items-start mb-2">
                                <h3 class="h5 mb-0">
                                    <#if review.product??>
                                        ${review.product.name!"Product"}
                                    <#else>
                                        Product
                                    </#if>
                                </h3>
                                <span class="badge bg-info text-dark">
                                    Overall:
                                    <#if review.overallRating??>
                                        ${review.overallRating?string("0.0")}
                                    <#else>
                                        N/A
                                    </#if>
                                </span>
                            </div>
                            <p class="text-muted small mb-3">
                                Quality
                                <#if review.qualityRating??>
                                    ${review.qualityRating?string("0.0")}
                                <#else>
                                    N/A
                                </#if>
                                · Delivery
                                <#if review.deliveryRating??>
                                    ${review.deliveryRating?string("0.0")}
                                <#else>
                                    N/A
                                </#if>
                            </p>
                            <p class="mb-3">${review.comment!"No comment provided."}</p>

                            <#if review.providerResponse?? && (review.providerResponse?has_content)>
                                <div class="border rounded p-3 bg-light">
                                    <strong>Provider response</strong>
                                    <p class="mb-1">${review.providerResponse}</p>
                                    <small class="text-muted">
                                        <#if review.providerResponseDate??>
                                            ${review.providerResponseDate?string("MMMM d, yyyy HH:mm")}
                                        <#else>
                                            Recently
                                        </#if>
                                    </small>
                                </div>
                            </#if>
                        </div>
                        <div class="card-footer d-flex justify-content-between align-items-center bg-white">
                            <small class="text-muted">
                                Created
                                <#if review.createdAt??>
                                    ${review.createdAt?string("MMMM d, yyyy")}
                                <#else>
                                    Unknown date
                                </#if>
                            </small>
                            <div class="d-flex gap-2">
                                <a href="/api/reviews/${review.id}" class="btn btn-sm btn-outline-secondary">Details</a>
                                <a href="/api/reviews/delete/${review.id}" class="btn btn-sm btn-outline-danger">Delete</a>
                            </div>
                        </div>
                    </article>
                </div>
            </#list>
        </div>
    <#else>
        <div class="text-center py-5 bg-white border rounded">
            <p class="lead mb-3">You haven’t written any reviews yet.</p>
            <a href="/api/reviews/create" class="btn btn-primary">Write your first review</a>
        </div>
    </#if>
</main>

<footer class="bg-dark text-white py-3">
    <div class="container d-flex justify-content-between">
        <small>&copy; ${.now?string("yyyy")} Centrix Marketplace</small>
        <div>
            <a href="/privacy" class="link-light me-3 text-decoration-none">Privacy</a>
            <a href="/terms" class="link-light text-decoration-none">Terms</a>
        </div>
    </div>
</footer>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
