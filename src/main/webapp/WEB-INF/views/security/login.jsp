<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <meta http-equiv="X-UA-Compatible" content="ie=edge"/>
    <title>Login</title>

    <link rel="stylesheet" href="<c:url value="resources/css/style.css"/>"/>
</head>
<body>
<form method="post" action="/login">
    User name: <input type="text" name="username"/> Password: <input type="password" name="password"/>
    <button type="submit">Login</button><br/>
    <br>
</form>
<a href="register/user">Register</a><br>


</body>
</html>
