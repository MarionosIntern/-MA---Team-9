<#import "/layouts/base.ftlh" as layout>

<@layout.block pageTitle="${product.name! 'Product details'}">

<div class="container py-5">
    <a href="/products" class="btn btn-link mb-3">
        <i class="bi bi-arrow-left"></i> Back to products
    </a>

    <div class="row g-4">
        <div class="col-12 col-lg-6">
            <div class="card shadow-sm h-100 text-center p-4">
                <img src="${(product.imageUrl)!'/images/placeholder-product.png'}"
                     alt="${product.name!'Product image'}"
                     class="img-fluid rounded"
                     style="max-height:320px; object-fit:contain;">
                <p class="text-muted small mt-3 mb-0">
                    Image source: ${(product.imageUrl)!'placeholder'}
                </p>
            </div>
        </div>
        <div class="col-12 col-lg-6">
            <div class="card shadow-sm h-100">
                <div class="card-body">
                    <p class="text-uppercase text-muted small mb-1">Product</p>
                    <h1 class="h4">${product.name!'Untitled item'}</h1>
                    <p class="text-muted">${product.category!'General'}
                        <span class="badge text-bg-secondary ms-2">${product.status!'Active'}</span>
                    </p>
                    <p class="lead fw-semibold mb-4">$${(product.price!0)?string["0.00"]}</p>
                    <p>${product.description!'This product does not have a description yet.'}</p>

                    <#if providerLoggedIn?? && providerLoggedIn>
                        <hr>
                        <p class="text-uppercase text-muted small mb-3">Actions</p>
                        <div class="d-flex flex-wrap gap-2 mb-3">
                            <a href="/products/${product.productId!0}/edit" class="btn btn-primary">Edit</a>
                            <a href="/products/${product.productId!0}/delete"
                               class="btn btn-outline-danger"
                               onclick="return confirm('Delete this product?');">Delete</a>
                        </div>
                    </#if>

                    <dl class="row mb-0">
                        <dt class="col-sm-4">ID</dt>
                        <dd class="col-sm-8">${product.productId!'—'}</dd>
                        <dt class="col-sm-4">Category</dt>
                        <dd class="col-sm-8">${product.category!'General'}</dd>
                        <dt class="col-sm-4">Status</dt>
                        <dd class="col-sm-8">${product.status!'Active'}</dd>
                        <dt class="col-sm-4">Price</dt>
                        <dd class="col-sm-8">$${(product.price!0)?string["0.00"]}</dd>
                    </dl>
                </div>
            </div>
        </div>
    </div>
</div>

</@layout.block>
