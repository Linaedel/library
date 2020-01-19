<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Библиотека</title>
</head>
<body>
<h1>Список доступных книг.</h1>
<div>
    <c:forEach items="${availableBooks.viewMap}" var="book">
        ${book.key.name}. ${book.key.author}. В наличие: ${book.key.available} шт.
            <c:if test="${book.value == 0}">
                <input type="button" value="Запросить" onclick="location.href='/availablebooks/${book.key.id}'"/>
            </c:if>
            <c:if test="${book.value == 1}">
                Книга запрошена.
            </c:if>
        <br/>
    </c:forEach>
    <a href="/index">На главную</a> <br/>
    <a href="/logout">Выйти</a> <br/>
</div>
</body>
</html>