<#import "/layouts/base.ftlh" as layout>

<@layout.block pageTitle="Products">

<div class="container py-5">
    <div class="d-flex flex-wrap justify-content-between align-items-center mb-4 gap-3">
        <div>
            <p class="text-uppercase small text-muted mb-1">Inventory</p>
            <h1 class="h4 mb-0">Products (${products?size})</h1>
        </div>
        <a href="/products/create" class="btn btn-primary">
            <i class="bi bi-plus-lg me-1"></i> Add product
        </a>
    </div>

    <div class="card shadow-sm">
        <div class="card-body p-0">
            <table class="table table-hover align-middle mb-0">
                <thead class="table-light">
                <tr>
                    <th scope="col">Preview</th>
                    <th scope="col">Name</th>
                    <th scope="col">Category</th>
                    <th scope="col">Price</th>
                    <th scope="col">Status</th>
                    <th scope="col" class="text-end">Actions</th>
                </tr>
                </thead>
                <tbody>
                <#if products?has_content>
                    <#list products as product>
                        <tr>
                            <td style="width:80px;">
                                <img src="${(product.imageUrl)!'/images/placeholder-product.png'}"
                                     alt="${product.name!'Product'}"
                                     class="img-fluid rounded"
                                     style="max-height:60px; object-fit:contain;">
                            </td>
                            <td>
                                <strong>${product.name!'Untitled item'}</strong>
                                <div class="text-muted small">${(product.description!'No description yet.')?truncate(60, '...')}</div>
                            </td>
                            <td>
                                <span class="badge text-bg-secondary">${product.category!'General'}</span>
                            </td>
                            <td>$${(product.price!0)?string["0.00"]}</td>
                            <td>
                                <span class="badge ${product.status?lower_case == 'inactive'?then('text-bg-secondary','text-bg-success')}">
                                    ${product.status!'Active'}
                                </span>
                            </td>
                            <td class="text-end">
                                <div class="btn-group btn-group-sm">
                                    <a href="/products/${product.productId!0}" class="btn btn-outline-primary">View</a>
                                    <a href="/products/${product.productId!0}/edit" class="btn btn-outline-secondary">Edit</a>
                                    <a href="/products/${product.productId!0}/delete" class="btn btn-outline-danger"
                                       onclick="return confirm('Delete this product?');">Delete</a>
                                </div>
                            </td>
                        </tr>
                    </#list>
                <#else>
                    <tr>
                        <td colspan="5" class="text-center py-4 text-muted">
                            No products found. <a href="/products/create">Create the first product</a>.
                        </td>
                    </tr>
                </#if>
                </tbody>
            </table>
        </div>
    </div>
</div>

</@layout.block>
