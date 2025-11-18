<#import "/layouts/base.ftlh" as layout>
<@layout.block pageTitle="Customers">

<div class="container py-5">
    <div class="d-flex flex-column flex-md-row justify-content-between align-items-md-center gap-3 mb-4">
        <div>
            <p class="text-uppercase text-muted mb-1">Directory</p>
            <h1 class="h3 mb-0">Customers</h1>
        </div>
        <div class="d-flex gap-2">
            <a href="/customers/create" class="btn btn-primary">
                <i class="bi bi-person-plus me-1"></i> New Customer
            </a>
            <a href="/customers" class="btn btn-outline-secondary">Refresh</a>
        </div>
    </div>

    <#if customerList?? && customerList?size gt 0>
        <div class="table-responsive shadow-sm rounded">
            <table class="table align-middle mb-0">
                <thead class="table-light">
                <tr>
                    <th scope="col">ID</th>
                    <th scope="col">Name</th>
                    <th scope="col">Email</th>
                    <th scope="col">Phone</th>
                    <th scope="col" class="text-end">Actions</th>
                </tr>
                </thead>
                <tbody>
                <#list customerList as customer>
                    <tr>
                        <td>${customer.id}</td>
                        <td>${customer.name!"-"}</td>
                        <td>${customer.email!"-"}</td>
                        <td>${customer.phoneNumber!"-"}</td>
                        <td class="text-end">
                            <a href="/customers/${customer.id}" class="btn btn-sm btn-outline-primary">View</a>
                            <a href="/customers/update/${customer.id}" class="btn btn-sm btn-outline-secondary">Edit</a>
                            <a href="/customers/delete/${customer.id}" class="btn btn-sm btn-outline-danger"
                               onclick="return confirm('Delete this customer?');">Delete</a>
                        </td>
                    </tr>
                </#list>
                </tbody>
            </table>
        </div>
    <#else>
        <div class="alert alert-info">
            No customers found yet. Use the button above to create the first record.
        </div>
    </#if>
</div>

</@layout.block>
