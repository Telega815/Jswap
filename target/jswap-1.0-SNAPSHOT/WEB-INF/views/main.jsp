<%--
  Created by IntelliJ IDEA.
  User: Telega
  Date: 20.02.2018
  Time: 8:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%--<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>--%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<html>
<head>
    <title>qwe</title>
</head>
<body>
    <p>fdsgdfgdfgsdfgdfsggfdsfgsdfg</p>

    <%--@elvariable id="user" type="ru.jswap.objects.User"--%>
    <form:form method="post" modelAttribute="user" class="box Login">

        <fieldset class="boxBody">

            <form:label path="name">
                Username
            </form:label>
            <form:input path="name"/>
        </fieldset>

        <input type="submit" class="btnLogin" value="Login" name="_eventId_submit">

    </form:form>
</body>
</html>
