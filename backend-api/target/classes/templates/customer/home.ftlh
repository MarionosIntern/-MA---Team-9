<#import "layouts/base.fthl" as layout>

<@layout.block pageTitle="Centrix - Home">

<section class="market-hero py-4">
    <div class="container">
        <div class="d-flex align-items-center justify-content-between">
            <h2 class="mb-0">Featured deals</h2>
            <a href="#categories" class="btn btn-link">Browse categories</a>
        </div>
        <div class="mt-3">
            <img src="https://videos.openai.com/vg-assets/assets%2Ftask_01k5y1w9vcfrzrchbgr71bscjv%2F1758724096_img_0.webp"
                 alt="Featured banner" class="img-fluid rounded" style="width:200%; height:auto; transform: scale(0.9)">
        </div>
    </div>
</section>

<section id="categories" class="py-4 bg-white">
    <div class="container">
        <div class="d-flex gap-3 overflow-auto pb-2">
            <button class="btn btn-outline-secondary">All</button>
            <button class="btn btn-outline-secondary">Clothes</button>
            <button class="btn btn-outline-secondary">Shoes</button>
            <button class="btn btn-outline-secondary">Technology</button>
            <button class="btn btn-outline-secondary">Accessories</button>
        </div>
    </div>
</section>

<section id="products" class="py-5 bg-light">
    <div class="container">
        <div class="d-flex justify-content-between align-items-center mb-3">
            <h2 class="mb-0">Popular products</h2>
            <small class="text-muted">Showing 6 of 120 results</small>
        </div>

        <div class="row g-4">
            <#-- You can iterate over a product list passed from the controller -->
            <#list products as p>
                <div class="col-6 col-md-3">
                    <article class="card h-100 p-3">
                        <img src="${p.imageUrl}" alt="${p.name}" class="img-fluid mb-3"
                             style="height:140px; object-fit:contain;">
                        <h3 class="h6">${p.name}</h3>
                        <p class="text-muted mb-1">${p.description}</p>
                        <p class="text-muted mb-1">${p.shipping}</p>
                        <div class="d-flex justify-content-between align-items-center">
                            <strong>$${p.price}</strong>
                            <a href="/cart/add/${p.id}" class="btn btn-sm btn-outline-primary">Add</a>
                            <a href="/products/${p.id}" class="btn btn-sm btn-link">View</a>
                        </div>
                    </article>
                </div>
            </#list>
        </div>
    </div>
</section>

<section id="contact" class="py-5">
    <div class="container">
        <h2>Contact Us</h2>
        <p class="mb-3">Questions or ready to get started? Reach out and we'll respond within 1 business day.</p>
        <a href="/contact" class="btn btn-primary">Contact Centrix</a>
    </div>
</section>

</@layout.block>
