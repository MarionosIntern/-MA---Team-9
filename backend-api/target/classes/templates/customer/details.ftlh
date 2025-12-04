<#import "/layouts/base.ftlh" as layout>
<@layout.block pageTitle="Customer Details">

<div class="container py-5">
    <a href="/customers" class="btn btn-link mb-3">
        <i class="bi bi-arrow-left"></i> Back to Customers
    </a>

    <div class="card shadow-sm">
        <div class="card-header d-flex justify-content-between align-items-center">
            <div>
                <p class="text-uppercase text-muted mb-1">Customer</p>
                <h2 class="h4 mb-0">${customer.name!"Unnamed"}</h2>
            </div>
            <div class="d-flex gap-2">
                <a href="/customers/update/${customer.id}" class="btn btn-outline-secondary">Edit</a>
                <a href="/customers/delete/${customer.id}" class="btn btn-outline-danger"
                   onclick="return confirm('Delete this customer?');">Delete</a>
            </div>
        </div>
        <div class="card-body">
            <dl class="row mb-0">
                <dt class="col-sm-3">ID</dt>
                <dd class="col-sm-9">${customer.id}</dd>

                <dt class="col-sm-3">Email</dt>
                <dd class="col-sm-9">${customer.email!"-"}</dd>

                <dt class="col-sm-3">Phone</dt>
                <dd class="col-sm-9">${customer.phoneNumber!"-"}</dd>

                <dt class="col-sm-3">Shipping Address</dt>
                <dd class="col-sm-9">${customer.shippingAddress!"-"}</dd>

                <dt class="col-sm-3">Profile Picture</dt>
                <dd class="col-sm-9">${customer.profilePicturePath!"-"}</dd>
            </dl>
        </div>
    </div>
</div>

</@layout.block>
