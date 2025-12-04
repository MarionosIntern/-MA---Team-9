<#import "/layouts/base.ftlh" as layout>

<@layout.block pageTitle="Sign up">

<div class="container py-5" style="max-width:640px;">
    <h1 class="h3 mb-4 text-center">Create your Centrix account</h1>

    <#if signupError??>
        <div class="alert alert-danger">${signupError}</div>
    </#if>

    <div class="card shadow-sm">
        <div class="card-body">
            <form action="/signup" method="post" class="row g-3">
                <div class="col-12">
                    <label class="form-label">Full name</label>
                    <input type="text" class="form-control" name="name" required>
                </div>
                <div class="col-md-6">
                    <label class="form-label">Email</label>
                    <input type="email" class="form-control" name="email" required>
                </div>
                <div class="col-md-6">
                    <label class="form-label">Password</label>
                    <input type="password" class="form-control" name="password" required>
                </div>
                <div class="col-md-6">
                    <label class="form-label">Phone number</label>
                    <input type="text" class="form-control" name="phoneNumber">
                </div>
                <div class="col-md-6">
                    <label class="form-label">Shipping address</label>
                    <input type="text" class="form-control" name="shippingAddress">
                </div>
                <div class="col-12 d-flex justify-content-end gap-2">
                    <a href="/" class="btn btn-outline-secondary">Cancel</a>
                    <button class="btn btn-primary" type="submit">Create account</button>
                </div>
            </form>
            <p class="mt-3 mb-0 text-center text-muted">
                Already have an account? <a href="/signin">Sign in</a>
            </p>
        </div>
    </div>
</div>

</@layout.block>
