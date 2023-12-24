<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="com">
<head>
  <meta charset="UTF-8"/>
  <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
  <meta http-equiv="X-UA-Compatible" content="ie=edge"/>
  <title>Home</title>

  <link rel="stylesheet" href="<c:url value="/resources/css/style.css"/>"/>
</head>
<body>
<header class="header--main-page">
  <nav class="container container--70">
    <ul class="nav--actions">
      <li class="logged-user">
        Welcome ${user.username}
        <ul class="dropdown">
          <li><a href="/user-homepage">My Home</a></li>
          <li><a href="/login">Log out</a></li>
        </ul>
      </li>
    </ul>

    <ul>
      <li><a href="/form" class="btn btn--without-border active">Start</a></li>
      <li><a href="#" class="btn btn--without-border">What is it about?</a></li>
      <li><a href="#" class="btn btn--without-border">About us</a></li>
      <li><a href="#" class="btn btn--without-border">Foundations and organizations</a></li>
      <li><a href="#" class="btn btn--without-border">Contact</a></li>
    </ul>
  </nav>

  <div class="slogan container container--90">
    <div class="slogan--item">
      <h1>
        You are ${user.firstName} on your profile.<br/>
        Here you can check your details and the list of donations you have made!
      </h1>
      <div>
        <table>
          <tr>
            <th>First name</th>
            <th>Last name</th>
            <th>Email</th>
            <th>Mobile number</th>
          </tr>
          <tr>
            <td>${user.firstName}</td>
            <td>${user.lastName}</td>
            <td>${user.email}</td>
            <td>${user.mobileNumber}</td>
            <td>
              <a href="user-profile-edit=${user.id}"> Edit</a>
            </td>
          </tr>
        </table>
      </div>
      <br>
      <div>
        <c:if test="${not empty donations}">
          <table>
            <tr>
              <th>Quantity</th>
              <th>Category</th>
              <th>Institution</th>
              <th>Pick Up Date and Time</th>
              <th>Pick Up Comment</th>
            </tr>
            <c:forEach items="${donations}" var="userDonation">
              <tr>
                <td>${userDonation.quantity}</td>
                <td>
                  <c:forEach var="category" items="${userDonation.categories}" varStatus="status">
                    ${category.name}<c:if test="${not status.last}">, </c:if>
                  </c:forEach>
                </td>
                <td>${userDonation.institutions.name}</td>
                <td>${userDonation.createPickApFormatted}</td>
                <td>${userDonation.pickUpComment}</td>
              </tr>
            </c:forEach>
          </table>
        </c:if>
        <c:if test="${empty donations}">
          <p>You have not made any donations yet.</p>
        </c:if>
      </div>
    </div>
  </div>
</header>

<section class="stats">
  <div class="container container--85">
    <div class="stats--item">
      <em>${sumUserQuantities}</em>
      <h3>Your donated bags</h3>
      <p>Lorem ipsum dolor sit amet consectetur adipisicing elit. Eius est beatae, quod accusamus illum
        tempora!</p>
    </div>

    <div class="stats--item">
      <em>${countUserDonations}</em>
      <h3>Your gifts given</h3>
      <p>Lorem ipsum dolor sit amet consectetur, adipisicing elit. Laboriosam magnam, sint nihil cupiditate quas
        quam.</p>
    </div>

  </div>
</section>

<section class="steps">
  <h2>Just 4 simple steps</h2>

  <div class="steps--container">
    <div class="steps--item">
      <span class="icon icon--hands"></span>
      <h3>Choose things</h3>
      <p>clothes, toys, equipment and more</p>
    </div>
    <div class="steps--item">
      <span class="icon icon--arrow"></span>
      <h3>Pack them up</h3>
      <p>use garbage bags</p>
    </div>
    <div class="steps--item">
      <span class="icon icon--glasses"></span>
      <h3>Decide who you want to help</h3>
      <p>choose a trusted place</p>
    </div>
    <div class="steps--item">
      <span class="icon icon--courier"></span>
      <h3>Order a courier</h3>
      <p>the courier will arrive at a convenient time</p>
    </div>
  </div>

  <a href="/form" class="btn btn--large">START!</a>
</section>

<section class="about-us">
  <div class="about-us--text">
    <h2>About us</h2>
    <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit. Voluptas vitae animi rem pariatur incidunt libero
      optio esse quisquam illo omnis.</p>
    <img src="<c:url value="/resources/images/signature.svg"/>" class="about-us--text-signature" alt="Signature"/>
  </div>
  <div class="about-us--image"><img src="<c:url value="/resources/images/about-us.jpg"/>" alt="People in circle"/>
  </div>
</section>

<section class="help">
  <h2>Who do we help?</h2>

  <!-- SLIDE 1 -->
  <div class="help--slides active" data-id="1">
    <p>In our database you will find a list of verified Foundations with which we cooperate.
      You can check what they do.</p>

    <ul class="help--slides-items">
      <c:forEach items="${intitutionsList}" var="institution" varStatus="loopStatus">
        <c:if test="${loopStatus.index % 2 == 0}">
          <li>
        </c:if>
        <div class="col">
          <div class="title">"${institution.name}" Foundation</div>
          <div class="subtitle">Goal and mission: ${institution.description}.</div>
        </div>
        <c:if test="${loopStatus.index}">
          </li>
        </c:if>
      </c:forEach>
    </ul>
  </div>

</section>

<footer>
  <div class="contact">
    <h2>Contact us</h2>
    <h3>Contact form</h3>
    <form class="form--contact">
      <div class="form-group form-group--50"><input type="text" name="name" placeholder="Name"/></div>
      <div class="form-group form-group--50"><input type="text" name="surname" placeholder="Last name"/></div>

      <div class="form-group"><textarea name="message" placeholder="Message" rows="1"></textarea></div>

      <button class="btn" type="submit">Send</button>
    </form>
  </div>
  <div class="bottom-line">
    <span class="bottom-line--copy">Copyright &copy; 2018</span>
    <div class="bottom-line--icons">
      <a href="#" class="btn btn--small"><img src="images/icon-facebook.svg"/></a> <a href="#"
                                                                                      class="btn btn--small"><img
            src="images/icon-instagram.svg"/></a>
    </div>
  </div>
</footer>

<script src="<c:url value="/resources/js/app.js"/>"></script>
</body>
</html>
