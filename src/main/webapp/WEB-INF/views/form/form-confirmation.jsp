<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <meta http-equiv="X-UA-Compatible" content="ie=edge" />
    <title>Form-confirmation</title>
    <link rel="stylesheet" href="<c:url value="/resources/css/style.css"/>"/>
</head>
<body>
<header class="header--form-page">
    <nav class="container container--90">
        <ul class="nav--actions">
            <li class="logged-user">
                Welcome ${user.username}
                <ul class="dropdown">
                    <li><a href="/user-homepage">My Home</a></li>
                    <li><a href="user-profile">My Profile</a></li>
                    <li><a href="/login">Log out</a></li>
                </ul>
            </li>
        </ul>

        <ul>
            <li><a href="/user-homepage" class="btn btn--without-border active">Go to your donation!</a></li>
            <li><a href="/user-homepage/#steps" class="btn btn--without-border">What is it about?</a></li>
            <li><a href="/user-homepage/#about-us" class="btn btn--without-border">About us</a></li>
            <li><a href="/user-homepage/#help" class="btn btn--without-border">Foundations and organizations</a></li>
            <li><a href="/user-homepage/#contact" class="btn btn--without-border">Contact</a></li>
        </ul>
    </nav>

    <div class="slogan container container--90">
        <h2>
            Thank you, ${user.firstName} for submitting the form. <br>
            We will send all the information about the collection to your email.
        </h2>
    </div>
</header>
<footer>
    <div id="contact" class="contact">
        <h2>Contact us</h2>
        <h3>Contact form</h3>
        <form class="form--contact" method="post" action="/form/donation-contact-confirm">
            <div class="form-group form-group--50"><input type="text" name="title" placeholder="Title"/></div>

            <div class="form-group"><textarea name="message" placeholder="Message" rows="1"></textarea></div>

            <button class="btn" type="submit">Send</button>
        </form>
    </div>
    <div class="bottom-line">
        <span class="bottom-line--copy">Copyright &copy; 2018</span>
        <div class="bottom-line--icons">
            <a href="#" class="btn btn--small">
                <img src="<c:url value="/resources/images/icon-facebook.svg"/>"/></a><a href="#" class="btn btn--small">
            <img src="<c:url value="/resources/images/icon-instagram.svg"/>"/></a>
        </div>
    </div>
</footer>
<script src="<c:url value="/resources/js/app.js"/>"></script>
</body>
</html>
