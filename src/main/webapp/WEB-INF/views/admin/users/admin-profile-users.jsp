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
    <section class="form--steps-container">
        <h2 class="centered-h2-with-icon">Users</h2>
        <div class="table-and-buttons-container">
        <div class="table-container">
        <c:if test="${empty users}">
            <p>No users found.</p>
        </c:if>
        <c:if test="${not empty users}">
            <table>
                <tr>
                    <th>First Name</th>
                    <th>Last name</th>
                    <th>Email</th>
                    <th>Mobile number</th>
                    <th>Donations</th>
                    <th>Role</th>
                    <th>Active</th>
                </tr>
                <c:forEach items="${users}" var="user">
                    <tr>
                        <td>${user.firstName}</td>
                        <td>${user.lastName}</td>
                        <td>${user.email}</td>
                        <td>${user.mobileNumber}</td>
                        <td>${user.userDonations.size()}</td>
                        <td>${user.role}</td>
                        <td>${user.active}</td>
                        <td class="buttons-container">
                            <a href="/user/delete?id=${user.id}">Delete</a>
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
