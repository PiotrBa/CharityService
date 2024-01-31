<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <meta http-equiv="X-UA-Compatible" content="ie=edge" />
    <title>Donation Form</title>
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
                    <li><a href="/user-profile">My profile</a></li>
                    <li><a href="/login">Log out</a></li>
                </ul>
            </li>
        </ul>

        <ul>
            <li><a href="/user-profile" class="btn btn--without-border active">Your profile</a></li>
            <li><a href="/user-homepage/#steps" class="btn btn--without-border">What is it about?</a></li>
            <li><a href="/user-homepage/#about-us" class="btn btn--without-border">About us</a></li>
            <li><a href="/user-homepage/#help" class="btn btn--without-border">Foundations and organizations</a></li>
            <li><a href="/user-homepage/#contact" class="btn btn--without-border">Contact</a></li>
        </ul>
    </nav>

    <div class="slogan container container--90">
        <div class="slogan--item">
            <h1>
                Give away things you no longer want<br />
                <span class="uppercase">to those in need</span>
            </h1>

            <div class="slogan--steps">
                <div class="slogan--steps-title">Just 4 simple steps:</div>
                <ul class="slogan--steps-boxes">
                    <li>
                        <div><em>1</em><span>Select items</span></div>
                    </li>
                    <li>
                        <div><em>2</em><span>Pack them in bags</span></div>
                    </li>
                    <li>
                        <div><em>3</em><span>Choose a foundation</span></div>
                    </li>
                    <li>
                        <div><em>4</em><span>Order a courier</span></div>
                    </li>
                </ul>
            </div>
        </div>
    </div>
</header>

