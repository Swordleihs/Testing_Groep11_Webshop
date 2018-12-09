<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1" />
    <meta charset="UTF-8">
    <title>Admin Login</title>
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
                <c:if test="${loggedIn != null}"><li><a href="Controller?action=signUp">Add admin</a></li></c:if>
                <c:if test="${loggedIn == null}"><li></li></c:if>
                <li id="cartLi"><a href="Controller?action=cart" id="cartLink"><img src="images/ShoppingCart.png" id="cartImage"></a></li>
            </ul>
        </nav>
        <h2>
            Admin Login
        </h2>
    </header>
        <main>
            <c:if test="${loggedIn == null}">
                <form id="a" method="post" action="Controller?action=login">
                    <ul>
                        <p><label for="userId">User ID</label><input type="text" id="userId" name="userId" required value="<c:out value='${previousUserId}' />"></p>

                        <p><label for="passwd">Password</label><input type="password" id="passwd" name="passwd" required></p>
                    </ul>
                    <input type="submit" value="Activate admin mode">
                </form>
            </c:if>
            <c:if test="${loggedIn != null}">
                <p>You are already logged in.</p>
            </c:if>
            <c:if test="${loginError != null}">
                <p class="alert-danger"><c:out value="${loginError}"/></p>
            </c:if>
        </main>
    <footer>
        &copy; Testing, UC Leuven-Limburg
    </footer>
</div>
</body>
</html>
