<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Provider Login – Centrix Marketplace</title>
</head>
<body>
<h1>Provider Login</h1>

<#-- Show simple error message if ?error is present in the URL -->
<#if (param.error)??>
    <p style="color: red;">Invalid email or password.</p>
</#if>

<form action="/providers/signin" method="post">
    <div>
        <label>Email</label>
        <input type="email" name="email" required>
    </div>

    <div>
        <label>Password</label>
        <input type="password" name="password" required>
    </div>

    <button type="submit">Sign in</button>
</form>

<p>
    Don’t have a provider account?
    <a href="/providers/signup">Sign up</a>
</p>
</body>
</html>
