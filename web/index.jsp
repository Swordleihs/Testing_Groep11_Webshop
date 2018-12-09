<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1" />
<title>Home</title>
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
					<li id="actual"><a href="Controller?action=index">Home</a></li>
					<li><a href="Controller?action=productOverview">Products</a></li>
					<c:if test="${loggedIn != null}"><li><a href="Controller?action=addProductPage">Add Product</a></li></c:if>
					<c:if test="${loggedIn == null}"><li></li></c:if>
					<c:if test="${loggedIn != null}"><li><a href="Controller?action=signUp">Add Admin</a></li></c:if>
					<c:if test="${loggedIn == null}"><li></li></c:if>
					<li id="cartLi"><a href="Controller?action=cart" id="cartLink"><img src="images/ShoppingCart.png" id="cartImage"></a></li>
				</ul>
			</nav>
			<h2>Home</h2>
		</header>
		<main>
			<p>Welcome to the Prato webshop.</p>
		</main>
		<footer> &copy; Testing, UC Leuven-Limburg </footer>
	</div>
</body>
</html>