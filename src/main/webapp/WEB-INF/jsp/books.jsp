<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Библиотека</title>
</head>
<body>
<h1>Список доступных книг.</h1>
<div>
    <c:forEach items="${books}" var="bookdescription">
        <a href="/book/${bookdescription.id}">${bookdescription.author}. ${bookdescription.name} </a> <br/>
    </c:forEach>
</div>
</body>
</html>
