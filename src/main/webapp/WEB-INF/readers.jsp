<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %><html>
<head>
    <title>Библиотека</title>
</head>
<body>
    <h2><a href="/reader">Добавить читателя</a></h2>
    <h2>Список читателей:</h2>
    <div>
        <c:forEach items="${readers}" var="person">
            <a href="/reader/${person.id}">${person.name}</a>. ${person.phone} <a href="/readers/del/${person.id}">Удалить</a> <br/>
        </c:forEach>
    </div>
</body>
<a href="/index">На главную</a> <br/>
<a href="/logout">Выйти</a> <br/>

</html>
