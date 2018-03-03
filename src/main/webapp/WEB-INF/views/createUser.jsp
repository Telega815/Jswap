<%--
  Created by IntelliJ IDEA.
  User: Telega
  Date: 28.02.2018
  Time: 15:15
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<html>
<head>
    <title>qwe</title>
</head>
<body>


<%--@elvariable id="user" type="ru.jswap.entities.User"--%>
<form:form method="POST" modelAttribute="user"
           class="box login">


    <fieldset class="boxBody">



        <form:label path="username">
            Username
        </form:label>
        <form:input path="username" />



    </fieldset>

    <footer>

        <input type="submit" class="btnLogin"
               value="Cancel" name="_eventId_cancel">

        <input type="submit" class="btnLogin"
               value="Create" name="_eventId_submit">
    </footer>

</form:form>


</body>
</html>
