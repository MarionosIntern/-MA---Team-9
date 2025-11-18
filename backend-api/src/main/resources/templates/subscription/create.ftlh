<#import "/layouts/base.ftlh" as layout>
<@layout.base title="Create Subscription">

<h1>Create New Subscription</h1>

<form action="/subscriptions/create" method="post">

    <label><strong>Customer ID</strong></label>
    <input type="number" name="customer.id" required>

    <label><strong>Subscription Type</strong></label>
    <select name="type" required>
        <option value="ONE_TIME">ONE_TIME</option>
        <option value="MONTHLY">MONTHLY</option>
    </select>

    <label><strong>Start Date</strong></label>
    <input type="datetime-local" name="startDate" required>

    <label><strong>End Date</strong> (optional)</label>
    <input type="datetime-local" name="endDate">

    <br><br>
    <button type="submit">Create Subscription</button>
</form>

<br>
<a href="/subscriptions">← Back to Subscriptions</a>

</@layout.base>
