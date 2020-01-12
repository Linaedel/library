<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %><html>
<head>
    <title>Библиотека</title>
</head>
<body>
    <h2><a href="/client">Добавить читателя</a></h2>
    <h2>Список читателей:</h2>
    <div>
        <c:forEach items="${clients}" var="person">
            ${person.name}. ${person.phone} <a href="/librarians/del/${person.id}">Удалить</a> <br/>
        </c:forEach>
    </div>
</body>
</html>
