<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h2>Добавить нового библиотекаря: </h2>

<form:form action="/librarians" method="post" enctype="application/x-www-form-urlencoded">
    <input type="text" name = "username"/> <br/>
    <input type="text" name="password"/> <br/>
    <input type="submit" value="Добавить"/>
</form:form>

<h2>Библиотекари: </h2>
<div>
    <c:forEach items="${librarians}" var="librarian">
        ${librarian.username}. ${librarian.password} <a href="/librarians/del/${librarian.id}">Удалить</a> <br/>
    </c:forEach>
</div>
</body>
</html>
