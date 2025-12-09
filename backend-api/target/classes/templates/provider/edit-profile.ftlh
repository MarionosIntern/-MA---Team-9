<#import "/provider/layout.ftlh" as layout>

<@layout.provider title="Edit Profile - Centrix">

    <div class="container py-4" style="max-width:640px;">
        <h1 class="h5 mb-2">Edit profile</h1>
        <p class="text-muted mb-3">
            Update your provider account. Enter your current password to confirm changes.
        </p>

        <#if error??>
            <div class="alert alert-danger py-2 small mb-3">
                ${error}
            </div>
        </#if>

        <div class="card shadow-sm">
            <div class="card-body">
                <form action="/providers/profile/edit" method="post" class="vstack gap-3">
                    <div class="row g-3">
                        <div class="col-12">
                            <label class="form-label">Provider / Store name</label>
                            <input class="form-control" type="text" name="name" value="${provider.name!''}" required>
                        </div>
                        <div class="col-md-6">
                            <label class="form-label">Email</label>
                            <input class="form-control" type="email" name="email" value="${provider.email!''}" required>
                        </div>
                        <div class="col-md-6">
                            <label class="form-label">Phone</label>
                            <input class="form-control" type="text" name="phoneNumber" value="${provider.phoneNumber!''}">
                        </div>
                        <div class="col-12">
                            <label class="form-label">Address</label>
                            <input class="form-control" type="text" name="address" value="${provider.address!''}">
                        </div>
                    </div>

                    <hr class="my-2">
                    <h2 class="h6 mb-2">Change password (optional)</h2>
                    <p class="text-muted small mb-2">Enter your current password to save changes. Add a new password if you want to update it.</p>

                    <div>
                        <label class="form-label">Current password</label>
                        <input class="form-control" type="password" name="currentPassword" required>
                    </div>
                    <div>
                        <label class="form-label">New password (optional)</label>
                        <input class="form-control" type="password" name="newPassword">
                    </div>

                    <div class="d-flex justify-content-end gap-2">
                        <a href="/providers/home" class="btn btn-outline-secondary">Cancel</a>
                        <button class="btn btn-primary" type="submit">Save profile</button>
                    </div>
                </form>
            </div>
        </div>
    </div>

</@layout.provider>
