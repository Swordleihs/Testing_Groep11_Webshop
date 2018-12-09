<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1" />
    <meta charset="UTF-8">
    <title>Product Overview</title>
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
                <c:if test="${loggedIn == null}">
                    <p><a href="Controller?action=loginPage" class="adminMode">Admin login</a></p>
                </c:if>
            </h2>
            <h1>
                <span>Prato Webshop</span>
            </h1>
        </div>
        <nav>
            <ul>
                <li><a href="Controller?action=index">Home</a></li>
                <li id="actual"><a href="Controller?action=productOverview">Products</a></li>
                <c:if test="${loggedIn != null}"><li><a href="Controller?action=addProductPage">Add Product</a></li></c:if>
                <c:if test="${loggedIn == null}"><li></li></c:if>
                <c:if test="${loggedIn != null}"><li><a href="Controller?action=signUp">Add admin</a></li></c:if>
                <c:if test="${loggedIn == null}"><li></li></c:if>
                <li id="cartLi"><a href="Controller?action=cart" id="cartLink"><img src="images/ShoppingCart.png" id="cartImage"></a></li>
            </ul>
        </nav>
        <h2>
            Product Overview
        </h2>

    </header><main>
    <c:if test="${producten != null}">
        <table id="overview">
            <tr>
                <th>Name</th>
                <th>Description</th>
                <th>Price (€)</th>
            </tr>
            <c:forEach var="product" items="${producten}">
                <tr>
                    <td><c:if test="${loggedIn != null}"><a href="Controller?action=updateProductPage&id=<c:out value="${product.productId}"/>"></c:if> <c:out value="${product.name}"/> </td>
                    <td> <c:out value="${product.description}"/> </td>
                    <td> <c:out value="${product.price}"/> </td>
                    <c:if test="${loggedIn != null}"><td><a href="Controller?action=deleteProductPage&productId=<c:out value="${product.productId}"/>">delete</a></td></c:if>
                    <td>amount:</td>
                    <td id="addToCartTd">
                        <form id="addToCartForm" method="post" action="Controller?action=addToCart&productId=<c:out value="${product.productId}"/>">
                            <input type="number" min="1" max="999" value="1" id="amount" name="amount">
                            <input type="submit" id="addToCart" value="Add to cart">
                        </form>
                    </td>
                </tr>
            </c:forEach>
            <caption>Product Overview</caption>
        </table>
    </c:if>
    <c:if test="${producten == null}">
        <p>No products found</p>
    </c:if>
</main>
    <footer>
        &copy; Testing, UC Leuven-Limburg
    </footer>
</div>
</body>
</html>
