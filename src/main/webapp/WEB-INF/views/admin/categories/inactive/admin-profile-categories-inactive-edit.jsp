<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
                    <li><a href="/admin-profile-users/active">Users</a></li>
                    <li><a href="/admin-profile-institutions/active">Institutions</a></li>
                    <li><a href="/admin-profile-donations">Donations</a></li>
                    <li><a href="/login">Log out</a></li>
                </ul>
            </li>
        </ul>
    </nav>
    <div class="slogan container container--90">
        <div class="login-style">
            <h2>Edit inactive category</h2>
            <form:form method="post" modelAttribute="category" class="form-edit-user">
                <div class="section--columns">
                    <form:input placeholder="Category name" path="name" id="name" class="form-control"/>
                </div>
                <div class="section--columns">
                    <div class="form-group--radio">
                        <form:radiobutton path="active" value="true" label="Active"/>
                        <form:radiobutton path="active" value="false" label="No Active"/>
                    </div>
                </div>
                <div style="text-align: center;" class="form-buttons">
                    <form:button class="btn">Save</form:button>
                    <a href="/admin-profile-categories/inactive" class="btn btn-secondary">Back</a>
                </div>
            </form:form>
        </div>
    </div>
</header>
</body>
</html>

