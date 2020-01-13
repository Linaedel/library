<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<html>
<head>
    <title>Библиотека</title>
</head>
<body>
<h1>Библиотека</h1>
<sec:authorize access="hasRole('ROLE_ADMIN')">
    <a href="/librarians">Редактировать список библиотекарей</a></br>
</sec:authorize>
<sec:authorize access="hasRole('ROLE_LIBRARIAN')">
    <a href="/readers">Список читателей</a>
    <a href="/books">Список книг</a>
</sec:authorize>
<sec:authorize access="hasRole('ROLE_READER')">
    <a href="/availablebooks">Взять книгу</a>
    <a href="/holdedbooks">Вернуть книгу</a>
</sec:authorize>
<sec:authorize access="!isAuthenticated()">
    <a href="/login">Login</a> <br/>
</sec:authorize>
<sec:authorize access="isAuthenticated()">
    <a href="/logout">Logout</a> <br/>
</sec:authorize>
</body>
</html>
