<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1" />
<meta charset="UTF-8">
<title>Admin Overview</title>
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
Admin Overview
</h2>

</header>

    <main>
    <c:if test="${personen != null}">
        <table id="overview">
            <tr>
                <th>E-mail</th>
                <th>First Name</th>
                <th>Last Name</th>
                <th>Check Password</th>
            </tr>
            <c:forEach var="person" items="${personen}">
                <tr>
                    <td><c:out value='${person.email}' /></td>
                    <td><c:out value='${person.firstName}'/></td>
                    <td><c:out value='${person.lastName}'/></td>
                    <td><a href="Controller?action=checkPas&personId=<c:out value='${person.userid}'/>">Check</a></td>
                    <td><a href="Controller?action=deletePersonPage&userid=<c:out value='${person.userid}'/>">Delete</a></td>
                </tr>
            </c:forEach>
            <caption>Users Overview</caption>
        </table>
    </c:if>
    <c:if test="${personen == null}">
        <p>No users found</p>
    </c:if>
</main>
<footer>
&copy; Testing, UC Leuven-Limburg
</footer>
</div>
</body>
</html>