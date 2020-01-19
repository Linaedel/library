<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>

<html>
<head>
    <title>Библиотека</title>
</head>
<body>
<form:form action="/reader" modelAttribute="reader" method="post" enctype="application/x-www-form-urlencoded">
    <form:hidden path="id"/> <br/>
    USERNAME: <form:input path="username"/> <br/>
    PASSWORD: <form:input path="password"/> <br/>
    NAME: <form:input path="name"/> <br/>
    ADDRESS: <form:input path="address"/> <br/>
    PHONE: <form:input path="phone"/> <br/>
    <input type="hidden" name="deleted" value="0">
    <input type="submit" value="Сохранить"/>
    <c:if test="${reader.id != null}">
        <input type="button" value="Удалить" onclick="location.href='/readers/del/${reader.id}'"/>
    </c:if>
</form:form>
</body>
</html>
