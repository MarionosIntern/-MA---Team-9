<#import "/layouts/base.ftlh" as layout>

<@layout.block pageTitle="Update Subscription">

<div class="container py-5">
    <a href="/subscriptions/${subscription.id}" class="btn btn-link mb-3">
        <i class="bi bi-arrow-left"></i> Back to subscription
    </a>

    <div class="card shadow-sm">
        <div class="card-header">
            <h1 class="h5 mb-0">Update subscription #${subscription.id}</h1>
        </div>
        <div class="card-body">
            <form action="/subscriptions/update/${subscription.id}" method="post" class="row g-3">
                <div class="col-md-6">
                    <label class="form-label">Customer</label>
                    <input type="text" class="form-control" value="${subscription.customer.name!'Customer'}" disabled>
                </div>
                <div class="col-md-6">
                    <label class="form-label">Customer ID</label>
                    <input type="text" class="form-control" value="${subscription.customer.id!'—'}" disabled>
                </div>

                <div class="col-md-6">
                    <label class="form-label" for="type">Subscription type</label>
                    <select id="type" name="type" class="form-select">
                        <option value="ONE_TIME" <#if subscription.type == "ONE_TIME">selected</#if>>One-Time</option>
                        <option value="MONTHLY" <#if subscription.type == "MONTHLY">selected</#if>>Monthly</option>
                        <option value="ANNUAL" <#if subscription.type == "ANNUAL">selected</#if>>Annual</option>
                    </select>
                </div>

                <div class="col-md-3">
                    <label class="form-label" for="startDate">Start date</label>
                    <input id="startDate" name="startDate" type="date" class="form-control"
                           value="${subscription.startDate?string('yyyy-MM-dd')}">
                </div>
                <div class="col-md-3">
                    <label class="form-label" for="endDate">End date</label>
                    <input id="endDate" name="endDate" type="date" class="form-control"
                           value="${subscription.endDate?string('yyyy-MM-dd')!}">
                </div>

                <div class="col-md-3">
                    <label class="form-label">Status</label>
                    <select name="active" class="form-select">
                        <option value="true" <#if subscription.active>selected</#if>>Active</option>
                        <option value="false" <#if !subscription.active>selected</#if>>Inactive</option>
                    </select>
                </div>

                <div class="col-12 d-flex justify-content-end gap-2">
                    <a href="/subscriptions/${subscription.id}" class="btn btn-outline-secondary">Cancel</a>
                    <button type="submit" class="btn btn-primary">Save changes</button>
                </div>
            </form>
        </div>
    </div>
</div>

</@layout.block>
