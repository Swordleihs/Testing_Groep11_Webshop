<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1" />
<meta charset="UTF-8">
<title>Add Admin</title>
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
            <c:if test="${loggedIn != null}"><li id="actual"><a href="Controller?action=signUp">Add admin</a></li></c:if>
            <c:if test="${loggedIn == null}"><li></li></c:if>
            <li id="cartLi"><a href="Controller?action=cart" id="cartLink"><img src="images/ShoppingCart.png" id="cartImage"></a></li>
        </ul>
    </nav>
<h2>
Add Admin
</h2>

</header>
    <c:if test="${loggedIn != null}">
    <main>
        <c:if test="${errors.size() > 0}">
            <div class="alert-danger">
                <ul>
                    <c:forEach var="error" items="${errors}">
                        <li> <c:out value="${error}"/> </li>
                    </c:forEach>
                </ul>
            </div>
        </c:if>
        <form method="post" action="Controller?action=addUser" novalidate="novalidate">

            <p><label for="userid">User id</label><input type="text" id="userid" name="userid" required value="<c:out value='${previousUserId}'/>"></p>

            <p><label for="firstName">First Name</label><input type="text" id="firstName" name="firstName"
             required value="<c:out value='${previousFirstName}' />"></p>

            <p><label for="lastName">Last Name</label><input type="text" id="lastName" name="lastName"
             required value="<c:out value='${previousLastName}' />"></p>

            <p><label for="email">Email</label><input type="email" id="email" name="email" required value="<c:out value='${previousEmail}' />"></p>

            <p><label for="password">Password</label><input type="password" id="password"  name="password"
             required></p>

            <p><input type="submit" id="signUp" value="Sign Up"></p>

        </form>
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
