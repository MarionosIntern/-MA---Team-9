<#macro block pageTitle="Centrix Marketplace" logoUrl="https://placehold.co/70x70/111827/FFFFFF?text=C">
<!doctype html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>${pageTitle}</title>

    <!-- Bootstrap + Icons -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.5/font/bootstrap-icons.css">
    <link rel="stylesheet" href="/css/styles.css">
</head>

<body>

<!-- ===== Header / Nav ===== -->
<header class="site-header py-3 border-bottom bg-dark text-white">
    <div class="container">
        <nav role="navigation" aria-label="Top navigation" class="d-flex align-items-center justify-content-between">
            <a href="/" class="d-flex align-items-center text-decoration-none text-white">
                <img src="${logoUrl}" alt="Centrix Marketplace logo" width="70" height="70" class="me-2 rounded"
                     style="object-fit:cover;"
                     onerror="this.onerror=null;this.src='https://placehold.co/70x70/111827/FFFFFF?text=C';">
                <span class="h4 mb-0">Centrix Marketplace</span>
            </a>

            <div class="d-flex align-items-center gap-3">
                <a href="/cart/view" id="open-cart" class="text-decoration-none text-white" aria-label="View cart">
                    MyCart <span id="cart-count" class="badge bg-primary text-white ms-1">${sessionCartCount!0}</span>
                </a>

                <a href="/favorites" id="favorites-link" class="text-decoration-none position-relative text-white">
                    <i class="bi bi-heart" style="font-size:1.25rem;"></i>
                    <span id="favorites-count" class="badge bg-danger text-white position-absolute"
                          style="top:-6px;right:-10px;display:${((favoritesCount!0) gt 0)?then('inline-block','none')}"
                    >${favoritesCount!0}</span>
                </a>

                <#if currentCustomerName??>
                    <span class="text-white small">Hi, ${currentCustomerName}</span>
                    <form action="/signout" method="post" class="d-inline">
                        <button type="submit" class="btn btn-outline-light btn-sm">Sign out</button>
                    </form>
                <#else>
                    <a href="/signup" class="btn btn-outline-light btn-sm">Sign up</a>
                    <a href="/signin" class="btn btn-primary btn-sm">Sign in</a>
                </#if>
            </div>
        </nav>

        <!-- Search Bar -->
        <div class="mt-3">
            <form id="search-form" class="d-flex" role="search" aria-label="Search products"
                  action="/products" method="get">
                <select name="category" class="form-select me-2" style="max-width:180px;">
                    <option value="all" selected>All</option>
                    <option value="Clothes">Clothes</option>
                    <option value="Shoes">Shoes</option>
                    <option value="Technology">Technology</option>
                    <option value="Accessories">Accessories</option>
                    <option value="Home">Home</option>
                </select>
                <input id="search-input" name="q" class="form-control me-2" type="search"
                       placeholder="Search Centrix" aria-label="Search products">
                <button id="search-submit" class="btn btn-primary" type="submit">Search</button>
            </form>
        </div>
    </div>
</header>

<!-- ===== Main Content Placeholder ===== -->
<main id="main" role="main">
    <#nested>
</main>

<!-- ===== Footer ===== -->
<footer class="py-4 bg-dark text-white">
    <div class="container d-flex justify-content-between">
        <small>&copy; Centrix 2025</small>
        <div>
            <a href="/privacy" class="text-white text-decoration-none me-3">Privacy</a>
            <a href="/terms" class="text-white text-decoration-none">Terms</a>
        </div>
    </div>
</footer>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
<script src="/js/cart.js"></script>
</body>
</html>
</#macro>