<section class="form--steps">
    <div class="form--steps-instructions">
        <div class="form--steps-container">
            <h3>Important!</h3>
            <p data-step="1" class="active">
                Fill in the details about your items. This will let us know who best to give them to.
            </p>
            <p data-step="2">
                Fill in the details about your items. This will let us know who best to give them to.
            </p>
            <p data-step="3">
                Choose one,
                to which your shipment will go.
            </p>
            <p data-step="4">Provide the address and pickup date for the items.</p>
        </div>
    </div>

    <div class="form--steps-container">
        <div class="form--steps-counter">Step <span>1</span>/4</div>
        <form:form modelAttribute="donation" method="post">
            <!-- STEP 1: class .active is switching steps -->
            <div data-step="1" class="active">
                <h3>Mark what you want to donate:</h3>
                <c:forEach items="${categoryList}" var="category">
                    <div class="form-group form-group--checkbox">
                        <label>
                            <input type="checkbox" name="categories" value="${category.id}" id="category"/>
                            <span class="checkbox"></span>
                            <span class="description">${category.name}</span>
                        </label>
                    </div>
                </c:forEach>
                <div class="form-group form-group--buttons">
                    <button type="button" class="btn next-step">Next</button>
                </div>
                <input type="hidden" id="hiddenCategories" name="hiddenCategories" />

            </div>

            <!-- STEP 2 -->
            <div data-step="2">
                <h3>Enter the number of 60l bags in which you have packed the items:</h3>

                <div class="form-group form-group--inline">
                    <label>
                        Number of 60l bags:
                        <form:input type="number" path="quantity" step="1" min="1" id="quantity"/>
                    </label>
                </div>

                <div class="form-group form-group--buttons">
                    <button type="button" class="btn prev-step">Back</button>
                    <button type="button" class="btn next-step">Next</button>
                </div>
                <input type="hidden" id="hiddenQuantity" name="hiddenQuantity" />

            </div>



            <!-- STEP 4 -->
            <div data-step="3">
                <h3>Choose the organization you want to help:</h3>
                <div class="form-group form-group--checkbox">
                    <c:forEach items="${institutionList}" var="institution">
                        <label style="margin-bottom: 20px">
                            <form:radiobutton path="institutions" name="${institution.name}" value="${institution.id}" id="institution"/>
                            <span class="checkbox radio"></span>
                            <span class="description">
                  <div class="title">Foundation: ${institution.name}</div>
                  <div class="subtitle">
                    Goal and mission: ${institution.description}
                  </div>
                </span>
                        </label>
                    </c:forEach>
                </div>
                <div class="form-group form-group--buttons">
                    <button type="button" class="btn prev-step">Back</button>
                    <button type="button" class="btn next-step">Next</button>
                </div>
                <input type="hidden" id="hiddenInstitution" name="hiddenInstitution" />

            </div>

            <!-- STEP 5 -->
            <div data-step="4">
                <h3>Provide the address and pickup time for the courier:</h3>

                <div class="form-section form-section--columns">
                    <div class="form-section--column">
                        <h4>Pickup address</h4>
                        <div class="form-group form-group--inline">
                            <label> Street <form:input type="text" path="street" id="street"/> </label>
                        </div>

                        <div class="form-group form-group--inline">
                            <label> City <form:input type="text" path="city" id="city"/> </label>
                        </div>

                        <div class="form-group form-group--inline">
                            <label> Zip code <form:input path="zipCode" id="zipCode"/></label>
                        </div>
                        <input type="hidden" id="hiddenStreet" name="hiddenStreet" />
                        <input type="hidden" id="hiddenCity" name="hiddenCity" />
                        <input type="hidden" id="hiddenZipCode" name="hiddenZipCode" />
                        <input type="hidden" id="hiddenPickUpDate" name="hiddenPickUpDate" />
                        <input type="hidden" id="hiddenPickUpTime" name="hiddenPickUpTime" />
                        <input type="hidden" id="hiddenPickUpComment" name="hiddenPickUpComment" />

                    </div>

                    <div class="form-section--column">
                        <h4>Pickup time and data</h4>
                        <div class="form-group form-group--inline">
                            <label> Date <form:input type="date" path="pickUpDate" id="pickUpDate"/> </label>
                            <label> Time <form:input type="time" path="pickUpTime" id="pickUpTime"/> </label>
                        </div>

                        <div class="form-group form-group--inline">
                            <label> Notes for the courier <form:textarea path="pickUpComment" id="pickUpComment" rows="5"></form:textarea></label>
                            <h4 style="color: red;">Don't forget to provide your phone number<br> to the courier in the note!</h4>
                        </div>
                    </div>
                </div>
                <div class="form-group form-group--buttons">
                    <button type="button" class="btn prev-step">Back</button>
                    <button type="button" class="btn next-step">Next</button>
                </div>
            </div>

        <!-- STEP 6 -->
        <div data-step="5">
            <h3>Confirm your details:</h3>
            <div class="summary">
                <div class="form-section">
                    <ul>
                        <li>
                            <span class="icon icon-bag"></span>
                            <span class="summary--text summary-category"
                            >Select bags and categories.</span
                            >
                        </li>

                        <li>
                            <span class="icon icon-hand"></span>
                            <span class="summary--text summary-institution"
                            >Choose a foundation.</span
                            >
                        </li>
                    </ul>
                </div>
                <div class="form-section form-section--columns">
                    <div class="form-section--column">
                        <ul>
                            <li class="pickUpDetails">Enter the street name.</li>
                            <li class="pickUpDetails">Enter the city name.</li>
                            <li class="pickUpDetails">Enter the postcode.</li>
                        </ul>
                    </div>

                    <div class="form-section--column">
                        <ul>
                            <li class="pickUpDetails">Choose a date.</li>
                            <li class="pickUpDetails">Choose a time.</li>
                            <li class="pickUpDetails">Enter a comment for the courier.</li>
                        </ul>
                    </div>
                </div>
            </div>

            <div class="form-group form-group--buttons">
                <button type="button" class="btn prev-step">Back</button>
                <button type="submit" class="btn">Confirm</button>
            </div>
        </div>
        </form:form>
    </div>
</section>

<footer>
    <div id="contact" class="contact">
        <h2>Contact us</h2>
        <h3>Contact form</h3>
        <form class="form--contact" method="post" action="/form/donation-contact">
            <div class="form-group form-group--50"><input type="text" name="title" placeholder="Title"/></div>

            <div class="form-group"><textarea name="message" placeholder="Message" rows="1"></textarea></div>

            <button class="btn" type="submit">Send</button>
        </form>
    </div>
    <div class="bottom-line">
        <span class="bottom-line--copy">Copyright &copy; 2018</span>
        <div class="bottom-line--icons">
            <a href="#" class="btn btn--small"
            ><img src="images/icon-facebook.svg"
            /></a>
            <a href="#" class="btn btn--small"
            ><img src="images/icon-instagram.svg"
            /></a>
        </div>
    </div>
</footer>
<script src="<c:url value="/resources/js/app.js"/>"></script>
</body>
</html>
