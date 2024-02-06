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
  <div class="slogan container container--90">
    <div class="login-style">
      <form:form method="post" modelAttribute="institution" class="form-edit-user">
        <div class="section--columns">
          <h1>Do you want to delete the ${institution.name} institution?</h1>
        </div>
        <div class="section--columns">
          <h1>The deletion of ${institution.name} will result in the removal of donations associated with it.</h1>
        </div>
        <div style="text-align: center;" class="form-buttons">
          <form:button class="btn">Yes</form:button>
          <a href="/admin-profile-institutions/active" class="btn btn-secondary">No</a>
        </div>
        </form:form>
    </div>
  </div>
</header>
</body>
</html>
