<#import "/layout.ftl" as layout>

<@layout.provider title="Provider Dashboard – Centrix Marketplace">

    <div class="container py-3">
        <h1 class="h5 mb-1">Welcome, ${provider.name}!</h1>
        <p class="cm-muted mb-3">
            Manage your listings and respond to customer reviews on Centrix Marketplace.
        </p>

        <div class="d-flex justify-content-between align-items-center mb-2">
            <h2 class="h6 mb-0">Your products</h2>
            <a href="/providers/products/upload" class="btn btn-sm btn-primary">+ Upload product</a>
        </div>

        <#if products?? && products?size gt 0>
            <div class="table-responsive">
                <table class="table table-sm align-middle bg-white">
                    <thead>
                    <tr>
                        <th>Name</th>
                        <th>Category</th>
                        <th>Price</th>
                        <th>Status</th>
                        <th></th>
                    </tr>
                    </thead>
                    <tbody>
                    <#list products as product>
                        <tr>
                            <td>${product.name}</td>
                            <td>${product.category}</td>
                            <td>$${product.price?string["0.00"]}</td>
                            <td><span class="badge bg-secondary">${product.status}</span></td>
                            <td class="text-end">
                                <a href="/providers/products/${product.productId}/edit"
                                   class="btn btn-sm btn-outline-primary">Edit</a>
                            </td>
                        </tr>
                    </#list>
                    </tbody>
                </table>
            </div>
        <#else>
            <p class="cm-muted mt-2">
                You don't have any products yet. <a href="/providers/products/upload">Upload your first product</a>.
            </p>
        </#if>
    </div>

</@layout.provider>