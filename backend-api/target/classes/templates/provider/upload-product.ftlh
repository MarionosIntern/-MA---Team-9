<#import "/provider/layout.ftlh" as layout>

<@layout.provider title="Upload Product - Centrix">

    <div class="container py-4">
        <div class="d-flex flex-column flex-lg-row justify-content-between align-items-lg-center mb-3 gap-2">
            <div>
                <p class="text-muted small mb-1">Add a new item</p>
                <h1 class="h5 mb-0">Upload product</h1>
            </div>
            <a href="/providers/home" class="btn btn-outline-secondary btn-sm">
                <i class="bi bi-arrow-left me-1"></i>Back to dashboard
            </a>
        </div>

        <div class="card shadow-sm">
            <div class="card-body">
                <form action="/providers/products/upload" method="post" class="row g-3">
                    <div class="col-12">
                        <label class="form-label">Product name</label>
                        <input class="form-control" type="text" name="name" required>
                    </div>

                    <div class="col-md-6">
                        <label class="form-label">Category</label>
                        <input class="form-control" type="text" name="category" required>
                    </div>

                    <div class="col-md-6">
                        <label class="form-label">Price</label>
                        <input class="form-control" type="number" step="0.01" min="0" name="price" required>
                    </div>

                    <div class="col-12">
                        <label class="form-label">Image URL (optional)</label>
                        <input class="form-control" type="url" name="imageUrl" placeholder="https://...">
                    </div>

                    <div class="col-12">
                        <label class="form-label">Description</label>
                        <textarea class="form-control" name="description" rows="3" required></textarea>
                    </div>

                    <div class="col-md-6">
                        <label class="form-label">Status</label>
                        <input class="form-control" type="text" name="status" placeholder="e.g. Active, In stock" required>
                    </div>

                    <div class="col-12 d-flex justify-content-end gap-2">
                        <a href="/providers/home" class="btn btn-outline-secondary">Cancel</a>
                        <button class="btn btn-primary" type="submit">Publish product</button>
                    </div>
                </form>
            </div>
        </div>
    </div>

</@layout.provider>
