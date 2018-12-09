<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1" />
    <meta charset="UTF-8">
    <title>Check Password</title>
    <link rel="stylesheet" type="text/css" href="css/style.css">
</head>
<body>
<div id="container">
    <header>
        <div id="headerArea">
            <h2>
                <c:if test="${loggedIn != null}">
                    <p>Welcome <c:out value='${loggedIn.firstName}' /></p>
                    <p><a href="Controller?action=adminOptions" class="adminMode">Admin mode</a></p>
                </c:if>
            </h2>
            <h1>
                <span>Prato Webshop</span>
            </h1>
        </div>
        <nav>
            <ul>
                <li><a href="Controller?action=index">Home</a></li>
                <li><a href="Controller?action=productOverview">Products</a></li>
                <c:if test="${loggedIn != null}"><li><a href="Controller?action=addProductPage">Add Product</a></li></c:if>
                <c:if test="${loggedIn == null}"><li></li></c:if>
                <c:if test="${loggedIn != null}"><li><a href="Controller?action=signUp">Add Admin</a></li></c:if>
                <c:if test="${loggedIn == null}"><li></li></c:if>
                <li id="cartLi"><a href="Controller?action=cart" id="cartLink"><img src="images/ShoppingCart.png" id="cartImage"></a></li>
            </ul>
        </nav>
        <h2>
            Check Password
        </h2>

    </header>

    <c:if test="${loggedIn != null}">
    <main>
        <h3>Fill out your password:</h3>
        <c:if test='${response != null}'>
            <c:if test='${nature.equals("p")}'>
                <p id="positive"><c:out value="${response}"/></p>
            </c:if>
            <c:if test='${nature.equals("n")}'>
                <p id="negative"><c:out value="${response}"/></p>
            </c:if>
            <p><a href="Controller?action=adminOptions" class="back">Back</a></p>
        </c:if>
        <c:if test='${response == null}'>
            <form method="post" action="Controller?action=doCheck&userId=<c:out value='${userId}'/>" novalidate="novalidate">
                <p><label for="passwd">Password</label><input type="password" id="passwd" name="passwd" required></p>
                <p><input type="submit" id="check" value="Check"></p>
            </form>
        </c:if>
    </main>
    </c:if>
    <c:if test="${loggedIn == null}">
        <p class="alert-danger2">Can't access page: insufficient rights.</p>
    </c:if>

    <footer>
        &copy; Testing, UC Leuven-Limburg
    </footer>
</div>
</body>
</html>
