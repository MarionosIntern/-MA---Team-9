<#-- subscription/update.ftlh -->
<#include "/layout/header.ftlh">

<h2 class="page-title">Update Subscription</h2>

<form action="/subscriptions/update/${subscription.id}" method="post" class="form-container">

    <div class="form-group">
        <label>Customer ID:</label>
        <input type="text" class="form-control" value="${subscription.customer.id}" disabled>
    </div>

    <div class="form-group">
        <label for="type">Subscription Type:</label>
        <select id="type" name="type" class="form-control">
            <option value="ONE_TIME" <#if subscription.type == "ONE_TIME">selected</#if>>One-Time</option>
            <option value="MONTHLY" <#if subscription.type == "MONTHLY">selected</#if>>Monthly</option>
        </select>
    </div>

    <div class="form-group">
        <label for="startDate">Start Date:</label>
        <input id="startDate" name="startDate" type="date"
               class="form-control"
               value="${subscription.startDate?string('yyyy-MM-dd')}">
    </div>

    <div class="form-group">
        <label for="endDate">End Date:</label>
        <input id="endDate" name="endDate" type="date"
               class="form-control"
               value="${subscription.endDate?string('yyyy-MM-dd')!}">
    </div>

    <div class="form-group">
        <label>Status:</label>
        <select name="active" class="form-control">
            <option value="true" <#if subscription.active>selected</#if>>Active</option>
            <option value="false" <#if !subscription.active>selected</#if>>Inactive</option>
        </select>
    </div>

    <button type="submit" class="btn btn-success">Save Changes</button>
    <a href="/subscriptions/${subscription.id}" class="btn btn-secondary">Cancel</a>

</form>

<#include "/layout/footer.ftlh">
