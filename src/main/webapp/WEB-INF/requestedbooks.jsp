<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Библиотека</title>
</head>
<body>
<h1>Список запрошенных книг:</h1>
<div>
    <c:if test="${requestedBooks.hasOne == 0}">
        Запрошенных книг на данный момент нет.
    </c:if>
    <c:forEach items="${requestedBooks.collection}" var="book">
        ${book.name}. ${book.author}. В наличии: ${book.available}. Запрашивают:
        <c:forEach items="${book.requesters}" var="requester">
            <c:if test="${book.available == 0}">
                ${requester.name},
            </c:if>
            <c:if test="${book.available > 0}">
                <a href="/requestedbooks/give?book=${book.id}&person=${requester.id}">${requester.name}</a>,
            </c:if>
        </c:forEach>
        <br/>
    </c:forEach>
    <a href="/index">На главную</a> <br/>
    <a href="/logout">Выйти</a> <br/>

</div>
</body>
</html>
