<#import "/layout.ftl" as layout>

<@layout.provider title="Edit Profile – Centrix Marketplace">

    <div class="container py-3">
        <h1 class="h5 mb-2">Edit profile</h1>
        <p class="cm-muted mb-3">
            Update your provider account information. Enter your current password to confirm changes.
        </p>

        <div class="card bg-white cm-card-narrow" style="max-width: 540px;">
            <div class="card-body">

                <#-- In your controller you can set model.addAttribute("error", "...") -->
                <#if error??>
                    <div class="alert alert-danger py-2 small mb-3">
                        ${error}
                    </div>
                </#if>

                <form action="/providers/profile/edit" method="post">
                    <div class="mb-3">
                        <label class="form-label cm-form-label">Provider / Store name</label>
                        <input class="form-control cm-form-control"
                               type="text" name="name"
                               value="${provider.name}" required>
                    </div>

                    <div class="mb-3">
                        <label class="form-label cm-form-label">Email</label>
                        <input class="form-control cm-form-control"
                               type="email" name="email"
                               value="${provider.email}" required>
                    </div>

                    <div class="mb-3">
                        <label class="form-label cm-form-label">Phone</label>
                        <input class="form-control cm-form-control"
                               type="text" name="phoneNumber"
                               value="${provider.phoneNumber!""}">
                    </div>

                    <div class="mb-3">
                        <label class="form-label cm-form-label">Address</label>
                        <input class="form-control cm-form-control"
                               type="text" name="address"
                               value="${provider.address!""}">
                    </div>

                    <hr class="my-3">

                    <h2 class="h6 mb-2">Change password (optional)</h2>
                    <p class="cm-muted mb-2">
                        Enter your current password to save profile changes. If you also
                        fill in a new password, it will be updated.
                    </p>

                    <div class="mb-3">
                        <label class="form-label cm-form-label">Current password</label>
                        <input class="form-control cm-form-control"
                               type="password" name="currentPassword" required>
                    </div>

                    <div class="mb-3">
                        <label class="form-label cm-form-label">New password (optional)</label>
                        <input class="form-control cm-form-control"
                               type="password" name="newPassword">
                    </div>

                    <button class="btn btn-primary" type="submit">Save profile</button>
                    <a href="/providers/home" class="btn btn-outline-secondary ms-2">Cancel</a>
                </form>
            </div>
        </div>
    </div>

</@layout.provider>