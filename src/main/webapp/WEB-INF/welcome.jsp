<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Библиотека</title>
</head>
<body>
    <sec:authorize access="isAuthenticated()">
        <h2>Приветствуем, <sec:authentication property="name"/>!</h2>
        <h3><a href="/index">Продолжить работу</a></h3>
        <h3><a href="/logout">Выйти</a></h3>
    </sec:authorize>
</body>
</html>