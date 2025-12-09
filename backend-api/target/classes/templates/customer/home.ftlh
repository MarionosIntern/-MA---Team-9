<#import "/layouts/base.ftlh" as layout>

<@layout.block pageTitle="Centrix Marketplace">

<#assign heroProduct = products?has_content?then(products[0], null)>

<#if loginSuccess?? || cartMessage?? || favoritesMessage??>
    <div class="container pt-4">
        <#if loginSuccess??>
            <div class="alert alert-success">${loginSuccess}</div>
        </#if>
        <#if cartMessage??>
            <div class="alert alert-success">${cartMessage}</div>
        </#if>
        <#if favoritesMessage??>
            <div class="alert alert-info">${favoritesMessage}</div>
        </#if>
    </div>
</#if>

<style>
    .home-hero {
        background: radial-gradient(circle at top left, #3b82f6, #111);
        color: #fff;
        border-radius: 1.25rem;
        padding: 3rem clamp(1.5rem, 5vw, 4rem);
        overflow: hidden;
        position: relative;
    }
    .home-hero::after {
        content: "";
        position: absolute;
        inset: 0;
        background: url("https://videos.openai.com/vg-assets/assets%2Ftask_01k5y1w9vcfrzrchbgr71bscjv%2F1758724096_img_0.webp") center/cover no-repeat;
        opacity: 0.15;
        pointer-events: none;
    }
    .home-hero-content {
        position: relative;
        z-index: 1;
    }
    .category-chip {
        border: 1px solid #e3e8ef;
        border-radius: 999px;
        padding: 0.4rem 1.25rem;
        font-size: 0.95rem;
        background: #fff;
        color: #111;
        transition: all 0.2s ease;
    }
    .category-chip:hover, .category-chip:focus {
        background: #111827;
        color: #fff;
    }
    .stat-card {
        border-radius: 1rem;
        padding: 1.5rem;
        background: #fff;
        box-shadow: 0 5px 30px rgba(15, 23, 42, 0.07);
    }
    .product-card {
        border-radius: 1.1rem;
        border: none;
        box-shadow: 0 12px 40px rgba(15, 23, 42, 0.12);
        transition: transform 0.2s ease, box-shadow 0.2s ease;
    }
    .product-card:hover {
        transform: translateY(-6px);
        box-shadow: 0 18px 40px rgba(15, 23, 42, 0.18);
    }
    .product-card img {
        border-radius: 0.95rem;
        background: #f7f8fb;
    }
    .product-price {
        font-size: 1.1rem;
        font-weight: 600;
        color: #111827;
    }
    .status-pill {
        font-size: 0.75rem;
        border-radius: 999px;
        padding: 0.15rem 0.6rem;
        background: #e0f2fe;
        color: #0369a1;
    }
</style>

<section class="py-5">
    <div class="container">
        <div class="home-hero">
            <div class="home-hero-content">
                <p class="text-uppercase small mb-2 opacity-75">Centrix Marketplace</p>
                <h1 class="display-5 fw-semibold">Bring premium tech, fashion, and lifestyle goods together in one cart.</h1>
                <p class="lead mb-4">
                    Browse ${products?size} curated products from vetted providers. 
                    Flexible fulfillment, protected checkout, and transparent reviews in one place.
                </p>
                <div class="d-flex flex-wrap gap-3">
                    <a class="btn btn-primary btn-lg px-4" href="/products">Browse catalog</a>
                    <a class="btn btn-outline-light btn-lg px-4" href="/reviews">See community reviews</a>
                    <#if currentCustomerName??>
                        <a class="btn btn-outline-light btn-lg px-4" href="/customers/profile/edit">Update profile</a>
                    </#if>
                </div>
                <#if heroProduct??>
                    <div class="mt-4 d-flex align-items-center gap-3">
                        <img src="${(heroProduct.imageUrl)!'/images/placeholder-product.png'}" alt="${heroProduct.name!'Featured product'}"
                             width="64" height="64" class="rounded-circle border border-2 border-light object-fit-cover">
                        <div>
                            <p class="mb-1 text-white-50 small">Featured drop</p>
                            <strong>${heroProduct.name!'New arrival'}</strong>
                            <p class="mb-0 text-white-50 small">${heroProduct.category!'Lifestyle'} · $${heroProduct.price!0}</p>
                        </div>
                    </div>
                </#if>
            </div>
        </div>
    </div>
</section>

<section class="pb-4">
    <div class="container">
        <div class="row g-3">
            <div class="col-12 col-md-4">
                <div class="stat-card">
                    <p class="text-uppercase text-muted small mb-1">Products live</p>
                    <h3 class="mb-2">${products?size}</h3>
                    <p class="mb-0 text-muted">Updated continuously as providers add new drops.</p>
                </div>
            </div>
            <div class="col-12 col-md-4">
                <div class="stat-card">
                    <p class="text-uppercase text-muted small mb-1">Avg. fulfillment time</p>
                    <h3 class="mb-2">2.4 days</h3>
                    <p class="mb-0 text-muted">Nationwide shipping with real-time status tracking.</p>
                </div>
            </div>
            <div class="col-12 col-md-4">
                <div class="stat-card">
                    <p class="text-uppercase text-muted small mb-1">Customer satisfaction</p>
                    <h3 class="mb-2">4.8 / 5</h3>
                    <p class="mb-0 text-muted">Based on verified post-checkout reviews.</p>
                </div>
            </div>
        </div>
    </div>
</section>

<section class="py-4 bg-white" id="categories">
    <div class="container">
        <div class="d-flex justify-content-between align-items-center mb-3">
            <h2 class="h4 mb-0">Shop by category</h2>
            <a href="/products" class="text-decoration-none">See all collections</a>
        </div>
        <div class="d-flex flex-wrap gap-2">
            <#list ["All","Clothes","Shoes","Technology","Accessories","Home","Outdoors","Beauty"] as category>
                <a class="category-chip text-decoration-none"
                   href="/products?category=${category?url}">${category}</a>
            </#list>
        </div>
    </div>
</section>

<section class="py-5 bg-light" id="products">
    <div class="container">
        <div class="d-flex justify-content-between align-items-center mb-4">
            <div>
                <p class="text-uppercase small text-muted mb-1">Popular right now</p>
                <h2 class="h4 mb-0">Handpicked for you</h2>
            </div>
            <small class="text-muted">Showing up to 6 featured products</small>
        </div>

        <#if products?has_content>
            <div class="row g-4">
                <#list products as p>
                    <#if p_index < 6>
                        <div class="col-12 col-sm-6 col-lg-4 col-xxl-3">
                            <article class="card product-card h-100 p-3">
                                <img src="${(p.imageUrl)!'/images/placeholder-product.png'}" alt="${p.name!'Product'}"
                                     class="img-fluid mb-3" style="height:170px; width:100%; object-fit:contain;">
                                <div class="d-flex justify-content-between align-items-center mb-1">
                                    <span class="badge text-bg-secondary">${p.category!'General'}</span>
                                    <span class="status-pill">${p.status!'In stock'}</span>
                                </div>
                                <h3 class="h6 mb-1">${p.name!'Untitled item'}</h3>
                                <p class="text-muted small mb-3">${(p.description!'No description yet.')?truncate(90, '...')}</p>
                                <div class="d-flex justify-content-between align-items-center flex-wrap gap-2">
                                    <span class="product-price">$${(p.price!0)?string["0.00"]}</span>
                                    <div class="d-flex gap-2">
                                        <form action="/cart/add/${p.productId!0}" method="post" class="d-inline-flex">
                                            <input type="hidden" name="quantity" value="1">
                                            <input type="hidden" name="redirect" value="/customer/home">
                                            <button type="submit" class="btn btn-sm btn-outline-primary">Add</button>
                                        </form>
                                        <form action="/favorites/toggle/${p.productId!0}" method="post" class="d-inline-flex">
                                            <input type="hidden" name="redirect" value="/customer/home">
                                            <button type="submit" class="btn btn-sm btn-outline-secondary" title="Toggle favorite">
                                                <i class="bi bi-heart"></i>
                                            </button>
                                        </form>
                                        <a href="/products/${p.productId!0}" class="btn btn-sm btn-link">View</a>
                                    </div>
                                </div>
                            </article>
                        </div>
                    </#if>
                </#list>
            </div>
        <#else>
            <div class="alert alert-secondary">
                No products are live yet. Check back soon or <a href="/providers/signup">invite a provider</a>.
            </div>
        </#if>
    </div>
</section>

<section class="py-5">
    <div class="container">
        <div class="row align-items-center g-4">
            <div class="col-12 col-md-7">
                <h2 class="h3">Need help curating your cart?</h2>
                <p class="text-muted mb-3">
                    Our concierge team can match you with recommended bundles, negotiate provider promos,
                    or configure enterprise subscriptions for repeat purchases.
                </p>
                <div class="d-flex gap-3 flex-wrap">
                    <a href="/contact" class="btn btn-primary px-4">Talk to concierge</a>
                    <a href="/subscriptions" class="btn btn-outline-secondary px-4">Explore subscriptions</a>
                </div>
            </div>
            <div class="col-12 col-md-5">
                <div class="stat-card h-100">
                    <h3 class="h5">Why Centrix?</h3>
                    <ul class="list-unstyled mb-0 small text-muted">
                        <li class="mb-2">• Unified cart + invoicing across providers</li>
                        <li class="mb-2">• Built-in review verification</li>
                        <li class="mb-2">• Provider analytics and SLA tracking</li>
                        <li>• Post-purchase support from real humans</li>
                    </ul>
                </div>
            </div>
        </div>
    </div>
</section>

</@layout.block>
