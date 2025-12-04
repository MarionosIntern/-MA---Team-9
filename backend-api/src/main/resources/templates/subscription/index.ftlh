<#import "/layouts/base.ftlh" as layout>

<@layout.block pageTitle="Your Subscriptions">

<div class="container py-5">
    <div class="d-flex flex-wrap justify-content-between align-items-center mb-4 gap-3">
        <div>
            <p class="text-uppercase small text-muted mb-1">Customer</p>
            <h1 class="h4 mb-0">${customer.name!'Centrix customer'}</h1>
            <p class="text-muted mb-0">${customer.email!'No email on file'}</p>
        </div>
        <a href="/subscriptions/create" class="btn btn-primary">
            <i class="bi bi-plus-lg me-1"></i> Create subscription
        </a>
    </div>

    <#if subscriptions?has_content>
        <div class="card shadow-sm">
            <div class="card-body p-0">
                <table class="table table-hover align-middle mb-0">
                    <thead class="table-light">
                    <tr>
                        <th scope="col">ID</th>
                        <th scope="col">Type</th>
                        <th scope="col">Start</th>
                        <th scope="col">End</th>
                        <th scope="col">Status</th>
                        <th scope="col" class="text-end">Actions</th>
                    </tr>
                    </thead>
                    <tbody>
                    <#list subscriptions as sub>
                        <tr>
                            <td>${sub.id}</td>
                            <td>${sub.type}</td>
                            <td>${sub.startDate?string("yyyy-MM-dd")}</td>
                            <td>
                                <#if sub.endDate??>
                                    ${sub.endDate?string("yyyy-MM-dd")}
                                <#else>
                                    <span class="text-muted">Ongoing</span>
                                </#if>
                            </td>
                            <td>
                                <span class="badge ${sub.active?then('text-bg-success','text-bg-secondary')}">
                                    ${sub.active?then('Active','Inactive')}
                                </span>
                            </td>
                            <td class="text-end">
                                <div class="btn-group btn-group-sm">
                                    <a href="/subscriptions/${sub.id}" class="btn btn-outline-primary">Details</a>
                                    <a href="/subscriptions/update/${sub.id}" class="btn btn-outline-secondary">Edit</a>
                                    <#if sub.active>
                                        <a href="/subscriptions/cancel/${sub.id}"
                                           class="btn btn-outline-danger"
                                           onclick="return confirm('Cancel this subscription?');">Cancel</a>
                                    </#if>
                                </div>
                            </td>
                        </tr>
                    </#list>
                    </tbody>
                </table>
            </div>
        </div>
    <#else>
        <div class="card shadow-sm">
            <div class="card-body text-center py-5">
                <h2 class="h5 mb-2">No subscriptions yet</h2>
                <p class="text-muted mb-4">Start a plan to unlock recurring delivery and billing automation.</p>
                <a href="/subscriptions/create" class="btn btn-primary">Create subscription</a>
            </div>
        </div>
    </#if>
</div>

</@layout.block>
