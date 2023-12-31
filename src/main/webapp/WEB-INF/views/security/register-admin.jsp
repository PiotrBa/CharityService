<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="select" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <meta http-equiv="X-UA-Compatible" content="ie=edge" />
    <title>Profile</title>
    <link rel="stylesheet" href="<c:url value="/resources/css/style.css"/>"/>
</head>
<body>
<header class="header--form-page">
    <nav class="container container--90">
        <ul class="nav--actions">
            <li class="logged-user">
                Welcome ${user.username}
                <ul class="dropdown">
                    <li><a href="/admin-profile-donations">Donations</a></li>
                    <li><a href="/admin-profile-institutions">Institutions</a></li>
                    <li><a href="/admin-profile-categories">Categories</a></li>
                    <li><a href="/login">Log out</a></li>
                </ul>
            </li>
        </ul>
    </nav>
    <div class="slogan container container--90">
        <div class="form-container">
            <h2>Admin registration</h2>
            <form:form method="post" modelAttribute="newAdmin">
                <div class="form-column">
                    <div class="form-group">
                        <label style="text-align: center;" for="firstName">First name:</label>
                        <form:input path="firstName" id="firstName" class="form-control"/>
                    </div>
                    <div class="form-group">
                        <label style="text-align: center;" for="email">Email:</label>
                        <form:input path="email" id="email" class="form-control"/>
                    </div>
                    <div class="form-group">
                        <label style="text-align: center;" for="username">User name:</label>
                        <form:input path="username" id="username" class="form-control"/>
                    </div>
                </div>
                <div class="form-column">
                    <div class="form-group">
                        <label style="text-align: center;" for="lastName">Last name:</label>
                        <form:input path="lastName" id="lastName" class="form-control"/>
                    </div>
                    <div class="form-group">
                        <label style="text-align: center;" for="mobileNumber">Mobile number:</label>
                        <form:input path="mobileNumber" id="mobileNumber" class="form-control"/>
                    </div>
                    <div class="form-group">
                        <label for="password">Password:</label>
                        <input type="password" id="password" name="password" class="form-control"/>
                    </div>
                </div>
                <div style="text-align: center;" class="form-buttons">
                    <form:button class="btn">Sign up</form:button>
                    <a href="/admin-profile-users" class="btn btn-secondary">Back</a>
                </div>
            </form:form>
        </div>
    </div>
</header>
</body>
</html>
