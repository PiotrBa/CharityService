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
          <li><a href="/admin-profile-donations">Donations</a></li>
          <li><a href="/admin-profile-categories/active">Categories</a></li>
          <li><a href="/login">Log out</a></li>
        </ul>
      </li>
    </ul>
  </nav>
  <h2 class="centered-h2-with-icon">Institutions</h2>
  <section class="form--steps-container">
    <div class="table-container">
    <c:if test="${empty institutions}">
      <h1 class="center-text">No institutions found.</h1>
    </c:if>
    <c:if test="${not empty institutions}">
      <ul class="btn1">
        <a href="/admin-profile-institutions/add">Add</a>
      </ul>
      <table>
        <tr>
          <th>Name</th>
          <th>Description</th>
        </tr>
        <c:forEach items="${institutions}" var="institution">
          <tr>
            <td>${institution.name}</td>
            <td>${institution.description}</td>
            <td class="buttons-container">
              <a href="/admin-profile-institutions/update?id=${institution.id}">Update</a>
            </td>
            <td class="buttons-container">
              <a href="/admin-profile-institutions/delete?id=${institution.id}">Delete</a>
            </td>
          </tr>
        </c:forEach>
      </table>
    </div>
    </c:if>
  </section>
</header>
</body>
</html>
