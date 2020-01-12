<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Библиотека</title>
</head>
<body>
<form:form action="/client" modelAttribute="client" method="post" enctype="application/x-www-form-urlencoded">
    <form:hidden path="id"/> <br/>
    USERNAME: <form:input path="username"/> <br/>
    PASSWORD: <form:input path="password"/> <br/>
    NAME: <form:input path="name"/> <br/>
    ADDRESS: <form:input path="address"/> <br/>
    PHONE: <form:input path="phone"/> <br/>
    <input type="hidden" name="deleted" value="0">
    <input type="submit" value="Сохранить"/>
</form:form>
</body>
</html>
