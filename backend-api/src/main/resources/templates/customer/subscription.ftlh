<#import "layouts/base.fthl" as layout>
<@layout.block pageTitle="Subscription Plans">

<div class="container py-5">
    <h1 class="mb-4">Your Subscriptions</h1>

    <#if subscriptions?? && subscriptions?size > 0>
        <table class="table table-bordered">
            <thead class="table-light">
                <tr>
                    <th>Type</th>
                    <th>Status</th>
                    <th>Start Date</th>
                    <th>End Date</th>
                    <th></th>
                </tr>
            </thead>
            <tbody>
            <#list subscriptions as sub>
                <tr>
                    <td>${sub.type}</td>
                    <td>
                        <#if sub.active>
                            <span class="badge bg-success">Active</span>
                        <#else>
                            <span class="badge bg-secondary">Inactive</span>
                        </#if>
                    </td>
                    <td>${sub.startDate}</td>
                    <td>${sub.endDate}</td>
                    <td>
                        <#if sub.active>
                            <form action="/subscriptions/cancel/${sub.id}" method="post" style="display:inline;">
                                <button type="submit" class="btn btn-outline-danger btn-sm">Cancel</button>
                            </form>
                        <#else>
                            <a href="/subscriptions/renew/${sub.id}" class="btn btn-outline-primary btn-sm">Renew</a>
                        </#if>
                    </td>
                </tr>
            </#list>
            </tbody>
        </table>
    <#else>
        <div class="alert alert-info">
            You don’t have any subscriptions yet. <a href="/subscriptions/new">Start one now!</a>
        </div>
    </#if>
</div>

</@layout.block>
