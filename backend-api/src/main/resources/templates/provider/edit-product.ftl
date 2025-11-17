<#import "/layout.ftl" as layout>

<@layout.provider title="Edit Product – Centrix Marketplace">

    <div class="container py-3">
        <h1 class="h5 mb-3">Edit product</h1>
        <p class="cm-muted mb-3">
            Update details for <strong>${product.name}</strong>. Changes to published products
            will be reflected on the Centrix homepage.
        </p>

        <div class="card bg-white">
            <div class="card-body">
                <form action="/providers/products/${product.id}/edit" method="post">
                    <div class="mb-3">
                        <label class="form-label cm-form-label">Product name</label>
                        <input class="form-control cm-form-control"
                               type="text" name="name"
                               value="${product.name}" required>
                    </div>

                    <div class="mb-3">
                        <label class="form-label cm-form-label">Category</label>
                        <input class="form-control cm-form-control"
                               type="text" name="category"
                               value="${product.category}" required>
                    </div>

                    <div class="mb-3">
                        <label class="form-label cm-form-label">Price</label>
                        <input class="form-control cm-form-control"
                               type="number" step="0.01" name="price"
                               value="${product.price}" required>
                    </div>

                    <div class="mb-3">
                        <label class="form-label cm-form-label">Description</label>
                        <textarea class="form-control cm-form-control"
                                  name="description" rows="3" required>
${product.description}</textarea>
                    </div>

                    <div class="mb-3">
                        <label class="form-label cm-form-label">Status</label>
                        <select class="form-select cm-form-control" name="status">
                            <option value="PUBLISHED"
                                <#if product.status?upper_case == "PUBLISHED">selected</#if>>
                                Published
                            </option>
                            <option value="DRAFT"
                                <#if product.status?upper_case == "DRAFT">selected</#if>>
                                Draft
                            </option>
                            <option value="INACTIVE"
                                <#if product.status?upper_case == "INACTIVE">selected</#if>>
                                Inactive
                            </option>
                        </select>
                    </div>

                    <button class="btn btn-primary" type="submit">Save changes</button>
                    <a href="/providers/home" class="btn btn-outline-secondary ms-2">Cancel</a>
                </form>
            </div>
        </div>
    </div>

</@layout.provider>