<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Библиотека</title>
</head>
<body>
<h1>Список доступных книг.</h1>
<div>
    <c:forEach items="${availableBooks}" var="book">
        ${book.key.name}. ${book.key.author}. В наличие: ${book.value} шт. <br/>
    </c:forEach>
</div>
</body>
</html>