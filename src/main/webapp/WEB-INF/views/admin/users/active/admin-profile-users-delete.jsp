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
          <li><a href="/admin-profile-donations">Donations</a></li>
          <li><a href="/admin-profile-institutions">Institutions</a></li>
          <li><a href="/admin-profile-categories/active">Categories</a></li>
          <li><a href="/login">Log out</a></li>
        </ul>
      </li>
    </ul>
  </nav>
  <div class="slogan container container--90">
    <div class="login-style">
      <form:form method="post" modelAttribute="user" class="form-edit-user">
        <div class="section--columns">
          <h1>Do you want to delete the user ${user.firstName} ${user.lastName}?</h1><br>
        </div>
        <div class="section--columns">
          <h1>If you do this, you will also remove his donations from the list.</h1>
        </div>
        <div style="text-align: center;" class="form-buttons">
          <form:button class="btn">Yes</form:button>
          <a href="/admin-profile-users/active" class="btn btn-secondary">No</a>
        </div>
        </form:form>
    </div>
  </div>
</header>
</body>
</html>
