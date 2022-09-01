<!DOCTYPE html>
<!--
<html lang="en">
-->
<html xmlns="http://www.w3.org/1999/xhtml" xmlns:th="https://www.thymeleaf.org"
      xmlns:sec="https://www.thymeleaf.org/thymeleaf-extras-springsecurity3">

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <title>Login page</title>

    <link rel="shortcut icon" type="image/x-icon" href="/images/favicon.png"/>
    <link rel="stylesheet" type="text/css" href="/styles/css/bootstrap.css"/>
    <link rel="stylesheet" type="text/css" href="/styles/css/login.css"/>
</head>
<body>
<div class="container">

    <form class="form-signin" method="post" action="/">
        <h2 class="form-signin-heading">Please sign in</h2>
        <#if logout>
            <div class="alert alert-info" role="alert">You've been logged out successfully.</div>
        </#if>
        <#if error>
            <div class="alert alert-danger" role="alert">Invalid Username or Password!</div>
        </#if>
        <p>
            <label for="username" class="sr-only">Username</label>
            <input type="text" id="username" name="username" class="form-control" placeholder="Username" required
                   autofocus>
        </p>
        <p>
            <label for="password" class="sr-only">Password</label>
            <input type="password" id="password" name="password" class="form-control" placeholder="Password" required>
        </p>
        <input name="${_csrf.parameterName}" type="hidden" value="${_csrf.token}"/>
        <button class="btn btn-lg btn-primary btn-block" type="submit">Sign in</button>
    </form>
</div>
</body>
</html>