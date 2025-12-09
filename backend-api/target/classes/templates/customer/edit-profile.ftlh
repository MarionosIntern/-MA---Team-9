<#import "/layouts/base.ftlh" as layout>

<@layout.block pageTitle="Edit Profile">

<div class="container py-5" style="max-width:640px;">
    <h1 class="h4 mb-3">Edit your profile</h1>
    <p class="text-muted mb-3">Update your account details. Enter your current password to save changes.</p>

    <#if error??>
        <div class="alert alert-danger py-2">${error}</div>
    </#if>

    <div class="card shadow-sm">
        <div class="card-body">
            <form action="/customers/profile/edit" method="post" class="row g-3">
                <div class="col-12">
                    <label class="form-label">Full name</label>
                    <input class="form-control" type="text" name="name" value="${customer.name!''}" required>
                </div>
                <div class="col-md-6">
                    <label class="form-label">Email</label>
                    <input class="form-control" type="email" name="email" value="${customer.email!''}" required>
                </div>
                <div class="col-md-6">
                    <label class="form-label">Phone</label>
                    <input class="form-control" type="text" name="phoneNumber" value="${customer.phoneNumber!''}">
                </div>
                <div class="col-12">
                    <label class="form-label">Shipping address</label>
                    <input class="form-control" type="text" name="shippingAddress" value="${customer.shippingAddress!''}">
                </div>

                <hr class="mt-2 mb-1">
                <div class="col-12">
                    <label class="form-label">Current password</label>
                    <input class="form-control" type="password" name="currentPassword" required>
                </div>
                <div class="col-12">
                    <label class="form-label">New password (optional)</label>
                    <input class="form-control" type="password" name="newPassword">
                </div>

                <div class="col-12 d-flex justify-content-end gap-2">
                    <a href="/customers/profile" class="btn btn-outline-secondary">Cancel</a>
                    <button class="btn btn-primary" type="submit">Save changes</button>
                </div>
            </form>
        </div>
    </div>
</div>

</@layout.block>
