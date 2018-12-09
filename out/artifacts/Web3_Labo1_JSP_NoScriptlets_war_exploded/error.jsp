<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isErrorPage="true"%>
<html>
<head>
    <title>Error</title>
    <link rel="stylesheet" type="text/css" href="css/style.css">
</head>
<body>
    <h1>A problem occured</h1>
    <p>It seems the server is experiencing a problem: <c:out value="${pagecontext.exception}"/></p>
    <p>Please try again.</p>
    <p> <a href="Controller?action=index">Home</a></p>
</body>
</html>