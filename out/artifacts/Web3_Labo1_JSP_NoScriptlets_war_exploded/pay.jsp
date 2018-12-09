<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1" />
    <meta charset="UTF-8">
    <title>Pay</title>
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
                <li><a href="Controller?action=productOverview">Products</a></li>
                <c:if test="${loggedIn != null}"><li><a href="Controller?action=addProductPage">Add Product</a></li></c:if>
                <c:if test="${loggedIn == null}"><li></li></c:if>
                <c:if test="${loggedIn != null}"><li><a href="Controller?action=signUp">Add admin</a></li></c:if>
                <c:if test="${loggedIn == null}"><li></li></c:if>
                <li id="cartLi"><a href="Controller?action=cart" id="cartLink"><img src="images/ShoppingCart.png" id="cartImage"></a></li>
            </ul>
        </nav>
        <h2>
            Payment Options
        </h2>
    </header>
        <c:if test="${products != null}">
            <main>
                <p id="paymentMethod">Select a payment method:</p>
                <div id="images">
                    <div class="imageContainer0">
                        <img src="images/BankcontactLogo.png" class="payMethodWidth">
                        <input type="radio" name="payment" checked id="bankcontact">
                    </div>
                    <div class="imageContainer1">
                        <img src="images/VisaLogo.png" class="payMethodWidth">
                        <input type="radio" name="payment" id="visa">
                    </div>
                    <div class="imageContainer2">
                        <img src="images/PaypalLogo.png" class="payMethodHeight">
                        <input type="radio" name="payment" id="paypal">
                    </div>
                    <div class="imageContainer3">
                        <img src="images/MastercardLogo.png" class="payMethodWidth">
                        <input type="radio" name="payment" id="master">
                    </div>
                </div>
                <p id="totalPrice">Total price: €<c:out value="${amount}"/></p>
                <p><a href="Controller?action=clearCart" id="buyButton">Checkout</a></p>
            </main>
        </c:if>
        <c:if test="${products == null}">
            <p class="alert-danger2">Your shopping cart is empty.</p>
        </c:if>
    <footer>
        &copy; Testing, UC Leuven-Limburg
    </footer>
</div>
</body>
</html>