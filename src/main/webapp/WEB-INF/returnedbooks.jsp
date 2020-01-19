<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Библиотека</title>
</head>
<body>
<h1>Список возвращаемых книг:</h1>
<div>
    <c:if test="${returnedBooks.hasOne == 0}">
        Книг к сдаче на данный момент нет.
    </c:if>
    <c:forEach items="${returnedBooks.collection}" var="book">
        ${book.description.name}. ${book.description.author}. Возвращает: ${book.returner.name}
        <c:forEach items="${returnedBooks.placements}" var="placement">
            <input type="button" value="${placement.name}" onclick="location.href='/returnedbooks/take?book=${book.id}&place=${placement.id}'"/>
        </c:forEach>
        <br/>
    </c:forEach>

</div>
<a href="/index">На главную</a> <br/>
<a href="/logout">Выйти</a> <br/>

</body>
</html>
