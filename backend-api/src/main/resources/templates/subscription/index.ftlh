<#-- templates/subscription/index.ftlh -->
<#include "/layout/header.ftlh">

<div class="page-container">

    <h2 class="page-title">Your Subscriptions</h2>

    <div class="customer-summary">
        <p><strong>Name:</strong> ${customer.name}</p>
        <p><strong>Email:</strong> ${customer.email}</p>
    </div>

    <hr/>

    <#-- If the user has subscriptions -->
    <#if subscriptions?has_content>

        <table class="table table-striped">
            <thead>
                <tr>
                    <th>ID</th>
                    <th>Type</th>
                    <th>Start</th>
                    <th>End</th>
                    <th>Status</th>
                    <th>Total Days</th>
                    <th>Actions</th>
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
                                —
                            </#if>
                        </td>

                        <td>
                            <#if sub.active>
                                <span class="badge badge-success">Active</span>
                            <#else>
                                <span class="badge badge-secondary">Inactive</span>
                            </#if>
                        </td>

                        <td>
                            <#if sub.endDate??>
                                ${sub.startDate?long?number_to_date("yyyy-MM-dd")?date_diff(sub.endDate?long?number_to_date("yyyy-MM-dd"))} days
                            <#else>
                                Ongoing
                            </#if>
                        </td>

                        <td>
                            <a href="/subscriptions/${sub.id}" class="btn btn-info btn-sm">Details</a>

                            <#if sub.active>
                                <a href="/subscriptions/cancel/${sub.id}"
                                   class="btn btn-danger btn-sm"
                                   onclick="return confirm('Cancel this subscription?');">
                                   Cancel
                                </a>
                            </#if>
                        </td>

                    </tr>
                </#list>
            </tbody>
        </table>

    <#-- If the user has NO subscriptions -->
    <#else>
        <div class="empty-state">
            <h3>You have no active subscriptions.</h3>
            <p>Choose a subscription option below to get started.</p>

            <a href="/subscriptions/create" class="btn btn-primary">
                Create Subscription
            </a>
        </div>
    </#if>

</div>

<#include "/layout/footer.ftlh">
