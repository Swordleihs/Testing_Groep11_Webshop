<%@ page import="java.util.Map" %>
<%@ page import="java.util.HashMap" %>
<%@ page import="domain.service.ShopService" %>
<%@ page import="java.util.Iterator" %>
<%@ page import="domain.model.Product" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1" />
    <title>Shopping Cart</title>
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
                <c:if test="${loggedIn != null}"><li><a href="Controller?action=signUp">Add Admin</a></li></c:if>
                <c:if test="${loggedIn == null}"><li></li></c:if>
                <li class="cartLi" id="actual"><a href="Controller?action=cart" id="cartLink"><img src="images/ShoppingCart.png" id="cartImage"></a></li>
            </ul>
        </nav>
        <h2>Shopping Cart</h2>
    </header>
    <main>
        <c:if test="${products != null}">
            <table id="overview">
                <tr>
                    <th>Name</th>
                    <th>Description</th>
                    <th>Price (€)</th>
                    <th>Amount</th>
                </tr>
                <%  Map<Integer, Integer> products = (HashMap<Integer, Integer>)session.getAttribute("products");
                    ShopService service = (ShopService)request.getAttribute("service");
                    double total = 0.0;
                    try{
                        Iterator iterator = products.entrySet().iterator();
                        while (iterator.hasNext()) {
                            HashMap.Entry pair = (HashMap.Entry)iterator.next();
                            int productId = Integer.parseInt(pair.getKey().toString());
                            Product product = service.getProduct(productId);
                            int amount = (int)pair.getValue();
                            total += amount*product.getPrice();
                %>
                <tr>
                    <td><%=product.getName() %></td>
                    <td><%=product.getDescription() %></td>
                    <td><%=product.getPrice()%></td>
                    <td><%=amount %></td>
                    <td><a href="Controller?action=removeFromCart&productId=<%=product.getProductId()%>">remove</a></td>
                </tr>
                <%
                        }
                    }catch (Exception e){
                        e.printStackTrace();
                    }%>
            </table>
            <p id="totalPrice">Total price: €<%=total%></p>
            <p><a href="Controller?action=payPage&amount=<%=total%>" id="buyButton">Buy</a></p>
        </c:if>
        <c:if test="${products == null}">
            <p>No products in shopping cart.</p>
        </c:if>
        <c:if test='${paymentSucces != null}'>
            <p id="positive">Payment successful</p>
        </c:if>
    </main>
    <footer> &copy; Testing, UC Leuven-Limburg </footer>
</div>
</body>
</html>
