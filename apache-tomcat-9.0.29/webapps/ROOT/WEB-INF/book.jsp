<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Библиотека</title>
</head>
<body>

    <form:form action="/book" modelAttribute="bookdescription" method="post" enctype="application/x-www-form-urlencoded">
        <form:hidden path="id"/> <br/>
        <form:input path="name"/> <br/>
        <form:input path="author"/> <br/>
        <form:input path="isbn"/> <br/>
        <input type="submit" value="Сохранить"/>
    </form:form>

</body>
</html>
