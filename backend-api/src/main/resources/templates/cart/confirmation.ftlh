<#import "/layouts/base.ftlh" as layout>
<@layout.block pageTitle="Order Confirmation">

<div class="container py-5">
    <div class="card shadow-sm mx-auto" style="max-width:640px;">
        <div class="card-body text-center">
            <h1 class="h4 mb-3">Thank you!</h1>
            <p class="text-muted mb-4">
                <#if checkoutSuccess??>
                    ${checkoutSuccess}
                <#else>
                    Your order has been placed successfully.
                </#if>
            </p>
            <a class="btn btn-primary me-2" href="/">Continue shopping</a>
            <a class="btn btn-outline-secondary" href="/cart/view">View cart</a>
        </div>
    </div>
</div>

</@layout.block>
