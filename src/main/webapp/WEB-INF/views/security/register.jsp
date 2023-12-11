<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <meta http-equiv="X-UA-Compatible" content="ie=edge" />
    <title>Register</title>
    <link rel="stylesheet" href="<c:url value="resources/css/style.css"/>"/>
</head>
<body>
<form:form method="post" modelAttribute="user">
    <div>
        User name: <form:input path="username"/>
    </div>
    <div>
        Password: <form:input path="password"/>
    </div>
    <div>
        First name: <form:input path="firstName"/>
    </div>
    <div>
        Last name: <form:input path="lastName"/>
    </div>
    <div>
        Mobile number: +44<form:input path="mobileNumber"/>
    </div>
    <div>
        Email: <form:input path="email"/>
    </div>
    <br>
    <form:button>Sign up</form:button>
</form:form>
<div>
    <a href="/login">Log in</a>
</div>

</body>
</html>
