<#import "/layout.ftlh" as layout>

<@layout.site title="Provider Login – Centrix Marketplace" showSearch=false>

    <div class="container">
        <div class="card shadow-sm cm-card-narrow">
            <div class="card-body">
                <h1 class="h5 text-center mb-3">Provider Login</h1>

                <#if (param.error)??>
                    <div class="alert alert-danger py-2 small mb-3">
                        Invalid email or password.
                    </div>
                </#if>

                <form action="/providers/signin" method="post">
                    <div class="mb-3">
                        <label class="form-label cm-form-label">Email</label>
                        <input class="form-control cm-form-control" type="email" name="email" required>
                    </div>

                    <div class="mb-3">
                        <label class="form-label cm-form-label">Password</label>
                        <input class="form-control cm-form-control" type="password" name="password" required>
                    </div>

                    <button class="btn btn-primary w-100" type="submit">Sign in</button>
                </form>

                <p class="mt-3 cm-muted text-center">
                    New to Centrix? <a href="/providers/signup">Create a provider account</a>
                </p>
            </div>
        </div>
    </div>

</@layout.site>