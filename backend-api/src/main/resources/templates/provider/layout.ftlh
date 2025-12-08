<#-- layout.ftl: shared layouts for Centrix Marketplace -->

<#macro site title showSearch=false>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>${title}</title>
    <link rel="stylesheet"
          href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css">
    <link rel="stylesheet" href="/css/centrix.css">
</head>
<body class="cm-body">

<nav class="navbar navbar-expand-lg cm-navbar">
    <div class="container-fluid cm-navbar-inner">
        <a class="navbar-brand cm-brand" href="/">Centrix Marketplace</a>

        <#-- Search bar (homepage) -->
        <#if showSearch>
            <form class="d-flex ms-4 flex-grow-1 cm-search-form" action="/" method="get">
                <input class="form-control cm-search-input" type="search" name="q"
                       placeholder="Search Centrix" aria-label="Search">
                <button class="btn cm-search-btn" type="submit">Search</button>
            </form>
        </#if>

        <ul class="navbar-nav ms-auto cm-navbar-right">
            <li class="nav-item">
                <a class="nav-link cm-nav-link" href="/providers/signin">Provider login</a>
            </li>
            <li class="nav-item">
                <a class="nav-link cm-nav-link" href="/providers/signup">Become a provider</a>
            </li>
        </ul>
    </div>
</nav>

<#-- Page header can be supplied by each page -->

<main class="cm-main container-fluid">
    <#nested>
</main>

<footer class="cm-footer">
    <div class="container">
        <span>&copy; ${.now?string("yyyy")} Centrix Marketplace</span>
    </div>
</footer>

</body>
</html>
</#macro>


<#-- Provider dashboard layout -->

<#macro provider title>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>${title}</title>
    <link rel="stylesheet"
          href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css">
    <link rel="stylesheet" href="/css/centrix.css">
</head>
<body class="cm-body">

<nav class="navbar navbar-expand-lg cm-navbar">
    <div class="container-fluid cm-navbar-inner">
        <a class="navbar-brand cm-brand" href="/providers/home">Centrix Provider</a>

        <button class="navbar-toggler" type="button" data-bs-toggle="collapse"
                data-bs-target="#providerNav" aria-controls="providerNav"
                aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>

        <div class="collapse navbar-collapse" id="providerNav">
            <ul class="navbar-nav ms-auto cm-navbar-right">
                <li class="nav-item">
                    <a class="nav-link cm-nav-link" href="/providers/products/upload">Upload product</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link cm-nav-link" href="/providers/reviews">Reviews</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link cm-nav-link" href="/providers/profile/edit">Profile</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link cm-nav-link" href="/providers/logout">Logout</a>
                </li>
            </ul>
        </div>
    </div>
</nav>

<main class="cm-main container-fluid">
    <#nested>
</main>

<footer class="cm-footer">
    <div class="container">
        <span>&copy; ${.now?string("yyyy")} Centrix Marketplace</span>
    </div>
</footer>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
</#macro>
