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
                    <li><a href="/admin-profile-institutions/active">Institutions</a></li>
                    <li><a href="/admin-profile-categories/active">Categories</a></li>
                    <li><a href="/login">Log out</a></li>
                </ul>
            </li>
        </ul>
    </nav>
    <section class="form--steps-container">
        <ul class="btn1">
            <a href="/admin-profile-users/active">Active users</a>
        </ul>
        <c:if test="${empty admins}">
        </c:if>
        <c:if test="${not empty admins}">
            <h2 class="centered-h2-with-icon">Inactive Administrators</h2>
        </c:if>
        <div class="table-and-buttons-container">
            <div class="table-container">
                <c:if test="${empty admins}">
                </c:if>
                <c:if test="${not empty admins}">
                    <table>
                        <tr>
                            <th>First Name</th>
                            <th>Last name</th>
                            <th>Email</th>
                            <th>Mobile number</th>
                            <th>Active</th>
                        </tr>
                        <c:forEach items="${admins}" var="admin">
                            <tr>
                                <td>${admin.firstName}</td>
                                <td>${admin.lastName}</td>
                                <td>${admin.email}</td>
                                <td>${admin.mobileNumber}</td>
                                <td>${admin.active}</td>
                                <td class="buttons-container">
                                    <a href="/admin-profile-users/inactive/update?id=${admin.id}">Edit</a>
                                </td>
                                <td class="buttons-container">
                                    <a href="/admin-profile-users/inactive/delete?id=${admin.id}">Delete</a>
                                </td>
                            </tr>
                        </c:forEach>
                    </table>
                </c:if>
            </div>
        </div>
        <c:if test="${empty users}">
        </c:if>
        <c:if test="${not empty users}">
            <h2 class="centered-h2-with-icon">Inactive Users</h2>
        </c:if>
        <div class="table-and-buttons-container">
            <div class="table-container">
                <c:if test="${empty users}">
                    <h1 class="center-text">No inactive users.</h1>
                </c:if>
                <c:if test="${not empty users}">
                    <table>
                        <tr>
                            <th>First Name</th>
                            <th>Last name</th>
                            <th>Email</th>
                            <th>Mobile number</th>
                            <th>Donations</th>
                            <th>Active</th>
                        </tr>
                        <c:forEach items="${users}" var="user">
                            <tr>
                                <td>${user.firstName}</td>
                                <td>${user.lastName}</td>
                                <td>${user.email}</td>
                                <td>${user.mobileNumber}</td>
                                <td>${userDonationsSum[user.id]}</td>
                                <td>${user.active}</td>
                                <td class="buttons-container">
                                    <a href="/admin-profile-users/inactive/update?id=${user.id}">Edit</a>
                                </td>
                                <td class="buttons-container">
                                    <a href="/admin-profile-users/inactive/delete?id=${user.id}">Delete</a>
                                </td>
                            </tr>
                        </c:forEach>
                    </table>
                </c:if>
            </div>
        </div>
    </section>
</header>
</body>
</html>
