<#import "layouts/base.fthl" as layout>
<@layout.block pageTitle="Create Customer">

<div class="container py-5">
    <a href="/customers" class="btn btn-link mb-3">
        <i class="bi bi-arrow-left"></i> Back to Customers
    </a>

    <div class="card shadow-sm">
        <div class="card-header">
            <h2 class="h4 mb-0">Create Customer</h2>
        </div>
        <div class="card-body">
            <form action="/customers" method="post" class="row g-3">
                <div class="col-md-6">
                    <label class="form-label" for="name">Full Name</label>
                    <input type="text" id="name" name="name" value="${customer.name!}" class="form-control" required>
                </div>
                <div class="col-md-6">
                    <label class="form-label" for="email">Email</label>
                    <input type="email" id="email" name="email" value="${customer.email!}" class="form-control" required>
                </div>
                <div class="col-md-6">
                    <label class="form-label" for="password">Password</label>
                    <input type="password" id="password" name="password" value="${customer.password!}" class="form-control" required>
                </div>
                <div class="col-md-6">
                    <label class="form-label" for="phoneNumber">Phone Number</label>
                    <input type="text" id="phoneNumber" name="phoneNumber" value="${customer.phoneNumber!}" class="form-control">
                </div>
                <div class="col-12">
                    <label class="form-label" for="shippingAddress">Shipping Address</label>
                    <input type="text" id="shippingAddress" name="shippingAddress" value="${customer.shippingAddress!}" class="form-control">
                </div>
                <div class="col-12">
                    <label class="form-label" for="profilePicturePath">Profile Picture URL</label>
                    <input type="text" id="profilePicturePath" name="profilePicturePath" value="${customer.profilePicturePath!}" class="form-control">
                </div>
                <div class="col-12 d-flex justify-content-end gap-2">
                    <a href="/customers" class="btn btn-outline-secondary">Cancel</a>
                    <button type="submit" class="btn btn-primary">Save</button>
                </div>
            </form>
        </div>
    </div>
</div>

</@layout.block>
