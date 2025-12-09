<#-- Shared layouts for Centrix Marketplace (provider side) -->

<#macro site title showSearch=false>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>${title}</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.1/font/bootstrap-icons.css">
    <link rel="stylesheet" href="/css/styles.css">
</head>
<body>

<header class="site-header py-3 border-bottom bg-dark text-white">
    <div class="container">
        <nav class="d-flex align-items-center justify-content-between">
            <a class="d-flex align-items-center text-decoration-none text-white" href="/">
                <span class="h4 mb-0">Centrix Marketplace</span>
            </a>
            <div class="d-flex align-items-center gap-3">
                <a class="btn btn-outline-light btn-sm" href="/signin">Provider login</a>
                <a class="btn btn-primary btn-sm" href="/providers/signup">Become a provider</a>
            </div>
        </nav>

        <#if showSearch>
            <div class="mt-3">
                <form class="d-flex" action="/" method="get" role="search">
                    <input class="form-control me-2" type="search" name="q"
                           placeholder="Search Centrix" aria-label="Search">
                    <button class="btn btn-primary" type="submit">Search</button>
                </form>
            </div>
        </#if>
    </div>
</header>

<main role="main">
    <#nested>
</main>

<footer class="py-4 bg-dark text-white mt-auto">
    <div class="container d-flex justify-content-between">
        <small>&copy; ${.now?string("yyyy")} Centrix Marketplace</small>
        <div>
            <a href="/privacy" class="text-white text-decoration-none me-3">Privacy</a>
            <a href="/terms" class="text-white text-decoration-none">Terms</a>
        </div>
    </div>
</footer>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
</#macro>


<#-- Provider dashboard layout -->
<#macro provider title>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>${title}</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.1/font/bootstrap-icons.css">
    <link rel="stylesheet" href="/css/styles.css">
</head>
<body>

<header class="site-header py-3 border-bottom bg-dark text-white">
    <div class="container">
        <nav class="navbar navbar-expand-lg navbar-dark">
            <a class="navbar-brand" href="/providers/home">Centrix Provider</a>
            <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#providerNav"
                    aria-controls="providerNav" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>

            <div class="collapse navbar-collapse" id="providerNav">
                <ul class="navbar-nav ms-auto">
                    <li class="nav-item"><a class="nav-link" href="/providers/products/upload">Upload product</a></li>
                    <li class="nav-item"><a class="nav-link" href="/providers/reviews">Reviews</a></li>
                    <li class="nav-item"><a class="nav-link" href="/providers/profile/edit">Profile</a></li>
                    <li class="nav-item"><a class="nav-link" href="/providers/logout">Logout</a></li>
                </ul>
            </div>
        </nav>
    </div>
</header>

<main class="container py-4" role="main">
    <#nested>
</main>

<footer class="py-4 bg-dark text-white mt-auto">
    <div class="container d-flex justify-content-between">
        <small>&copy; ${.now?string("yyyy")} Centrix Marketplace</small>
        <div>
            <a href="/privacy" class="text-white text-decoration-none me-3">Privacy</a>
            <a href="/terms" class="text-white text-decoration-none">Terms</a>
        </div>
    </div>
</footer>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
</#macro>
