<#import "/layouts/base.ftlh" as layout>

<@layout.block pageTitle="Favorites">

<div class="container py-5">
    <h1 class="mb-4">Saved Favorites</h1>

    <#if favoritesMessage??>
        <div class="alert alert-success">${favoritesMessage}</div>
    </#if>

    <#if favorites?? && favorites?size gt 0>
        <div class="row g-4">
            <#list favorites as product>
                <div class="col-12 col-md-6 col-lg-4">
                    <div class="card h-100">
                        <img src="${(product.imageUrl)!'/images/placeholder-product.png'}"
                             class="card-img-top"
                             alt="${product.name!'Product'}"
                             style="height:220px; object-fit:contain;">
                        <div class="card-body d-flex flex-column">
                            <h5 class="card-title">${product.name!'Untitled item'}</h5>
                            <p class="card-text text-muted">${(product.description!'No description yet.')?truncate(100, '...')}</p>
                            <div class="mt-auto d-flex justify-content-between align-items-center">
                                <span class="fw-semibold">$${(product.price!0)?string["0.00"]}</span>
                                <div class="d-flex gap-2">
                                    <form action="/cart/add/${product.productId!0}" method="post">
                                        <input type="hidden" name="redirect" value="/favorites">
                                        <button class="btn btn-sm btn-outline-primary">Add to cart</button>
                                    </form>
                                    <form action="/favorites/toggle/${product.productId!0}" method="post">
                                        <input type="hidden" name="redirect" value="/favorites">
                                        <button class="btn btn-sm btn-outline-danger">Remove</button>
                                    </form>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </#list>
        </div>
        <div class="mt-4 text-end">
            <form action="/favorites/clear" method="post">
                <input type="hidden" name="redirect" value="/favorites">
                <button class="btn btn-outline-secondary btn-sm">Clear all</button>
            </form>
        </div>
    <#else>
        <div class="alert alert-info">
            You have no favorites yet. Explore the <a href="/customer/home">marketplace</a> to add items.
        </div>
    </#if>
</div>

</@layout.block>
