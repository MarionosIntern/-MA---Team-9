<#import "layout.ftl" as layout>

<@layout.site title="Centrix Marketplace" showSearch=true>

    <header class="cm-hero">
        <div class="container">
            <div class="cm-hero-title">Featured deals</div>
            <p class="cm-hero-text mb-0">
                Shop popular products from providers on Centrix Marketplace.
            </p>
        </div>
    </header>

    <div class="cm-category-row">
        <div class="container">
            <button class="cm-cat-btn active">All</button>
            <button class="cm-cat-btn">Clothes</button>
            <button class="cm-cat-btn">Shoes</button>
            <button class="cm-cat-btn">Technology</button>
            <button class="cm-cat-btn">Accessories</button>
        </div>
    </div>

    <section class="cm-products-section">
        <div class="container">
            <div class="cm-section-header">
                <h2>Popular products</h2>
                <p>Showing ${products?size} of ${products?size} results</p>
            </div>

            <div class="row g-3">
                <#list products as product>
                    <div class="col-12 col-sm-6 col-md-4 col-lg-3">
                        <div class="card h-100 cm-product-card">
                            <#if product.imageUrl?? && product.imageUrl?length gt 0>
                                <img src="${product.imageUrl}" class="card-img-top" alt="${product.name}">
                            </#if>
                            <div class="card-body d-flex flex-column">
                                <h5 class="card-title mb-1">${product.name}</h5>
                                <p class="cm-product-meta mb-1">${product.category}</p>
                                <p class="card-text flex-grow-1 mb-1">
                                    ${product.description?truncate(75, '...')}
                                </p>
                                <div class="d-flex justify-content-between align-items-center mt-1">
                                    <span class="cm-product-price">$${product.price?string["0.00"]}</span>
                                    <button type="button" class="btn btn-sm btn-primary">Add</button>
                                </div>
                            </div>
                        </div>
                    </div>
                </#list>
            </div>
        </div>
    </section>

    <section class="cm-contact">
        <div class="container">
            <h3 class="h6 mb-1">Contact Us</h3>
            <p class="mb-0">
                Questions or ready to get started? Reach out and we’ll respond as fast as we can.
            </p>
        </div>
    </section>
    
</@layout.site>