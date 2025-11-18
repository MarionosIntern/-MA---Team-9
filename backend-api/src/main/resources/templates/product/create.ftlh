<#import "/layouts/base.ftlh" as layout>

<@layout.block pageTitle="Create Product">

<div class="container py-5">
    <a href="/products" class="btn btn-link mb-3">
        <i class="bi bi-arrow-left"></i> Back to products
    </a>

    <div class="card shadow-sm">
        <div class="card-header">
            <h1 class="h5 mb-0">Create product</h1>
        </div>
        <div class="card-body">
            <form action="/products/create" method="post" class="row g-3">
                <div class="col-md-6">
                    <label class="form-label">Name</label>
                    <input class="form-control" type="text" name="name" value="${product.name!}" required>
                </div>
                <div class="col-md-6">
                    <label class="form-label">Category</label>
                    <input class="form-control" type="text" name="category" value="${product.category!}">
                </div>
                <div class="col-md-6">
                    <label class="form-label">Image URL</label>
                    <input class="form-control" type="url" name="imageUrl" value="${product.imageUrl!}">
                    <small class="text-muted">Paste an https:// link to the product photo.</small>
                </div>
                <div class="col-md-3">
                    <label class="form-label">Price</label>
                    <input class="form-control" type="number" step="0.01" min="0" name="price" value="${product.price!0}">
                </div>
                <div class="col-md-3">
                    <label class="form-label">Status</label>
                    <input class="form-control" type="text" name="status" value="${product.status!'Active'}">
                </div>
                <div class="col-md-3">
                    <label class="form-label">Provider ID</label>
                    <input class="form-control" type="number" min="1" name="providerId" value="${product.providerId!}">
                </div>
                <div class="col-12">
                    <label class="form-label">Description</label>
                    <textarea class="form-control" name="description" rows="4">${product.description!}</textarea>
                </div>
                <div class="col-12 d-flex justify-content-end gap-2">
                    <a href="/products" class="btn btn-outline-secondary">Cancel</a>
                    <button class="btn btn-primary" type="submit">Create</button>
                </div>
            </form>
        </div>
    </div>
</div>

</@layout.block>
