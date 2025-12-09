<#import "/provider/layout.ftlh" as layout>

<@layout.provider title="Provider Home - Centrix">

    <header class="mb-4">
        <div class="container">
            <div class="d-flex flex-column flex-lg-row align-items-lg-center justify-content-between gap-3">
                <div>
                    <p class="text-muted mb-1">Provider dashboard</p>
                    <h1 class="h4 mb-0">Welcome${provider?? ? then(", " + provider.name!"", "")}</h1>
                    <small class="text-muted">Manage your products and track performance.</small>
                </div>
                <div class="d-flex gap-2">
                    <a class="btn btn-primary" href="/providers/products/upload"><i class="bi bi-plus-lg me-1"></i>Upload product</a>
                    <a class="btn btn-outline-secondary" href="/providers/reviews"><i class="bi bi-chat-left-text me-1"></i>Reviews</a>
                </div>
            </div>
        </div>
    </header>

    <section class="py-2">
        <div class="container">
            <div class="d-flex align-items-center justify-content-between mb-3">
                <div>
                    <p class="text-muted small mb-1">Inventory</p>
                    <h2 class="h5 mb-0">Your products</h2>
                </div>
                <span class="badge text-bg-secondary">Total: ${products?size}</span>
            </div>

            <div class="row g-3">
                <#list products as product>
                    <div class="col-12 col-sm-6 col-md-4 col-lg-3">
                        <div class="card h-100 shadow-sm">
                            <#if product.imageUrl?? && product.imageUrl?length gt 0>
                                <img src="${product.imageUrl}" class="card-img-top" alt="${product.name}" style="height:180px;object-fit:contain;">
                            </#if>
                            <div class="card-body d-flex flex-column">
                                <h5 class="card-title mb-1">${product.name}</h5>
                                <p class="text-muted small mb-1">${product.category!'General'}</p>
                                <p class="card-text flex-grow-1 small mb-2">
                                    ${product.description?truncate(75, '...')}
                                </p>
                                <div class="d-flex justify-content-between align-items-center mt-1">
                                    <span class="fw-semibold">$${product.price?string["0.00"]}</span>
                                    <a class="btn btn-sm btn-outline-primary" href="/products/${product.productId!0}">View</a>
                                </div>
                            </div>
                        </div>
                    </div>
                </#list>
            </div>
        </div>
    </section>

    <section class="py-4">
        <div class="container">
            <div class="card shadow-sm">
                <div class="card-body d-flex flex-wrap gap-3 align-items-center justify-content-between">
                    <div>
                        <h3 class="h6 mb-1">Need help?</h3>
                        <p class="mb-0 text-muted">Questions about uploads, pricing, or reviews? Our team can assist.</p>
                    </div>
                    <a class="btn btn-outline-primary" href="/contact">Contact support</a>
                </div>
            </div>
        </div>
    </section>
    
</@layout.provider>
