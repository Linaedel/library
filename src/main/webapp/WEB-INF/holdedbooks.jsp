<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Библиотека</title>
</head>
<body>
<h1>Список книг на руках:</h1>
<div>
    <c:forEach items="${holdedBooks}" var="book">
        ${book.description.name}. ${book.description.author}. 
        <c:if test="${book.returned == 0}">
            <input type="button" value="Вернуть" onclick="location.href='/holdedbooks/${book.id}'"/>
        </c:if>
        <c:if test="${book.returned == 1}">
            Книга отправлена в библиотеку.
        </c:if>
        <br/>
    </c:forEach>
</div>

</body>
</html>
