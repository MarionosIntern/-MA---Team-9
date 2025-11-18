<#import "/layout.ftl" as layout>

<@layout.provider title="Upload Product – Centrix Marketplace">

    <div class="container py-3">
        <h1 class="h5 mb-3">Upload a new product</h1>
        <p class="cm-muted">
            Published products will appear on the Centrix Marketplace homepage.
        </p>

        <div class="card bg-white">
            <div class="card-body">
                <form action="/providers/products/upload" method="post">
                    <div class="mb-3">
                        <label class="form-label cm-form-label">Product name</label>
                        <input class="form-control cm-form-control" type="text" name="name" required>
                    </div>

                    <div class="mb-3">
                        <label class="form-label cm-form-label">Category</label>
                        <input class="form-control cm-form-control" type="text" name="category" required>
                    </div>

                    <div class="mb-3">
                        <label class="form-label cm-form-label">Price</label>
                        <input class="form-control cm-form-control" type="number" step="0.01"
                               name="price" required>
                    </div>

                    <div class="mb-3">
                        <label class="form-label cm-form-label">Description</label>
                        <textarea class="form-control cm-form-control" name="description"
                                  rows="3" required></textarea>
                    </div>

                     <div class="mb-3">
                        <label class="form-label cm-form-label">Status i.e Describe the condition of the product</label>
                        <input class="form-control cm-form-control" type="text" name="status" required>
                    </div>

                    <button class="btn btn-primary" type="submit">Publish product</button>
                    <a href="/providers/home" class="btn btn-outline-secondary ms-2">Cancel</a>
                </form>
            </div>
        </div>
    </div>

</@layout.provider>