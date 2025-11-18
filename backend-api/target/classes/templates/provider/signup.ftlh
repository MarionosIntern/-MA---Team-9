<#import "/provider/layout.ftlh" as layout>

<@layout.site title="Provider Signup - Centrix Marketplace" showSearch=false>

    <div class="container">
        <div class="card shadow-sm cm-card-narrow">
            <div class="card-body">
                <h1 class="h5 mb-3 text-center">Become a Provider</h1>

                <form action="/providers/signup" method="post">
                    <div class="mb-3">
                        <label class="form-label cm-form-label">Store / Provider Name</label>
                        <input class="form-control cm-form-control" type="text" name="name" required>
                    </div>

                    <div class="mb-3">
                        <label class="form-label cm-form-label">Email</label>
                        <input class="form-control cm-form-control" type="email" name="email" required>
                    </div>

                    <div class="mb-3">
                        <label class="form-label cm-form-label">Password</label>
                        <input class="form-control cm-form-control" type="password" name="password" required>
                    </div>

                    <div class="mb-3">
                        <label class="form-label cm-form-label">Phone</label>
                        <input class="form-control cm-form-control" type="text" name="phoneNumber">
                    </div>

                    <div class="mb-3">
                        <label class="form-label cm-form-label">Address</label>
                        <input class="form-control cm-form-control" type="text" name="address">
                    </div>

                    <button class="btn btn-primary w-100" type="submit">Create account</button>
                </form>

                <p class="mt-3 cm-muted text-center">
                    Already a provider? <a href="/providers/signin">Sign in here</a>.
                </p>
            </div>
        </div>
    </div>

</@layout.site>
