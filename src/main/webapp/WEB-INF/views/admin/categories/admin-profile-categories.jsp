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
          <li><a href="/admin-profile-users">Users</a></li>
          <li><a href="/admin-profile-institutions">Institutions</a></li>
          <li><a href="/admin-profile-donations">Donations</a></li>
          <li><a href="/login">Log out</a></li>
        </ul>
      </li>
    </ul>
    <ul>
      <h2>Categories</h2>
    </ul>
  </nav>
  <section class="form--steps-container">
    <c:if test="${empty categories}">
      <p>No categories found.</p>
    </c:if>
    <c:if test="${not empty categories}">
      <ul class="btn">
        <a href="/categories/add">Add</a>
      </ul>
      <table>
        <tr>
          <th>Name</th>
        </tr>
        <c:forEach items="${categories}" var="category">
          <tr>
            <td>${category.name}</td>
            <td class="btn">
              <a href="/categories/update?id=${category.id}">Update</a>
            </td>
            <td class="btn">
              <a href="/categories/delete?id=${category.id}">Delete</a>
            </td>
          </tr>
        </c:forEach>
      </table>
    </c:if>
  </section>
</header>
</body>
</html>
