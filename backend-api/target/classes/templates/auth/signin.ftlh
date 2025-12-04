<#import "/layouts/base.ftlh" as layout>

<@layout.block pageTitle="Sign in">

<div class="container py-5" style="max-width:480px;">
    <h1 class="h3 mb-4 text-center">Sign in to Centrix</h1>

    <#if loginError??>
        <div class="alert alert-danger">${loginError}</div>
    </#if>
    <#if loginSuccess??>
        <div class="alert alert-success">${loginSuccess}</div>
    </#if>

    <div class="card shadow-sm">
        <div class="card-body">
            <form action="/signin" method="post" class="d-grid gap-3">
                <div>
                    <label class="form-label">Email</label>
                    <input type="email" class="form-control" name="email" required>
                </div>
                <div>
                    <label class="form-label">Password</label>
                    <input type="password" class="form-control" name="password" required>
                </div>
                <button class="btn btn-primary" type="submit">Sign in</button>
            </form>
            <p class="mt-3 mb-0 text-center text-muted">
                Need an account? <a href="/signup">Sign up</a>
            </p>
        </div>
    </div>
</div>

</@layout.block>
