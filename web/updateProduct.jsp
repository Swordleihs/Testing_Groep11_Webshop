<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1" />
    <meta charset="UTF-8">
    <title>Update Product</title>
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
            Update Product
        </h2>
    </header><main>
    <c:if test="${errors.size() > 0}">
        <div class="alert-danger">
            <ul>
                <c:forEach var="error" items="${errors}">
                    <li>${error}</li>
                </c:forEach>
            </ul>
        </div>
    </c:if>
    <form method="post" action="Controller?action=updateProduct&prodid=<c:out value="${previousProductId}"/>" novalidate="novalidate">
        <p><label for="name">Name</label><input type="text" id="name" name="name"
                                                required value="<c:out value='${previousName}' />"></p>

        <p><label for="desc">Description</label><input type="text" id="desc" name="desc"
                                                       required value="<c:out value='${previousDescription}' />"></p>

        <p><label for="price">Price</label><input type="number" id="price" name="price" step="0.01" required value="<c:out value='${previousPrice}' />"></p>


        <p><input type="submit" id="update" value="Update Product"></p>

    </form>
</main>
    <footer>
        &copy; Testing, UC Leuven-Limburg
    </footer>
</div>

</body>
</html>
