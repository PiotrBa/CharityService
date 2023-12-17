<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
          <li><a href="/admin-profile-categories">Categories</a></li>
          <li><a href="/login">Log out</a></li>
        </ul>
      </li>
    </ul>
    <ul>
      <h2>Donations</h2>
    </ul>
  </nav>
  <section class="form--steps-container">
    <c:if test="${empty donations}">
      <p>No donations found.</p>
    </c:if>

    <c:if test="${not empty donations}">
      <table>
        <tr>
          <th>Name</th>
          <th>Quantity of bags</th>
          <th>Categories</th>
          <th>Institutions</th>
          <th>Pick Up Date</th>
          <th>Pick Up Time</th>
          <th>Pick Up Comment</th>
        </tr>
        <c:forEach items="${donations}" var="donation">
          <tr>
            <td>${donation.user.firstName} ${donation.user.lastName}</td>
            <td>${donation.quantity}</td>
            <td>
              <c:forEach items="${donation.categories}" var="category" varStatus="status">
                ${category.name}<c:if test="${not status.last}">,</c:if>
              </c:forEach>
            </td>
            <td>${donation.institutions.name}</td>
            <td><fmt:formatDate value="${donation.pickUpDate}" pattern="yyyy-MM-dd" /></td>
            <td><fmt:formatDate value="${donation.pickUpTime}" pattern="HH:mm" /></td>
            <td>${donation.pickUpComment}</td>
          </tr>
        </c:forEach>
      </table>
    </c:if>
  </section>
</header>
</body>
</html>
