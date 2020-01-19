<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Библиотека</title>
</head>
<body>

    <form:form action="/book"  method="post" enctype="application/x-www-form-urlencoded">
        <input type="hidden" name = "id" value="${bookdescription.details.id}"/> <br/>
        <input type="text" name= "name" value="${bookdescription.details.name}"/> <br/>
        <input type="text" name= "author" value="${bookdescription.details.author}"/> <br/>
        <input type="text" name= "isbn" value="${bookdescription.details.isbn}"/> <br/>
        <input type="submit" value="Сохранить"/>
    </form:form>
    <h2>Книги в библиотеке: </h2>
    <div>
        <c:forEach items="${bookdescription.booksOnHolder}" var="book">
            ${book.key.id} => ${book.value.name}   <a href="/book/del/${book.key.id}">Удалить</a> <br/>
        </c:forEach>
    </div>

    <h2>Книги на руках: </h2>
    <div>
        <c:forEach items="${bookdescription.booksOnHands}" var="book">
            ${book.key.id} => ${book.value.name}<br/>
        </c:forEach>
    </div>
    <a href="/index">На главную</a> <br/>
    <a href="/logout">Выйти</a> <br/>
</body>
</html>
